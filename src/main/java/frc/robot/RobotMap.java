/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static final int FRONT_RIGHT_CANTALON = 3;
  public static final int BACK_RIGHT_CANTALON = 2;
  public static final int FRONT_LEFT_CANTALON = 1;
  public static final int BACK_LEFT_CANTALON = 0;

  public static final boolean RIGHT_ENCODER_FRONT = false;
  public static final boolean LEFT_ENCODER_FRONT = true;

  public static final int TOP_SHOOTER_CANVICTOR = 0;
  public static final int BOTTOM_SHOOTER_CANVICTOR = 1;

  public static final int FRONT_RIGHT_CLIMBING_SOLENOID = 0;
  public static final int FRONT_LEFT_CLIMBING_SOLENOID = 3;
  public static final int BACK_RIGHT_CLIMBING_SOLENOID = 1;
  public static final int BACK_LEFT_CLIMBING_SOLENOID = 2;
  public static final int HATCH_HOLDING_SOLENOID = 4;
  public static final int HATCH_PUSHING_SOLENOID = 5;

  //left, center, right
  public static final int[] LIGHT_SENSOR_DIO_PORTS = {7, 8, 9};
  public static final int LIGHT_SENSOR_1_DIO_PORT = 0;
  public static final int LIGHT_SENSOR_2_DIO_PORT = 5;
  public static final int LIGHT_SENSOR_3_DIO_PORT = 6;

  public static final int ULTRASONIC_CHANNEL = 1;
}
