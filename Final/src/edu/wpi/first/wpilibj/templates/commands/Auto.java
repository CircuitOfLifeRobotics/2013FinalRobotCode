package edu.wpi.first.wpilibj.templates.commands;

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
        stage = 0;
        disc = 0;
        enabled = false;
        launcher.fs.disable();
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public void start() {
        super.start();
        enable();
        stage = 0;
        disc = 0;
        starttime = System.currentTimeMillis();
    }
    
    public static final int SPINUP = 0, FEED = 1, WAIT_FOR_FEED = 2;
    private int stage;
    private int disc;
    private long starttime;
    
    private void nextStage() {
        stage = (stage+1)%3;
        starttime = System.currentTimeMillis();
    }

    protected void execute() {
        
        if (!isEnabled()) {
            launcher.set(0);
            return;
        }
        
        launcher.set(-1);
        
        if (stage == SPINUP) {
            if (System.currentTimeMillis() - starttime > (disc == 1 ? 3000 : 3500)) {
                nextStage();
            }
        } else if (stage == FEED) {
            launcher.fs.execute(true);
            nextStage();
        } else if (stage == WAIT_FOR_FEED) {
            launcher.fs.execute(false);
            if (launcher.fs.isReady()) {
                nextStage();
                disc += 1;
                if (disc > 2) {
                    disable();
                }
            }
            if (System.currentTimeMillis() - starttime > 5000) {
                disable();
            }
        }
        
    }

    //run once
    protected boolean isFinished() {
        return !isEnabled();
    }

    protected void end() {
    }

    protected void interrupted() {
        disable();
    }
}
