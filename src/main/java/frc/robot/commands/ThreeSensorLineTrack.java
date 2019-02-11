package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ThreeSensorLineTrack extends Command {
    private double startAngle;

    public ThreeSensorLineTrack(){
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        startAngle = Robot.sensors.getNavXYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        int[] data = Robot.sensors.getPhotoswitchStatuses();
        switch(data[0] + 2 * data[1] + 4 * data[2] ) { // converts it to binary to make comparing easier
            case 0b000:
            case 0b010:
            case 0b101:
            case 0b111:
                Robot.drivetrain.tankDrive(0.2, 0.2);
                break;
            case 0b100:
                Robot.drivetrain.tankDrive(-0.3, 0.3);
                break;
            case 0b001:
                Robot.drivetrain.tankDrive(0.3, -0.3);
                break;
            case 0b110:
                Robot.drivetrain.tankDrive(0, 0.2);
                break;
            case 0b011:
                Robot.drivetrain.tankDrive(0.2, 0);
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
        Robot.drivetrain.tankDrive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        this.end();
    }
}