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
  private final Joystick ps4Controller;

  public enum Side{
    Left, Right, Both
  }

  public OI(){
    xboxController = new Joystick(RobotMap.XBOX_CONTROLLER_PORT);
    ps4Controller = new Joystick(RobotMap.PS4_CONTROLLER_PORT);

    JoystickButton oneLineTrackButton = new JoystickButton(xboxController, 7);
    oneLineTrackButton.whileHeld(new OneSensorLineTrack());

    JoystickButton wallDriveButton = new JoystickButton(xboxController, 2);
    wallDriveButton.whileHeld(new DriveToWall());

    JoystickButton driveStraightButton = new JoystickButton(xboxController, 3);
    driveStraightButton.whileHeld(new GyroDriveStraight());

    JoystickButton threeLineTrackButton = new JoystickButton(xboxController, 4);
    threeLineTrackButton.whileHeld(new ThreeSensorLineTrack());

    JoystickButton acquireLineButton = new JoystickButton(xboxController, 5);
    acquireLineButton.whileHeld(new AcquireLine());

    JoystickButton PIDDriveButton = new JoystickButton(xboxController, 6);
    PIDDriveButton.whileHeld(new PIDDriveStraight());

    JoystickButton suckButton = new JoystickButton(ps4Controller, 3);
    suckButton.whileHeld(new Suck());

    JoystickButton shootButton = new JoystickButton(ps4Controller, 1);
    shootButton.whileHeld(new Shoot());

    JoystickButton climbButton = new JoystickButton(ps4Controller, 2);
    climbButton.whileHeld(new Climb());

    JoystickButton hatchPickupButton = new JoystickButton(ps4Controller, 4);
    hatchPickupButton.whileHeld(new HatchPickup());

    DPadTrigger testPneumaticsButton = new DPadTrigger(ps4Controller, 0);
    testPneumaticsButton.whileActive(new TestSubsystem(Robot.pneumatics));

    DPadTrigger testDrivetrainButton = new DPadTrigger(xboxController, 90);
    testDrivetrainButton.whileActive(new TestSubsystem(Robot.drivetrain));

    DPadTrigger testShooterButton = new DPadTrigger(xboxController, 180);
    testShooterButton.whileActive(new TestSubsystem(Robot.shooter));

    DPadTrigger testSensorButton = new DPadTrigger(xboxController, 270);
    testSensorButton.whileActive(new TestSubsystem(Robot.sensors));
  }

  public Joystick getXboxController(){
    return this.xboxController;
  }

  public Joystick getPs4Controller(){
    return this.ps4Controller;
  }

  public Joystick getMainController(){
    return getPs4Controller();
  }

  public void buzz(Joystick controller, double intensity, Side side){
    switch (side){
      case Left:
        controller.setRumble(GenericHID.RumbleType.kLeftRumble, intensity);
        controller.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        break;
      case Right:
        controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        controller.setRumble(GenericHID.RumbleType.kRightRumble, intensity);
        break;
      case Both:
        controller.setRumble(GenericHID.RumbleType.kLeftRumble, intensity);
        controller.setRumble(GenericHID.RumbleType.kRightRumble, intensity);
    }
  }
}
