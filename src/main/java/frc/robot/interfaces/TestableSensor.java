package frc.robot.interfaces;

public interface TestableSensor {
    void resetTest();
    void test();
    String getStatus();
}
