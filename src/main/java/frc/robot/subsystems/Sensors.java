package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.sensors.Lidar;

public class Sensors extends Subsystem {
    private final int NUM_SAMPLES = 10;

    private final DigitalInput photoswitch;
    private final DigitalInput photoswitch2;
    private final Lidar lidar;
    private final AHRS navx;

    private int[] lidarValues;

    public Sensors(){
        photoswitch = new DigitalInput(RobotMap.LIGHT_SENSOR_DIO_PORT);
        photoswitch2 = new DigitalInput(RobotMap.LIGHT_SENSOR_2_DIO_PORT);
        lidar = new Lidar(I2C.Port.kMXP, (byte) 0x62);
        navx = new AHRS(SPI.Port.kMXP);
        lidarValues = new int[NUM_SAMPLES];
    }

    public boolean getPhotoswitchStatus() {
        return photoswitch.get();
    }

    public boolean getPhotoswitch2Status(){
        return !photoswitch2.get();
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
        SmartDashboard.putBoolean("Photoswitch status", getPhotoswitchStatus());
        SmartDashboard.putBoolean("Photoswitch2 status", getPhotoswitch2Status());
        SmartDashboard.putNumber("LIDAR Distance (cm)", getLidarDistance());
        SmartDashboard.putNumber("LIDAR Distance (in)", ((double) getLidarDistance()) / 2.54);
        SmartDashboard.putNumber("Right Encoder", Robot.drivetrain.getRightEncoderSpeed());
        SmartDashboard.putNumber("Left Encoder", Robot.drivetrain.getLeftEncoderSpeed());
        SmartDashboard.putNumber("Right encoder distance", Robot.drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("Left encoder distance", Robot.drivetrain.getLeftEncoderDistance());
        SmartDashboard.putNumber("AVG LIDAR Distance (in)", getAvgLidarDistance());
        SmartDashboard.putNumber("NavX Yaw", getNavXYaw());
    }

    @Override
    protected void initDefaultCommand() {

    }
}
