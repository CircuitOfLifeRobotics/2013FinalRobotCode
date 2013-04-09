
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.TiltToPotReading;

/**
 * This class holds all of the objects for operator interaction, such as Joysticks
 * and Joystick buttons. It also returns input from such objects.
 */
public class OI {
    public Joystick shootercontroller = new Joystick(1);
    public final int POWER_AXIS = 3, FEED_BUTTON = 1;
    public final int TILT_UP_BUTTON = 6, TILT_DOWN_BUTTON = 5;
    
    public Joystick drivercontroller = new Joystick(2);
    public final int FORWARD_AXIS = 2, TURN_AXIS = 4;
    
    public JoystickButton climbbutton = new JoystickButton(shootercontroller, 7),
            aimbutton = new JoystickButton(shootercontroller, 8),
            loadbutton = new JoystickButton(shootercontroller, 4);
    public static final int CLIMB_ANGLE = 294, AIM_ANGLE = 567, LOAD_ANGLE = 531;
    
    public OI() {
        climbbutton.whenPressed(new TiltToPotReading(CLIMB_ANGLE));
        aimbutton.whenPressed(new TiltToPotReading(AIM_ANGLE));
        loadbutton.whenPressed(new TiltToPotReading(LOAD_ANGLE));
    }
    
    public DriverInput getDriverInput() {
        return new DriverInput(
                drivercontroller.getRawAxis(FORWARD_AXIS),
                drivercontroller.getRawAxis(TURN_AXIS)
                );
    }
    
    public ShooterInput getShooterInput() {
        return new ShooterInput(
                shootercontroller.getRawAxis(POWER_AXIS),
                shootercontroller.getRawButton(FEED_BUTTON)
                );
    }
    
    public TiltInput getTiltInput() {
        return new TiltInput(
                shootercontroller.getRawButton(TILT_UP_BUTTON),
                shootercontroller.getRawButton(TILT_DOWN_BUTTON)
                );
    }
    
    
    /**
     * Utility class for holding input from the driver
     */
    public static class DriverInput {
        public DriverInput(double fwd, double t) {
            forward = fwd;
            turn = t;
        }
        public double forward;
        public double turn;
    }
    
     /**
     * Utility class for holding input from the shooter
     */
    public static class ShooterInput {
        public ShooterInput(double power, boolean feed) {
            this.power = power;
            this.feed = feed;
        }
        public double power;
        public boolean feed;
    }
    
    /**
     * Utility class for holding input for the tilt subsystem
     */
    public static class TiltInput {
        public TiltInput(boolean up, boolean down) {
            this.up = up;
            this.down = down;
        }
        public boolean shouldTilt() {
            return up != down;
        }
        public Relay.Value getDirection() {
            if (shouldTilt()) {
                return (up) ? Relay.Value.kReverse : Relay.Value.kForward;
            } else {
                return Relay.Value.kOff;
            }
        }
        public boolean up;
        public boolean down;
    }
}

