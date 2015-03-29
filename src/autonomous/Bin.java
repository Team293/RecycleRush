package autonomous;

import subsystems.DriveTrain;
import subsystems.Elevator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Bin extends Auto{
	private static double liftT = liftTLength;
	private static double turnT = liftT + turnTLength + 0.15;
	private static double driveT = turnT + driveTLength - 0.35;


	public Bin() {
		super();
	}
	
	public void run() {
		zero();
		if (zeroed && autoTimer.get() < liftT) {
			Elevator.setPresetPosition(3);
			SmartDashboard.putString("mode", "lifting");
		} else if (autoTimer.get() < turnT) {
			DriveTrain.tankDrive(0, -turnSpeed);
			SmartDashboard.putString("mode", "turning");
		} else if (autoTimer.get() < driveT) {
			DriveTrain.tankDrive(-driveSpeed, -driveSpeed);
			SmartDashboard.putString("mode", "driving");
		} else {
			DriveTrain.stop();
			SmartDashboard.putString("mode", "stopping");
		}
		Elevator.periodicPControl();
	}
}