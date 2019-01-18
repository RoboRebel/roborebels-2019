package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.sensors.Lidar;

public class Sensors extends Subsystem {
    private final DigitalInput photoswitch;
    private final Lidar lidar;

    public Sensors(){
        photoswitch = new DigitalInput(RobotMap.LIGHT_SENSOR_DIO_PORT);
        lidar = new Lidar(I2C.Port.kMXP, (byte) 0x62);
    }

    public boolean getPhotoswitchStatus() {
        return photoswitch.get();
    }

    public int getLidarDistance(){
        return lidar.getDistance();
    }

    public void updateShuffleboard(){
        SmartDashboard.putBoolean("Photoswitch status", getPhotoswitchStatus());
        SmartDashboard.putNumber("LIDAR Distance (cm)", getLidarDistance());
        SmartDashboard.putNumber("LIDAR Distance (in)", ((double) getLidarDistance()) / 2.54);
    }
    @Override
    protected void initDefaultCommand() {

    }
}
