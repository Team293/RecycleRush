package SpikeLibrary;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class SpikeLEDButton extends SpikeButton{

	private Timer timer = new Timer();
	private int outputNumber;
	private double periodLength = 0.1; //length of flashes in seconds
	private boolean flashState = false;

	public SpikeLEDButton(Joystick joystick, int buttonNumber, int outputNumber) {
		super(joystick, buttonNumber);
		this.joystick = joystick;
		this.outputNumber = outputNumber;
		timer.start();

	}

	public void setOutput(boolean state) {
		joystick.setOutput(outputNumber, state);
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
