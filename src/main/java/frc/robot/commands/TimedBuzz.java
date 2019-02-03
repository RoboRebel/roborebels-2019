package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TimedBuzz extends Command {
    private Timer timer;
    private double time;

    public TimedBuzz(double seconds){
        timer = new Timer();
        time = seconds;
    }

    @Override
    protected void initialize() {
        timer.start();
    }

    @Override
    protected void execute() {
        Robot.oi.buzz(1.0, OI.Side.Both);
    }

    @Override
    protected boolean isFinished() {
        return timer.hasPeriodPassed(time);
    }
}
