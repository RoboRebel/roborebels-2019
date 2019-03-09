package frc.robot.commands;

import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleVisionRing extends Command {
    @Override
    protected void execute() {
        Robot.sensors.setVisionRing(!Robot.sensors.getVisionRing());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
