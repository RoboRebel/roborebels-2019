package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.ControlMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HatchDropoff extends Command {

    public HatchDropoff(){
        requires(Robot.pneumatics);
    }
    @Override
    protected void initialize() {
        Robot.pneumatics.setHatchPushing(false);
    }

    @Override
    protected void execute() {
        Robot.drivetrain.tankDrive(0.2, 0.2);
        Robot.pneumatics.setHatchPushing(true);
        if(Robot.oi.getController(ControlMap.HATCH_DROPOFF_CONTROLLER_PORT).getRawButton(ControlMap.HATCH_DROPOFF_RELEASE_BUTTON)){
            Robot.pneumatics.setHatchHolding(false);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.pneumatics.setHatchPushing(false);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
