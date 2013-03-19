package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveControl;

/**
 * Subsystem that controls the drive-train of the robot. Many of the methods are
 * just encapsulating existing wpilibj library methods.
 * @author developer
 */
public class Drive extends Subsystem {

    public void initDefaultCommand() {
        setDefaultCommand(new DriveControl());
    }
    
    public void startEncoders(){
        RobotMap.leftenc.start();
        RobotMap.rightenc.start();
    }
    
    public void stopEncoders(){
        RobotMap.leftenc.stop();
        RobotMap.rightenc.stop();
    }
    
    public void resetEncoders(){
        RobotMap.leftenc.reset();
        RobotMap.rightenc.reset();
    }
    
    public void arcadeDrive(Joystick s) {
        RobotMap.robotdrive.arcadeDrive(s);
    }
    
    public void tankDrive(Joystick a, Joystick b) {
        tankDrive(a, b, false);
    }
    
    public void tankDrive(Joystick a, Joystick b, boolean inverse) {
        double aval = a.getRawAxis(2);
        double bval = b.getRawAxis(2);
        if (inverse){
            aval = -aval;
            bval = -bval;
        }
        RobotMap.robotdrive.tankDrive(aval, bval);
    }
    
    public void arcadeDrive(double movement, double turn) {
        RobotMap.robotdrive.arcadeDrive(movement, turn);
    }
}
