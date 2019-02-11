package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Climb extends Command {
    public Climb(){
        this.requires(Robot.pneumatics);
    }

    @Override
    protected void execute() {
        Robot.pneumatics.setBackClimb(Robot.oi.getPs4Controller().getRawButton(5));
        Robot.pneumatics.setFrontClimb(Robot.oi.getPs4Controller().getRawButton(6));
    }

    @Override
    protected void end() {
        Robot.pneumatics.setFrontClimb(false);
        Robot.pneumatics.setBackClimb(false);
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
