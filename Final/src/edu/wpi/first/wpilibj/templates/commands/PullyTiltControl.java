package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI.TiltInput;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 * Controls the tilt subsystem of the robot. (default command)
 */
public class PullyTiltControl extends CommandBase {
    
    public PullyTiltControl() {
        requires(ptilt);
    }

    protected void initialize() {
        //While this method is blank, it is required 
    }

    protected void execute() {
        //Creates TiltInput object ti
        TiltInput ti = oi.getTiltInput();
        //Puts values read from potentiometer into Smart Dashboard
        SmartDashboard.putBoolean("ptilt up", ti.up);
        SmartDashboard.putBoolean("ptilt down", ti.down);
        SmartDashboard.putNumber("tilt angle", RobotMap.tiltpot.getValue());
        
        RobotMap.tiltrelay.set(ti.getDirection());
        
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
