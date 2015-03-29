package autonomous;

import subsystems.DriveTrain;
import subsystems.Elevator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CanPickup extends Auto{
	private static double liftT = liftTLength;

	public CanPickup() {
		super();
	}

	public void run() {
		zero();
		if (zeroed && autoTimer.get() < liftT) {
			Elevator.setPresetPosition(3);
			SmartDashboard.putString("mode", "lifting");
		} else {
			DriveTrain.stop();
			SmartDashboard.putString("mode", "stopping");
		}
		Elevator.periodicPControl();
	}
}