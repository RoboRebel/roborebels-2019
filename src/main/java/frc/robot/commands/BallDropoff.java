package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

import javax.management.timer.Timer;

public class BallDropoff extends Command {
    public BallDropoff() {
        this.requires(Robot.shooter);
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    @Override
    protected void initialize() {
        setTimeout(1000);
    }
    @Override
    protected void execute() {
        Robot.shooter.set(1.0);
    }
    @Override
    protected void end() {
        Robot.shooter.set(0);
    }
}
