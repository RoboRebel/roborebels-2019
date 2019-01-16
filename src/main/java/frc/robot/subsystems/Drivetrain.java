/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithGamepad;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  private SpeedController left, right;

  public Drivetrain(){
    left = new SpeedControllerGroup(
      new PWMVictorSPX(RobotMap.FRONT_LEFT_VICTOR), 
      new PWMVictorSPX(RobotMap.BACK_LEFT_VICTOR));
    right = new SpeedControllerGroup(
      new PWMVictorSPX(RobotMap.FRONT_RIGHT_VICTOR), 
      new PWMVictorSPX(RobotMap.BACK_RIGHT_VICTOR));
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    this.left.set(leftSpeed);
    this.right.set(-rightSpeed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithGamepad());
  }
}
