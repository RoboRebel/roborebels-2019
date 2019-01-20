package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.sensors.Lidar;

import java.util.Arrays;

public class Sensors extends Subsystem {
    private final int numSamples = 10;

    private final DigitalInput photoswitch;
    private final Lidar lidar;

    private int[] lidarValues;

    public Sensors(){
        photoswitch = new DigitalInput(RobotMap.LIGHT_SENSOR_DIO_PORT);
        lidar = new Lidar(I2C.Port.kMXP, (byte) 0x62);
        lidarValues = new int[numSamples];
    }

    public boolean getPhotoswitchStatus() {
        return photoswitch.get();
    }

    public int getLidarDistance(){
        return lidar.getDistance();
    }

    public int getAvgLidarDistance(){
        updateLidarValues();
        int accum = 0;
        for(int num : lidarValues)
            accum += num;
        return accum/numSamples;
    }

    private void updateLidarValues(){
        for(int i = 0; i < lidarValues.length - 1; i++)
            lidarValues[i] = lidarValues[i+1];
        lidarValues[lidarValues.length-1] = getLidarDistance();
    }

    public void updateShuffleboard(){
        SmartDashboard.putBoolean("Photoswitch status", getPhotoswitchStatus());
        SmartDashboard.putNumber("LIDAR Distance (cm)", getLidarDistance());
        SmartDashboard.putNumber("LIDAR Distance (in)", ((double) getLidarDistance()) / 2.54);
//        SmartDashboard.putNumber("AVG LIDAR Distance (cm)", getAvgLidarDistance());
    }
    @Override
    protected void initDefaultCommand() {

    }
}
