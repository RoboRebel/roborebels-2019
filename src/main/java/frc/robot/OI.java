/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.sensors.DPadTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  private final Joystick xboxController;
  private final JoystickButton oneLineTrackButton;
  private final JoystickButton wallDriveButton;
  private final JoystickButton driveStraightButton;
  private final JoystickButton threeLineTrackButton;
  private final JoystickButton acquireLineButton;
  private final JoystickButton PIDDriveButton;
  private final JoystickButton shootButton;
  private final DPadTrigger testPneumaticsButton;
  private final DPadTrigger testDrivetrainButton;
  private final DPadTrigger testShooterButton;
  private final DPadTrigger testSensorButton;

  public enum Side{
    Left, Right, Both
  }

  public OI(){
    xboxController = new Joystick(0);

    oneLineTrackButton = new JoystickButton(xboxController, 1);
    oneLineTrackButton.whileHeld(new OneSensorLineTrack());

    wallDriveButton = new JoystickButton(xboxController, 2);
    wallDriveButton.whileHeld(new DriveToWall());

    driveStraightButton = new JoystickButton(xboxController, 3);
    driveStraightButton.whileHeld(new GyroDriveStraight());

    threeLineTrackButton = new JoystickButton(xboxController, 4);
    threeLineTrackButton.whileHeld(new ThreeSensorLineTrack());

    acquireLineButton = new JoystickButton(xboxController, 5);
    acquireLineButton.whileHeld(new AcquireLine());

    PIDDriveButton = new JoystickButton(xboxController, 6);
    PIDDriveButton.whileHeld(new PIDDriveStraight());

    shootButton = new JoystickButton(xboxController, 7);
    shootButton.whileHeld(new Shoot());

    testPneumaticsButton = new DPadTrigger(xboxController, 0);
    testPneumaticsButton.whileActive(new TestSubsystem(Robot.pneumatics));

    testDrivetrainButton = new DPadTrigger(xboxController, 90);
    testDrivetrainButton.whileActive(new TestSubsystem(Robot.drivetrain));

    testShooterButton = new DPadTrigger(xboxController, 180);
    testShooterButton.whileActive(new TestSubsystem(Robot.shooter));

    testSensorButton = new DPadTrigger(xboxController, 270);
    testSensorButton.whileActive(new TestSubsystem(Robot.sensors));
  }

  public Joystick getXboxController(){
    return this.xboxController;
  }

  public void buzz(double intensity, Side side){
    switch (side){
      case Left:
        this.xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, intensity);
        this.xboxController.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        break;
      case Right:
        this.xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        this.xboxController.setRumble(GenericHID.RumbleType.kRightRumble, intensity);
        break;
      case Both:
        this.xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, intensity);
        this.xboxController.setRumble(GenericHID.RumbleType.kRightRumble, intensity);
    }
  }
}
