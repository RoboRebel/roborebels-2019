/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import static frc.robot.ControlMap.*;
import frc.robot.Robot;
import org.opencv.core.Mat;

public class DriveWithGamepad extends Command {
  private Joystick controller;
  public DriveWithGamepad() {
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    controller = Robot.oi.getController(DRIVE_CONTROLLER_PORT);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double left = (-trim(controller.getRawAxis(LEFT_STICK_Y_AXIS))) + (trim(controller.getRawAxis(RIGHT_TRIGGER)) - trim(controller.getRawAxis(LEFT_TRIGGER)));
    double right = (-trim(controller.getRawAxis(RIGHT_STICK_Y_AXIS))) + (trim(controller.getRawAxis(RIGHT_TRIGGER)) - trim(controller.getRawAxis(LEFT_TRIGGER)));
    if(Robot.pneumatics.isClimbing() || Robot.oi.getController(DRIVE_CONTROLLER_PORT).getRawButton(DRIVE_PRECISION_BUTTON)){
      left = left/3.0;
      right = right/3.0;
    }
    Robot.drivetrain.tankDrive(trim(left), trim(right));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.tankDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  private double trim(double x){
    if(x == 0)
      return 0.0;
    boolean neg = x < 0.0;
    x = Math.abs(x);
    if(x > 1.0)
      x = 1.0;
    if (x < 0.2)
      x = 0.0;
    return neg ? -x : x;
  }
}
