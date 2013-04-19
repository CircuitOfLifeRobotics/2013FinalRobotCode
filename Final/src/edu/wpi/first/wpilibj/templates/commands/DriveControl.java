package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.OI.DriverInput;
import edu.wpi.first.wpilibj.templates.subsystems.Drive;

/**
 * This Command controls the Drive subsystem of the robot. (default command)
 */
public class DriveControl extends CommandBase {
    
    public DriveControl() {
        requires(drive);
    }

    protected void initialize() {
        drive.startEncoders();
    }

    protected void execute() {
        DriverInput di = oi.getDriverInput();
        drive.arcadeDrive(di.forward, di.turn);
        Drive.encoderValue();

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
