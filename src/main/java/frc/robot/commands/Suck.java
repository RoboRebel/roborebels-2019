package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Suck extends Command {
    public Suck(){
        this.requires(Robot.shooter);
    }

    @Override
    protected void execute() {
        Robot.shooter.set(-0.5);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.shooter.set(0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
