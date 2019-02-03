package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class BuzzBuzz extends Command {
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(Robot.sensors.getPhotoswitchStatus(1)){
            Robot.oi.buzz(1.0, OI.Side.Both);
        }else  if(Robot.sensors.getPhotoswitchStatus(0)){
            Robot.oi.buzz(1.0, OI.Side.Right);
        }else if(Robot.sensors.getPhotoswitchStatus(2)){
            Robot.oi.buzz(1.0, OI.Side.Left);
        }else {
            Robot.oi.buzz(0, OI.Side.Both);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}