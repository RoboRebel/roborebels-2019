package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SendIt extends Command {

    double speed;

    public SendIt(){
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        speed = 0.0;
    }

    @Override
    protected void execute() {
        if(speed < 0.75)
            speed += 0.05;
        System.out.println(speed);
        Robot.drivetrain.tankDrive(speed, speed);
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
