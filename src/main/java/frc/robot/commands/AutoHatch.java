package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoHatch extends Command {
    private static final int DISTANCE_FROM_WALL = 30;

    private boolean started;

    public enum HatchCommandType{
        Pickup, Dropoff
    }

    private HatchCommandType type;
    private Command hatchCommand;
    private Command lineTrackCommand;

    public AutoHatch(HatchCommandType type){
        this.type = type;
        this.requires(Robot.drivetrain);
        this.requires(Robot.pneumatics);

        if(type == HatchCommandType.Pickup)
            hatchCommand = new HatchPickup();
        else
            hatchCommand = new HatchDropoff();
    }

    @Override
    protected void initialize() {
        lineTrackCommand.start();
        started = false;
    }

    @Override
    protected void execute() {
        if(Robot.sensors.getAvgLidarDistance() < DISTANCE_FROM_WALL && !started){
            lineTrackCommand.cancel();
            hatchCommand.start();
            started = false;
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        lineTrackCommand.cancel();
        hatchCommand.cancel();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
