package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.interfaces.TestableSensor;
import frc.robot.sensors.Photoswitch;
import frc.robot.sensors.Ultrasonic;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.interfaces.Testable;
import frc.robot.sensors.Lidar;

import java.util.ArrayList;

public class Sensors extends Subsystem implements Testable {

    private enum SensorType{
        PID, Digital
    }

    private final int NUM_SAMPLES = 10;
    public final int NUM_PHOTOSWITCHES = 3;

    private Photoswitch[] photoswitches;

    private final Lidar lidar;
    private int[] lidarValues;

    private final AHRS navx;

    private final Ultrasonic ultrasonic;

    private final ArrayList<TestableSensor> testUnits;

    public Sensors(){
        testUnits = new ArrayList<>();
        photoswitches = new Photoswitch[NUM_PHOTOSWITCHES];
        for(int i = 0; i < NUM_PHOTOSWITCHES; i++) {
            photoswitches[i] = new Photoswitch(RobotMap.LIGHT_SENSOR_DIO_PORTS[i]);
            testUnits.add(photoswitches[i]);
        }
        lidar = new Lidar(I2C.Port.kMXP, (byte) 0x62);
        testUnits.add(lidar);
        ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_CHANNEL);
        testUnits.add(ultrasonic);
        navx = new AHRS(SPI.Port.kMXP);
        lidarValues = new int[NUM_SAMPLES];
    }

    public boolean getPhotoswitchStatus(int index) {
        return !photoswitches[index].get();
    }

    public int[] getPhotoswitchStatuses(){
        int[] ret = new int[NUM_PHOTOSWITCHES];
        for(int i = 0; i < NUM_PHOTOSWITCHES; i++)
            ret[i] = !photoswitches[i].get() ? 1 : 0;
        return ret;
    }

    public double getUltrasonicDistance(){
        return ultrasonic.getDistance();
    }
    public int getLidarDistance(){
        return lidar.getDistance();
    }

    public int getAvgLidarDistance(){
        updateLidarValues();
        int accum = 0;
        for(int num : lidarValues)
            accum += num;
        return accum/ NUM_SAMPLES;
    }

    private void updateLidarValues(){
        for(int i = 0; i < lidarValues.length - 1; i++)
            lidarValues[i] = lidarValues[i+1];
        lidarValues[lidarValues.length-1] = (int) ((double) getLidarDistance() / 2.54);
    }

    public double getNavXYaw(){
        return navx.getYaw();
    }

    public AHRS getNavx(){
        return navx;
    }

    public void updateShuffleboard(){
        SmartDashboard.putBoolean("Photoswitch1 status", getPhotoswitchStatus(0));
        SmartDashboard.putBoolean("Photoswitch2 status", getPhotoswitchStatus(1));
        SmartDashboard.putBoolean("Photoswitch3 status", getPhotoswitchStatus(2));
//        SmartDashboard.putString("Photoswitch Binary String", Integer.toBinaryString(getPhotoswitchStatuses()[0]+getPhotoswitchStatuses()[1]*2+ getPhotoswitchStatuses()[2]*3));
//        SmartDashboard.putNumber("LIDAR Distance (cm)", getLidarDistance());
//        SmartDashboard.putNumber("LIDAR Distance (in)", ((double) getLidarDistance()) / 2.54);
        SmartDashboard.putNumber("Right Encoder", Robot.drivetrain.getRightEncoderSpeed());
        SmartDashboard.putNumber("Left Encoder", Robot.drivetrain.getLeftEncoderSpeed());
        SmartDashboard.putNumber("Right encoder distance", Robot.drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("Left encoder distance", Robot.drivetrain.getLeftEncoderDistance());
        SmartDashboard.putNumber("AVG LIDAR Distance (in)", getAvgLidarDistance());
        SmartDashboard.putNumber("NavX Yaw", getNavXYaw());
        SmartDashboard.putNumber("POV", Robot.oi.getXboxController().getPOV());
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void testUnit(int index, boolean status) {
        if(status)
            testUnits.get(index).test();
        else
            testUnits.get(index).getStatus();
    }

    @Override
    public void resetTest() {
        for(TestableSensor testableSensor : testUnits)
            testableSensor.resetTest();
    }

    @Override
    public int getUnitAmount() {
        return testUnits.size();
    }

    @Override
    public Subsystem getRobotSubsystem() {
        return this;
    }
}
