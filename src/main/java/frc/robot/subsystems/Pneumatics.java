package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.interfaces.Testable;

import java.util.ArrayList;

public class Pneumatics extends Subsystem implements Testable {

    private final Solenoid frontRightClimbing;
    private final Solenoid frontLeftClimbing;
    private final Solenoid backRightClimbing;
    private final Solenoid backLeftClimbing;

    private final Solenoid hatchHolding;
    private final Solenoid hatchPushing;

    private ArrayList<Solenoid> testList;

    public Pneumatics(){
        testList = new ArrayList<>();
        frontRightClimbing = new Solenoid(RobotMap.FRONT_RIGHT_CLIMBING_SOLENOID);
        testList.add(frontRightClimbing);
        frontLeftClimbing = new Solenoid(RobotMap.FRONT_LEFT_CLIMBING_SOLENOID);
        testList.add(frontLeftClimbing);
        backRightClimbing = new Solenoid(RobotMap.BACK_RIGHT_CLIMBING_SOLENOID);
        testList.add(backRightClimbing);
        backLeftClimbing = new Solenoid(RobotMap.BACK_LEFT_CLIMBING_SOLENOID);
        testList.add(backLeftClimbing);

        hatchHolding = new Solenoid(RobotMap.HATCH_HOLDING_SOLENOID);
        testList.add(hatchHolding);
        hatchPushing = new Solenoid(RobotMap.HATCH_PUSHING_SOLENOID);
        testList.add(hatchPushing);
    }

    public void setFrontClimb(boolean val){
        frontLeftClimbing.set(val);
        frontRightClimbing.set(val);
    }

    public void setBackClimb(boolean val){
        backLeftClimbing.set(val);
        backRightClimbing.set(val);
    }

    public void setHatchPushing(boolean val){
        hatchPushing.set(val);
    }

    public void setHatchHolding(boolean val){
        hatchHolding.set(val);
    }

    public void testUnit(int index, boolean set){
        testList.get(index).set(set);
    }

    public void resetTest(){
        for(Solenoid piston : testList)
            piston.set(false);
    }

    public int getUnitAmount(){
        return testList.size();
    }

    public Subsystem getRobotSubsystem(){
        return this;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
