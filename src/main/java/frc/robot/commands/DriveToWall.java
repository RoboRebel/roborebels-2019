package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToWall extends Command {

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if(Robot.sensors.getAvgLidarDistance() < 150) {
            Robot.drivetrain.tankDrive(0.2, 0.1);
        }else {
            Robot.drivetrain.tankDrive(0.5, 0.4);
        }
    }

    @Override
    protected boolean isFinished() {
        return Robot.sensors.getAvgLidarDistance() < 50;
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
