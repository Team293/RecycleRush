package autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.DriveTrain;
import subsystems.Elevator;

public class BinToteTurn extends Auto{
	/**
	 * Puts yellow bin without stacking the three bins in a stack.
	 */
	private static double liftT = liftTLength;
	private static double captureT = liftT + captureTLength;
	private static double turnT = captureT + turnTLength;
	private static double driveT = turnT + driveTLength;
	private static double turn2T = driveT + turn2TLength;
	
	public BinToteTurn() {
		super();
	}

	public void run() {
		zero();
		if (zeroed && autoTimer.get() < liftT) {
			Elevator.setPresetPosition(3);
			SmartDashboard.putString("mode", "lifting");
		} else if (autoTimer.get() < captureT) {
			DriveTrain.tankDrive(captureSpeed, captureSpeed);
			Elevator.periodicPControl();
			SmartDashboard.putString("mode", "capturing");
		} else if (autoTimer.get() < turnT) {
			DriveTrain.tankDrive(0, turnSpeed);
			SmartDashboard.putString("mode", "turning");
		} else if (autoTimer.get() < driveT) {
			DriveTrain.tankDrive(driveSpeed, driveSpeed);
			SmartDashboard.putString("mode", "driving");
		} else if (autoTimer.get() < turn2T) {
			DriveTrain.tankDrive(-turn2Speed, turn2Speed);
			SmartDashboard.putString("mode", "turning2");
		} else {
			DriveTrain.stop();
			SmartDashboard.putString("mode", "stopping");
		}
		Elevator.periodicPControl();
	}

}
