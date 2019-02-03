package frc.robot.sensors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DPadTrigger extends Trigger {
    private int targetDeg;
    private Joystick gamepad;

    public DPadTrigger(Joystick gamepad, int degrees){
        this.targetDeg = degrees;
        this.gamepad = gamepad;
    }

    @Override
    public boolean get() {
        return gamepad.getPOV() == targetDeg;
    }
}
