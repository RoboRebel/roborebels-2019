package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.interfaces.Testable;

import java.util.ArrayList;

public class Shooter extends Subsystem implements Testable {
    private WPI_VictorSPX top;
    private WPI_VictorSPX bottom;
    private ArrayList<WPI_VictorSPX> testList;

    public Shooter(){
        testList = new ArrayList<>();

        top = new WPI_VictorSPX(RobotMap.TOP_SHOOTER_CANVICTOR);
        top.setInverted(false);
        testList.add(top);

        bottom = new WPI_VictorSPX(RobotMap.BOTTOM_SHOOTER_CANVICTOR);
        bottom.setInverted(true);
        testList.add(bottom);
    }

    public void set(double speed){
        top.set(speed);
        bottom.set(speed);
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void testUnit(int index, boolean status) {
        testList.get(index).set(status ? 1.0 : 0);
    }

    @Override
    public void resetTest() {
        for(WPI_VictorSPX victorSPX : testList)
            victorSPX.set(0);
    }

    @Override
    public int getUnitAmount() {
        return testList.size();
    }

    @Override
    public Subsystem getRobotSubsystem() {
        return this;
    }
}
