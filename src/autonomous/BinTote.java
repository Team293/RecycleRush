package autonomous;

import subsystems.DriveTrain;
import subsystems.Elevator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BinTote extends Auto{
	private static double liftT = liftTLength;
	private static double captureT = liftT + captureTLength;
	private static double turnT = captureT + turnTLength;
	private static double driveT = turnT + driveTLength;

	public BinTote() {
		super();
	}

	public void run() {
		zero();
		if (zeroed && autoTimer.get() < liftT) {
			Elevator.setPresetPosition(3);
			SmartDashboard.putString("mode", "lifting");
		} else if (autoTimer.get() < captureT) {
			DriveTrain.tankDrive(captureSpeed, captureSpeed);
			SmartDashboard.putString("mode", "capturing");
		} else if (autoTimer.get() < turnT) {
			DriveTrain.tankDrive(0, turnSpeed);
			SmartDashboard.putString("mode", "turning");
		} else if (autoTimer.get() < driveT) {
			DriveTrain.tankDrive(driveSpeed, driveSpeed);
			Elevator.periodicPControl();
			SmartDashboard.putString("mode", "driving");
		} else {
			DriveTrain.stop();
			SmartDashboard.putString("mode", "stopping");
		}
		Elevator.periodicPControl();
	}

}
