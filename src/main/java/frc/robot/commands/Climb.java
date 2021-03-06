package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ControlMap;
import frc.robot.Robot;

public class Climb extends Command {
    public Climb(){
        this.requires(Robot.pneumatics);
    }

    @Override
    protected void initialize() {
        Robot.pneumatics.setClimbing(true);
    }

    @Override
    protected void execute() {
        Robot.pneumatics.setBackClimb(Robot.oi.getController(ControlMap.CLIMB_CONTROLLER_PORT).getRawButton(ControlMap.CLIMB_REAR_DEPLOY));
        Robot.pneumatics.setFrontClimb(Robot.oi.getController(ControlMap.CLIMB_CONTROLLER_PORT).getRawButton(ControlMap.CLIMB_FRONT_DEPLOY));
    }

    @Override
    protected void end() {
        Robot.pneumatics.setFrontClimb(false);
        Robot.pneumatics.setBackClimb(false);
        Robot.pneumatics.setClimbing(false);
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
