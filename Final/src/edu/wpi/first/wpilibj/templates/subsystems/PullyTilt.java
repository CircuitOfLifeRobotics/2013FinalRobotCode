package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.PullyTiltControl;

/**
 * Controls the Tilt mechanisms on the bot.
 * @author developer
 */
public class PullyTilt extends Subsystem {
    
    public void initDefaultCommand() {
        setDefaultCommand(new PullyTiltControl());
    }
    
    public void set(Relay.Value rval) {
        RobotMap.tiltrelay.set(rval);
    }
    
    public int getPotReading() {
        return RobotMap.tiltpot.getValue();
    }
}
