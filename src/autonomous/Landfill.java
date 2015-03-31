package autonomous;

import subsystems.DriveTrain;
import subsystems.Elevator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Landfill extends Auto{
	private static double turnT = 3;
	private static double deployT = 1 + turnT;
	private static double backupT = turnT + deployT;


	public Landfill() {
		super();
	}

	public void run() {
		zero();
		if (zeroed && autoTimer.get() < turnT) {
			DriveTrain.gyroDrive(90, 0);
			SmartDashboard.putString("mode", "turning");
		} else if (autoTimer.get() < deployT) {
			Elevator.setPresetPosition(1);
			SmartDashboard.putString("mode", "deploying");
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
