package frc.robot.interfaces;

import edu.wpi.first.wpilibj.command.Subsystem;

public interface Testable {
    //implementing classes should have a list of testable units, with status on or off

    void testUnit(int index, boolean status);

    void resetTest();

    int getUnitAmount();

    Subsystem getRobotSubsystem();
}
