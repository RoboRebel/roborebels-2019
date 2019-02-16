/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithGamepad;
import frc.robot.interfaces.Testable;

import java.util.ArrayList;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem implements Testable {
  private SpeedController left, right;
  private ArrayList<WPI_TalonSRX> testList;
  private WPI_TalonSRX frontLeft, frontRight, backLeft, backRight;

  public Drivetrain(){
    testList = new ArrayList<>();

    frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_CANTALON);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontLeft.configOpenloopRamp(0.1);
    testList.add(frontLeft);
    backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_CANTALON);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.configOpenloopRamp(0.1);
    testList.add(backLeft);
    left = new SpeedControllerGroup(frontLeft, backLeft);

    frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_CANTALON);
    frontRight.setNeutralMode(NeutralMode.Brake);
    frontRight.configOpenloopRamp(0.1);
    testList.add(frontRight);
    backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_CANTALON);
    backRight.setNeutralMode(NeutralMode.Brake);
    backRight.configOpenloopRamp(0.1);
    testList.add(backRight);
    right = new SpeedControllerGroup(frontRight, backRight);
    right.setInverted(true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    this.left.set(leftSpeed);
    this.right.set(rightSpeed);
  }

  public double getLeftEncoderSpeed(){
    return getLeftEncoderTalon().getSelectedSensorVelocity();
  }

  public double getRightEncoderSpeed(){
    return getRightEncoderTalon().getSelectedSensorVelocity();
  }

  public double getRightEncoderDistance(){
    return getRightEncoderTalon().getSelectedSensorPosition();
  }

  public double getLeftEncoderDistance(){
    return getLeftEncoderTalon().getSelectedSensorPosition();
  }

  public WPI_TalonSRX getLeftEncoderTalon(){
    return RobotMap.LEFT_ENCODER_FRONT ? frontLeft : backLeft;
  }

  public WPI_TalonSRX getRightEncoderTalon(){
    return RobotMap.RIGHT_ENCODER_FRONT ? frontRight : backRight;
  }

  public SpeedController getLeft(){
    return left;
  }

  public SpeedController getRight() {
    return right;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithGamepad());
  }

  @Override
  public void testUnit(int index, boolean status) {
    testList.get(index).set(status ? 0.5 : 0);
  }

  @Override
  public void resetTest() {
    for(WPI_TalonSRX talonSRX : testList){
      talonSRX.set(0);
    }
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
