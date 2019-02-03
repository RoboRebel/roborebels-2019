/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithGamepad;
import frc.robot.interfaces.Testable;

import java.util.ArrayList;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem implements Testable {
  private SpeedController left, right;
  private Encoder leftEncoder, rightEncoder;
  private ArrayList<WPI_TalonSRX> testList;

  public Drivetrain(){
    testList = new ArrayList<>();

    WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_CANTALON);
    testList.add(frontLeft);
    WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_CANTALON);
    testList.add(backLeft);
    left = new SpeedControllerGroup(frontLeft, backLeft);

    WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_CANTALON);
    testList.add(frontRight);
    WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_CANTALON);
    testList.add(backRight);
    right = new SpeedControllerGroup(frontRight, backRight);
    right.setInverted(true);

    leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B);
    leftEncoder.setPIDSourceType(PIDSourceType.kRate);
    leftEncoder.setDistancePerPulse(-(7.0 * Math.PI)/360.0);

    rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B);
    rightEncoder.setPIDSourceType(PIDSourceType.kRate);
    rightEncoder.setDistancePerPulse((7.0 * Math.PI)/360.0);

  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    this.left.set(leftSpeed);
    this.right.set(rightSpeed);
  }

  public double getLeftEncoderSpeed(){
    return leftEncoder.getRate();
  }

  public double getRightEncoderSpeed(){
    return rightEncoder.getRate();
  }

  public double getRightEncoderDistance(){
    return rightEncoder.getDistance();
  }

  public double getLeftEncoderDistance(){
    return leftEncoder.getDistance();
  }

  public Encoder getLeftEncoder(){
    return leftEncoder;
  }

  public Encoder getRightEncoder(){
    return rightEncoder;
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
