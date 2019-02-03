package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToWall extends Command {
    public DriveToWall(){
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if(Robot.sensors.getAvgLidarDistance() < 100) {
            Robot.drivetrain.tankDrive(0.2, 0.1);
        }else {
            Robot.drivetrain.tankDrive(0.7, 0.7);
        }
    }

    @Override
    protected boolean isFinished() {
        return Robot.sensors.getAvgLidarDistance() < 30;
    }

    @Override
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }


}
