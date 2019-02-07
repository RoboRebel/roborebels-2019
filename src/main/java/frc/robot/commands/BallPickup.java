package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallPickup extends Command {

    public BallPickup(){
        requires(Robot.shooter);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
