package frc.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HatchPickup extends CommandGroup {
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        addSequential(new ExtendActuator1());
        addSequential(new ExtendActuator2());
        addSequential(new ContractActuator1());
    }
}
