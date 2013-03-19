package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.Drive;
import edu.wpi.first.wpilibj.templates.subsystems.Launcher;
import edu.wpi.first.wpilibj.templates.subsystems.PullyTilt;

/**
 * This class contains the variables that are required for all of the command
 * classes, such as the subsystems.
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    
    public static Drive drive = new Drive();
    public static PullyTilt ptilt = new PullyTilt();
    public static Launcher launcher = new Launcher();

    public static void init() {
        // creates operator interface
        oi = new OI();

        // puts the subsystems on the dashboard for debugging purposes
        SmartDashboard.putData(drive);
        SmartDashboard.putData(launcher);
        SmartDashboard.putData(ptilt);
    }

   
    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
