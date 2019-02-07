package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallDropoff extends Command {

    public BallDropoff(){
        requires(Robot.shooter);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
