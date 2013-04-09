package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TiltToPotReading extends CommandBase {
    
    private int dest;
    
    public TiltToPotReading(int dest) {
        requires(ptilt);
        this.dest = dest;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    private boolean done = false;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (oi.shootercontroller.getRawButton(3)) {
            done = true;
            return;
        }
        
        int tilt = ptilt.getPotReading();
        SmartDashboard.putNumber("tilt angle", tilt);
        
        if (Math.abs(dest-tilt) < 3){
            ptilt.set(Relay.Value.kOff);
            done = true;
        } else {
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
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (!done) {
            return false;
        } else {
            done = false;
            return true;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
