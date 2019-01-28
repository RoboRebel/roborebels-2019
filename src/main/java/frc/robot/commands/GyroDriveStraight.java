package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GyroDriveStraight extends Command {
    private PIDController pidController;
    private RotationOutput rotationOutput;

    private static double K_P = 0.01;
    private static double K_I = 0.001;
    private static double K_D = 0.0;

    public GyroDriveStraight(){
        requires(Robot.drivetrain);
        rotationOutput = new RotationOutput();
        pidController = new PIDController(K_P,K_I,K_D, Robot.sensors.getNavx(),rotationOutput);
        pidController.setOutputRange(-0.25,0.25);
        pidController.setInputRange(-180.0,180.0);
        pidController.setContinuous();
    }
    @Override
    protected void initialize() {
        pidController.setSetpoint(Robot.sensors.getNavXYaw());
        pidController.enable();
    }

    @Override
    protected void execute() {
        if(rotationOutput.getOutput() < 0)
            Robot.drivetrain.tankDrive(0.5, 0.5 + rotationOutput.getOutput());
        else
            Robot.drivetrain.tankDrive(0.5 + rotationOutput.getOutput(), 0.5);
        System.out.println(rotationOutput.getOutput());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }

    public class RotationOutput implements PIDOutput {
        private double output = 0.0;
        @Override
        public void pidWrite(double output) {
            this.output = output;
        }
        public double getOutput(){
            return output;
        }
    }
}