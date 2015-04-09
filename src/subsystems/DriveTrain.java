package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {    
    private static final Talon leftMotor = new Talon(Ports.leftDrive);
    private static final Talon rightMotor = new Talon(Ports.rightDrive);

    private static final Encoder leftEncoder = new Encoder(Ports.leftDriveEncoder1, Ports.leftDriveEncoder2);
    private static final Encoder rightEncoder = new Encoder(Ports.rightDriveEncoder1, Ports.rightDriveEncoder2);
    public static final RobotDrive drive = new RobotDrive(leftMotor, rightMotor);
    //Underneath is all stuff for Straight drive
    private static Gyro gyro;
	private static PIDRobotDrive pidRobotDrive;	// this wraps RobotDrive
	private static PIDGyro pidGyro;	
	static PIDController autoDrivePID;
	static double direction=0;   
	static int status=1;
	static double leftencoder;
	static double rightencoder;
	static double averageencoder;
	static int count;
    //finish

    

    public static void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
    public static void slowDrive(double leftSpeed, double rightSpeed) {
    	drive.tankDrive(leftSpeed * 0.5, rightSpeed * 0.5);
    }
	
	public static void arcadeDrive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public static void gyroDrive(double setAngle, double speed) {
		double error = setAngle - gyro.getAngle();
		double difference = error * .02;
		drive.tankDrive(speed - difference, speed + difference);
	}
	
	public static void stop() {
		drive.tankDrive(0, 0);
	}
	//PID Stuff//////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		private static class PIDRobotDrive implements PIDOutput {
			public void pidWrite(double output) {
				drive.drive(0.5, output);	// use pid output to steer
			}
		}

		// alternatively, could also try feeding gyro into PIDController().
		private static class PIDGyro implements PIDSource {
			public double pidGet() {
				SmartDashboard.putNumber("Gyro Angle",gyro.getAngle());
				SmartDashboard.putNumber("Raw Gyro", gyro.getRate());
				double angle = gyro.getAngle()*2;	// angle could be > 360, or -ve
				angle %= 360;				// angle will be positive after modulo
				if (angle > 180)			// range is 0 to 360
					angle -= 360;			// range is now -180 to +180
				angle /= 180;				// range is now -1 to +1
				angle=direction+angle;
				SmartDashboard.putNumber("Gyro", -angle);
				return -angle;
				
				
			}
		}

		// change PID constants
		public void setPID(double p, double i, double d) {
			autoDrivePID.setPID(p, i, d);
		}

		public static void gyroInit() {
			gyro = new Gyro(0);             // Gyro on Analog Channel 1
			gyro.setSensitivity(0.0125); 	// ADW22307:  depends on gyro model

	        drive.setExpiration(0.1);		// set timeout to 100ms
			drive.setSensitivity(1.0);		// set turn radius, needs experimentation

				// contains pidWrite()
			pidRobotDrive = new PIDRobotDrive();
			pidGyro = new PIDGyro();				// contains pidGet()

			// values for kP, kI, kD can be updated using setPID()
			autoDrivePID = new PIDController(0.45, 0.0, 0.0, pidGyro, pidRobotDrive);
			autoDrivePID.setContinuous(false);
			autoDrivePID.setInputRange(-1.0, +1.0);
			autoDrivePID.setOutputRange(-1.0, +1.0);
			autoDrivePID.setTolerance(1.0);			// 1%
			autoDrivePID.setSetpoint(0.0);
		}

		public static void enable(){
			if (status==1){
			autoDrivePID.enable();
			status=0;
			}
				
		}
		public static void disable(){
			if (status==0){
			autoDrivePID.disable();
			status=1;
			}	
		}
		public static int turnleft(){
			direction=direction+.005;	
			count=count+1;
			return count;
		}
		public static double getAvgDistance(){
			leftencoder =leftEncoder.get()/256*6*Math.PI;  //spread out wheel diameter 6 to make sense
			rightencoder=rightEncoder.get()/256*6*Math.PI;
			averageencoder=leftencoder+rightencoder/2;
			return averageencoder;	
		}
		public static void resetEncoders(){
			leftEncoder.reset();
			rightEncoder.reset();
		}

}
