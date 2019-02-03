package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AcquireLine extends Command {
    private static final double DELTA_DISTANCE = 15.0;

    private double targetDistance;
    private String direction;
    public AcquireLine() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        targetDistance = Robot.drivetrain.getRightEncoderDistance() - DELTA_DISTANCE;
        direction = "Right";
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(direction.equals("Right")){
            if(Robot.drivetrain.getRightEncoderDistance() > targetDistance) {
                Robot.drivetrain.tankDrive(0.5, -0.4);
            }
            else {
                direction = "Left";
                targetDistance = Robot.drivetrain.getRightEncoderDistance() + 2 * DELTA_DISTANCE;
            }
        }else {
            if(Robot.drivetrain.getRightEncoderDistance() < targetDistance) {
                Robot.drivetrain.tankDrive(-0.4, 0.4);
            }
            else {
                Robot.drivetrain.tankDrive(0, 0);
            }
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