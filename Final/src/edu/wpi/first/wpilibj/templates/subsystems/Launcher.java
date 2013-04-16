package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.LauncherControl;

/**
 * This class controls the launcher system installed on the robot. This class
 * contains a state machine that will start and stop the feeding motor based upon
 * operator input and a microswitch installed on the bot which spins the feeder
 * in one revolution per press.
 */
public class Launcher extends Subsystem {
    
    public FeederState fs = new FeederState();
    
    public void initDefaultCommand() {
        setDefaultCommand(new LauncherControl());
    }
    
    public void set(double speed) {
        RobotMap.shootjag.set(speed);
    }
    
    /**
     * This method is called to update the state machine based feeder.
     * @param t - whether the operator has the feeding button down or not.
     */
    public void triggerFeeder(boolean t) {
        fs.execute(t);
    }
    
    public class FeederState {
        
        //states
        private final byte S_READY = 0, S_WAIT_OFF = 1, S_WAIT_ON = 2;
        private final byte STATE_COUNT = (byte)3;
        private byte state = 0;
        
        public FeederState() {
        }
        
        public boolean isReady() {
            return state == S_READY;
        }
        public boolean isRunning() {
            return !isReady();
        }
        
        public void disable() {
            RobotMap.feedspike.set(Relay.Value.kOff);
            state = 0;
        }
        
        private void nextState() {
            state = (byte)((state + (byte)1) % STATE_COUNT);
        }
        
        /**
         * updates the state
         * @param btn - whether the operator has the button down.
         */
        public void execute(boolean btn) {
            boolean fb;
            switch (state) {
                case S_READY:
                    if (btn) {
                        nextState();
                    }
                    break;
                case S_WAIT_OFF:
                    fb = RobotMap.feedbtn.get();
                    SmartDashboard.putBoolean("feeder button", fb);
                    if (!fb) {
                        nextState();
                    }
                    break;
                case S_WAIT_ON:
                    fb = RobotMap.feedbtn.get();
                    SmartDashboard.putBoolean("feeder button", fb);
                    if (fb) {
                        nextState();
                    }
                    break;
                default:
                    break;
            }
            if (state == S_READY) {
                RobotMap.feedspike.set(Relay.Value.kOff);
            } else {
                RobotMap.feedspike.set(Relay.Value.kForward);
            }
        }
        
    }
}
