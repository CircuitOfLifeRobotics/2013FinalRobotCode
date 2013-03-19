package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.Auto;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 * Main class of the robot. Most of the methods below are called upon entering/exiting
 * various stages of the match.
 * 
 * Autonomous is run from this class.
 * 
 * camera light is set in this class.
 * 
 */
public class FinalBot extends IterativeRobot {

    // Autonomous command object that is called to enter/exit autonomous
    private Auto auto;

    /**
     * Called when the robot first starts
     */
    public void robotInit() {
        auto = new Auto();
        CommandBase.init();
    }

    /**
     * Called at the beginning of autonomous and starts the auto command.
     */
    public void autonomousInit() {
        auto.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Called at the beginning of the tele-operated period and shuts off autonomous.
     */
    public void teleopInit() {
        // Disable autonomous
        auto.disable();
        auto.cancel();
        
        // Turns the light on
	RobotMap.camlight.set(Relay.Value.kForward);
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void disabledInit(){
        // Turns the light off
        RobotMap.camlight.set(Relay.Value.kOff);
        
        // Disable autonomous
        auto.disable();
        auto.cancel();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
