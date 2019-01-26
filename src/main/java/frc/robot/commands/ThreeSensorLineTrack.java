package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import java.util.Arrays;

public class ThreeSensorLineTrack extends Command {
    private double startAngle;

    public ThreeSensorLineTrack() {
        requires(Robot.sensors);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startAngle = Robot.sensors.getNavXYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

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