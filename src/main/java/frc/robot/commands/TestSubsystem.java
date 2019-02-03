package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.interfaces.Testable;

public class TestSubsystem extends Command {
    private int unitNum;
    private Timer timer;
    private Testable subsystem;

    public TestSubsystem(Testable subsystem){
        requires(subsystem.getRobotSubsystem());
        this.subsystem = subsystem;
        timer = new Timer();
    }

    @Override
    protected void initialize() {
        unitNum = 0;
        (new TimedBuzz(0.2)).start();
        timer.start();
    }

    @Override
    protected void execute() {
        if(timer.hasPeriodPassed(1)) {
            this.subsystem.testUnit(unitNum, false);
            (new TimedBuzz(0.2)).start();
            if(unitNum < this.subsystem.getUnitAmount() - 1) {
                unitNum++;
            }else{
                unitNum = 0;
            }
        }
        this.subsystem.testUnit(unitNum, true);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.subsystem.resetTest();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
