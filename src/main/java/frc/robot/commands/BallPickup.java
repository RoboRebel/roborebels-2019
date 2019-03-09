package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallPickup extends Command {
    public BallPickup() {
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
        Robot.shooter.set(-0.5);
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
