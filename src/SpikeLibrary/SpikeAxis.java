package SpikeLibrary;

import edu.wpi.first.wpilibj.Joystick;

public class SpikeAxis {
	int port;
	Joystick joystick;
	
	public SpikeAxis(Joystick joystickInput, int portInput) {
		joystick = joystickInput;
		port = portInput;
	}
	
	public double get() {
		return joystick.getRawAxis(port);
	}
}
