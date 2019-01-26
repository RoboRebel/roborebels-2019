package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import java.util.Arrays;

public class ThreeSensorLineTrack extends Command {
    private double startAngle;
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startAngle = Robot.sensors.getNavXYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        int[] data = {Robot.sensors.getPhotoswitchStatus() ? 1 : 0, Robot.sensors.getPhotoswitch2Status() ? 1 : 0, Robot.sensors.getPhotoswitch3Status() ? 1 : 0};
        switch(data[0] + 2 * data[1] + 4 * data[2] ) { // converts it to binary to make comparing easier
            case 0b000:
            case 0b010:
            case 0b110:
            case 0b011:
            case 0b101:
            case 0b111:
                System.out.println("Straight");
                break;
            case 0b100:
                System.out.println("Clockwise");
                break;
            case 0b001:
                System.out.println("Counter Clockwise");
                break;
            default:
                System.out.println("Something has gone very wrong.");
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}