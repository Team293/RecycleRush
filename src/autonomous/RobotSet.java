package autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.DriveTrain;
import subsystems.Elevator;

public class RobotSet extends Auto{
	private static double driveT = 1.5;

	public RobotSet() {
		super();
	}

	public void run() {
		zero();
		if (zeroed && autoTimer.get() < driveT) {
			DriveTrain.tankDrive(driveSpeed, driveSpeed);
			SmartDashboard.putString("mode", "driving");
		}
		Elevator.periodicPControl();
	}
}
