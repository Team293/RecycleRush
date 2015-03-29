package SpikeLibrary;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class SpikeLED {

	private Timer timer = new Timer();
	private double periodLength = 0.1; //length of flashes in seconds
	private boolean flashState = false;

	Joystick joystick;
	int port;

	public SpikeLED(Joystick joystick, int port) {
		this.joystick = joystick;
		this.port = port;
	}

	public void setOutput(boolean state) {
		joystick.setOutput(port, state);
	}

	public void flash(boolean state) {
		if (state) {
			if (timer.get() > periodLength) {
				timer.reset();
				flashState = !flashState;
				setOutput(flashState);
			}
		} else {
			setOutput(false);
		}
	}
}
