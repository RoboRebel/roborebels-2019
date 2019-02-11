package frc.robot.sensors;

import java.nio.ByteBuffer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.interfaces.TestableSensor;


//taken from this chiefdelphi post
//https://www.chiefdelphi.com/t/how-do-i-use-the-lidar-with-a-navx-mxp/161381/10

public class Lidar implements PIDSource, TestableSensor {

    private I2C i2c;
    private java.util.Timer updater;
    private ByteBuffer buffer = ByteBuffer.allocateDirect(3);
    private volatile int distance;
    private int measurementCount = 0;

    private static final int LIDAR_BUSY_MASK = 0x01;
    private static final int LIDAR_COMMAND_ACQUIRE_WITHOUT_CORRECTION = 0x03;
    private static final int LIDAR_COMMAND_ACQUIRE_WITH_CORRECTION = 0x04;
    private static final int LIDAR_CONFIG_REGISTER = 0x00;
    private static final int LIDAR_STATUS_REGISTER = 0x01;
    private static final int LIDAR_SIG_COUNT = 0x02;
    private static final int LIDAR_ACQ_CONFIG = 0x04;
    private static final int LIDAR_THRESHOLD_BYPASS = 0x1c;
    private static final int LIDAR_DISTANCE_REGISTER = 0x8f;

    private static final int UPDATE_PERIOD = 20; // in milliseconds
    private static final int RETRY_COUNT = 50;

    private final Port port;

    private int testResult;
    private int testAccum;
    private int testAmount;

    public Lidar(Port port, byte address) {
        this.port = port;
        i2c = new I2C(port, address);

        setup();

        updater = new java.util.Timer();
        updater.schedule(new TimerTask() {
            @Override
            public void run() {
                distance = getUpdatedDistance();
            }
        }, 0, UPDATE_PERIOD);
        System.out.println("Started");
    }

    // Distance in cm
    public int getDistance() {
        return distance;
    }

    public void setup() {
        i2c.write(LIDAR_SIG_COUNT, 0x80);
        sleep(1);
        i2c.write(LIDAR_ACQ_CONFIG, 0x08);
        sleep(1);
        i2c.write(LIDAR_THRESHOLD_BYPASS, 0x00);
        sleep(1);
    }

    // Update distance variable
    private int getUpdatedDistance() {
        int command = (measurementCount % 100 == 0 ? LIDAR_COMMAND_ACQUIRE_WITH_CORRECTION : LIDAR_COMMAND_ACQUIRE_WITHOUT_CORRECTION);
        i2c.write(LIDAR_CONFIG_REGISTER, command); // Initiate measurement
		/*if (measurementCount++ % 50 == 0) {
			System.out.println("count = " + measurementCount + ", distance = " + distanceValue);
		}*/
        measurementCount++;
        int busyCount = 0;
        do {
            sleep(1);
            int status = readByte(LIDAR_STATUS_REGISTER);
            boolean busy = (status & LIDAR_BUSY_MASK) == LIDAR_BUSY_MASK;
            if (!busy) {
                return readShort(LIDAR_DISTANCE_REGISTER);
            } else {
                busyCount++;
            }
			/*SmartDashboard.putNumber("status", status);
			SmartDashboard.putBoolean("busyFlag", busy);*/
        } while (busyCount < RETRY_COUNT);
        System.out.println("Distance read timed out");
        return distance;
    }

    private void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private int readByte(int register) {
        buffer.put(0, (byte) register);
        i2c.writeBulk(buffer, 1);
        i2c.readOnly(buffer, 1);
        return buffer.get(0) & 0xFF;
    }

    private int readShort(int register) {
        buffer.put(0, (byte) register);
        i2c.writeBulk(buffer, 1);
        i2c.readOnly(buffer, 2);
        return buffer.getShort(0) & 0xFFFF;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        return getDistance();
    }

    @Override
    public void resetTest(){
        testAmount = 0;
        testResult = 0;
        testAccum = 0;
    }

    @Override
    public void test() {
        testAmount++;
        testAccum += getDistance();
        testResult = testAccum / testAmount;
    }

    @Override
    public String getStatus() {
//        this.resetTest();
        return String.format("LIDAR on port %s average distance(mm) over test: %d", port.name() ,testResult);
    }
}
