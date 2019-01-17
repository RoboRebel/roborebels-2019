package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Sensors extends Subsystem {
    private final DigitalInput photoswitch;

    public Sensors(){
        photoswitch = new DigitalInput(RobotMap.LIGHT_SENSOR_DIO_PORT);
    }

    public boolean getPhotoswitchStatus() {
        return photoswitch.get();
    }

    public void updateShuffleboard(){
        SmartDashboard.putBoolean("Photoswitch status", getPhotoswitchStatus());
    }
    @Override
    protected void initDefaultCommand() {

    }
}
