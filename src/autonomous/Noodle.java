package autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.DriveTrain;
import subsystems.Elevator;

public class Noodle extends Auto{
	private static double liftT = liftTLength;
	private static double turnT = liftTLength + .6;
	
	public Noodle() {
		super();
	}
	
	public void run() {
		zero();
		if (zeroed && autoTimer.get() < liftT) {
			Elevator.setPresetPosition(3);
			SmartDashboard.putString("mode", "lifting");
		} else if (zeroed && autoTimer.get() < turnT) {
			DriveTrain.tankDrive(-0.3, -0.7);
		}
		Elevator.periodicPControl();
	}
}
