package autonomous;

import subsystems.Elevator;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto {
	public static Timer autoTimer = new Timer();
	public static double liftTLength = 1.23;
	public static double captureTLength = 0.4;
	public static double turnTLength = 2.5;
	public static double driveTLength = 2.3;
	public static double turn2TLength = 1.2;
	//hello, from steve whos totally a programmer too
	public static double captureSpeed = 0.5;
	public static double turnSpeed = 0.55;
	public static double driveSpeed = 0.7;
	public static double turn2Speed = 0.7;
	
	public static boolean zeroed = false;

	
	public void init() {
		autoTimer.start();
		if (SmartDashboard.getBoolean("Middle Auto")) {
			turnTLength = 2.4;
		}
	}
	
	public void zero() {
		if (Elevator.isDown()) {
			zeroed = true;
		}
		if (!zeroed) {
			Elevator.updateManualPosition(false);
			Elevator.periodicPControl();
			autoTimer.reset();
		}
	}
	
	public void run() {
		
	}

}
