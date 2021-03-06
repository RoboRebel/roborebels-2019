package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Shoot extends Command {
    public Shoot(){
        this.requires(Robot.shooter);
//        this.requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        Robot.shooter.set(1.0);
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
