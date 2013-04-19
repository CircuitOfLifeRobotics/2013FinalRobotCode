package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class holds all of the objects for actual controls on the robot, such as
 * motor controllers and relays.
 */
public class RobotMap {
    
    // Drive Subsystem
    public static RobotDrive robotdrive = new RobotDrive(1,2,3,4);
    public static Encoder leftenc = new Encoder(1, 2),
            rightenc = new Encoder(3, 4);
    
    
    // Shooter Subsystem
    public static Jaguar shootjag = new Jaguar(5);
    public static Relay feedspike = new Relay(1);
    public static DigitalInput feedbtn = new DigitalInput(7);
    
    // Tilt Subsystem
    public static Relay tiltrelay = new Relay(3);
    
    // Light for the camera
    public static Relay camlight = new Relay(2);
    
    public static AnalogChannel tiltpot = new AnalogChannel(1);
}
