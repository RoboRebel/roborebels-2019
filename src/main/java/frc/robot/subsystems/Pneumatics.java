package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Pneumatics extends Subsystem {
    private final Solenoid frontRightClimbing;
    private final Solenoid frontLeftClimbing;
    private final Solenoid backRightClimbing;
    private final Solenoid backLeftClimbing;

    private final Solenoid hatchHolding;
    private final Solenoid hatchPushing;

    public Pneumatics(){
        frontRightClimbing = new Solenoid(RobotMap.FRONT_RIGHT_CLIMBING_SOLENOID);
        frontLeftClimbing = new Solenoid(RobotMap.FRONT_LEFT_CLIMBING_SOLENOID);
        backRightClimbing = new Solenoid(RobotMap.BACK_RIGHT_CLIMBING_SOLENOID);
        backLeftClimbing = new Solenoid(RobotMap.BACK_LEFT_CLIMBING_SOLENOID);

        hatchHolding = new Solenoid(RobotMap.HATCH_HOLDING_SOLENOID);
        hatchPushing = new Solenoid(RobotMap.HATCH_PUSHING_SOLENOID);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
