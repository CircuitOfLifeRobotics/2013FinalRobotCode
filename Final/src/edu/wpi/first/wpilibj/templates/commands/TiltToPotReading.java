/**
 * This class controls the tilt subsystem and tilts the launcher of the robot to
 * a desired angle. The driver can press a button on the Xbox controller
 * and the shooter will tilt. The button mappings are as follows:
 * Y: Tilts to feeding angle
 * Start: tilts to shooter angle (from back of pyramid)
 * Select: begins climbing sequence (lowers launcher until robot is lifted)
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TiltToPotReading extends CommandBase {
    
    private int dest;
    
    public TiltToPotReading(int dest) {
        requires(ptilt);
        this.dest = dest;
    }

    protected void initialize() {
    }

    private boolean done = false;
    
    protected void execute() {
        //Cancels command if user presses X 
        if (oi.shootercontroller.getRawButton(3)) {
            done = true;
            return;
        }
        
        int tilt = ptilt.getPotReading();
        SmartDashboard.putNumber("tilt angle", tilt);
        //If the value of the potentiometer is within +- 3, stop tilting
        if (Math.abs(dest-tilt) < 3){
            ptilt.set(Relay.Value.kOff);
            done = true;
        } else {
            //If tilts too high, go down
            if (dest-tilt > 0) {
                ptilt.set(Relay.Value.kReverse);
            } else {
                ptilt.set(Relay.Value.kForward);
            }
        }
    }

    public int getDestinationValue() {
        return dest;
    }
    //Checks to see if tilting is finished
    protected boolean isFinished() {
        if (!done) {
            return false;
        } else {
            done = false;
            return true;
        }
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
