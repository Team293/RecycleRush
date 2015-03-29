package SpikeLibrary;

import java.awt.List;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;

public class SpikeInputs implements Runnable{

	private static final int buttonsPerJoystick = 11;
	private static final int axesPerJoystick = 4;
	private static final int outputsPerJoystick = 9;
	private static final int numberOfJoysticks = 3;
	private static final Joystick joystick0 = new Joystick(0);
	private static final Joystick joystick1 = new Joystick(1);
	private static final Joystick joystick2 = new Joystick(2);

	ArrayList<ArrayList<Boolean>> buttonValues = new ArrayList<ArrayList<Boolean>> ();
	for (int joystickPort = 0; joystickPort < numberOfJoysticks; joystickPort ++) {
		ArrayList<Boolean> joystickBValues = new ArrayList<Boolean> ();
		for (int port = 0; port < buttonsPerJoystick; port++) {
			joystickBValues.add(false);
		}
		buttonValues.add(joystickBValues);
	}

	@Override
	public void run() {
		for (int joystickPort = 0; joystickPort < numberOfJoysticks; joystickPort ++) {
			Joystick joystick = new Joystick(joystickPort);
			for (int buttonPort = 0; buttonPort < buttonsPerJoystick; buttonPort++) {
				ArrayList<Boolean> joystickValues = buttonValues.get(joystickPort);
				joystickValues.set(buttonPort, joystick.getRawButton(buttonPort));
				buttonValues.set(joystickPort, joystickValues);
			}
		}
	}

}