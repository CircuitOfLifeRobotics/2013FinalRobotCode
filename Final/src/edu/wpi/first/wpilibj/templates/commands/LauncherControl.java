package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI.ShooterInput;

/**
 * Controls the Launcher subsystem. (default command)
 */
public class LauncherControl extends CommandBase {
    
    public LauncherControl() {
        requires(launcher);
    }

    protected void initialize() {
    }

    protected void execute() {
        ShooterInput si = oi.getShooterInput();
        launcher.set(si.power);
        launcher.triggerFeeder(si.feed);
        SmartDashboard.putNumber("Shooter Speed", si.power);
        SmartDashboard.putBoolean("Shooter Feeding", si.feed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
