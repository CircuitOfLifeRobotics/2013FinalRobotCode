package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * This class is the script used for autonomous. When execute runs, a thread is
 * launched that spins the shooter and flings the discs.
 */
public class Auto extends CommandBase {

    public Auto() {
        requires(launcher);
    }

    protected void initialize() {
    }
    private boolean enabled = false;

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public void start() {
        super.start();
        enable();
    }

    protected void execute() {
        // exit if the thread is running
        if (enabled) { return; }
        
        // set enabled
        enable();
        new Thread(new Runnable() {
            public void run() {
                // run the shooter for 3 flying discs
                for (int c = 0; c < 3 && enabled; c++) {
                    
                    //// spinup period ////
                    for (int t = 0; t < 250 && enabled; t++) {
                        launcher.set(-1);
                        Timer.delay(0.01);
                    }
                    
                    //// launch period ////
                    // starts pushing the flying discs
                    launcher.fs.execute(true);
                    long starttime = System.currentTimeMillis();
                    while (launcher.fs.isRunning() && enabled) {
                        launcher.set(-1);
                        launcher.fs.execute(false);
                        
                        // check for the disc being stuck in the bot
                        if (System.currentTimeMillis() - starttime > 4000) {
                            disable();
                        }
                        Timer.delay(.01);
                    }
                }
                launcher.set(0);
                disable();
            }
        }).start();

    }

    //run once
    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        disable();
    }
}
