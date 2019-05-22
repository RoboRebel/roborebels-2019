package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.OneSensorLineTrack;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ControlMap {
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int RIGHT_BUMPER = 6;
    public static final int LEFT_BUMPER = 5;
    public static final int BACK_BUTTON = 7;
    public static final int START_BUTTON = 8;
    public static final int LEFT_STICK = 9;
    public static final int RIGHT_STICK = 10;
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;
    public static final int LEFT_STICK_X_AXIS = 0;
    public static final int LEFT_STICK_Y_AXIS = 1;
    public static final int RIGHT_STICK_X_AXIS = 4;
    public static final int RIGHT_STICK_Y_AXIS = 5;
    public static final int DIRECTION_PAD = 6;

    public static final int PS4_CONTROLLER_PORT = 1;
    public static final int XBOX_CONTROLLER_PORT = 0;

    public static final int DRIVE_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int DRIVE_PRECISION_BUTTON = LEFT_BUMPER;
    public static final int BUZZ_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;

    public static final int LINE_TRACK_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int LINE_TRACK_CONTROLLER_BUTTON = START_BUTTON;

    public static final int SUCK_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int SUCK_CONTROLLER_BUTTON = A_BUTTON;

    public static final int SHOOT_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int SHOOT_CONTROLLER_BUTTON = B_BUTTON;

    public static final int CLIMB_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int CLIMB_CONTROLLER_BUTTON = BACK_BUTTON;
    public static final int CLIMB_FRONT_DEPLOY = RIGHT_BUMPER;
    public static final int CLIMB_REAR_DEPLOY = LEFT_BUMPER;

    public static final int HATCH_PICKUP_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int HATCH_PICKUP_CONTROLLER_BUTTON = X_BUTTON;

    public static final int SEND_IT_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int SEND_IT_CONTROLLER_BUTTON = RIGHT_STICK;

    public static final int HATCH_DROPOFF_CONTROLLER_PORT = XBOX_CONTROLLER_PORT;
    public static final int HATCH_DROPOFF_CONTROLLER_BUTTON = Y_BUTTON;
    public static final int HATCH_DROPOFF_RELEASE_BUTTON = RIGHT_BUMPER;
}
