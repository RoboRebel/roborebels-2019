package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SendIt extends Command {

    @Override
    protected void execute() {
        Robot.drivetrain.tankDrive(0.75, 0.75);
    }

    @Override
    protected void end() {
        Robot.drivetrain.tankDrive(0,0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
