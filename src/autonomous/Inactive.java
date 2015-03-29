package autonomous;

import subsystems.Elevator;

public class Inactive extends Auto{
	public void run() {
		zero();
		Elevator.periodicPControl();
	}
}
