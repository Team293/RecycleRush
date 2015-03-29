package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {    
    private static final VictorSP leftMotor = new VictorSP(Ports.leftDrive);
    private static final VictorSP rightMotor = new VictorSP(Ports.rightDrive);
    
    private static final RobotDrive drive = new RobotDrive(leftMotor, rightMotor);

    public static void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
    public static void slowDrive(double leftSpeed, double rightSpeed) {
    	drive.tankDrive(leftSpeed * 0.5, rightSpeed * 0.5);
    }
	
	public static void skidControl(double leftInput, double rightInput) {
		
	}
	
	public static void arcadeDrive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public static void stop() {
		drive.tankDrive(0, 0);
	}
}
