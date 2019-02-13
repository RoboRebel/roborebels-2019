package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HatchPickup extends Command {
    Timer timer;

    public HatchPickup(){
        requires(Robot.pneumatics);
        timer = new Timer();
    }
    @Override
    protected void initialize() {
        Robot.pneumatics.setHatchHolding(false);
        Robot.pneumatics.setHatchPushing(false);
        timer.start();
    }

    @Override
    protected void execute() {
        Robot.pneumatics.setHatchPushing(true);
        if(timer.get() >= 0.25)
            Robot.pneumatics.setHatchHolding(true);
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
