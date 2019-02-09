package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.interfaces.TestableSensor;

public class Ultrasonic implements TestableSensor {
    private static final double VOLTS_PER_5_MILLIMETER = 5.0/1024.0;

    private AnalogInput sensor;

    private double testAccum;
    private double testValue;
    private int testNum;
    private final int port;

    public Ultrasonic(int port){
        this.port = port;
        sensor = new AnalogInput(port);
    }

    public double getDistance(){
        return 5.0 * (sensor.getVoltage() / VOLTS_PER_5_MILLIMETER);
    }

    @Override
    public void resetTest() {
        testValue = 0;
        testAccum = 0;
        testNum = 0;
    }

    @Override
    public void test() {
        testNum++;
        testAccum += this.getDistance();
        testValue = testAccum / (double) testNum;
    }

    @Override
    public String getStatus() {
        return String.format("Ultrasonic sensor on port %d average distance(mm) over test: %d", this.port, testValue);
    }
}
