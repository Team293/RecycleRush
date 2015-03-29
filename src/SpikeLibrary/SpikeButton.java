package SpikeLibrary;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SpikeButton extends JoystickButton{

	
    Joystick joystick;
    int buttonNumber;
    boolean toggle = false;
    boolean previous = false;
    boolean current = false;

	public SpikeButton(Joystick joystick, int buttonNumber) {
		super(joystick, buttonNumber);
		this.joystick = joystick;
		this.buttonNumber = buttonNumber;
	}
	
	private void update() {
		previous = current;
//		current = super.get();
		current = joystick.getRawButton(buttonNumber);
	}
	
	public boolean isHeld() {
		update();
		return current;
	}
	
	public boolean isBumped() {
		update();
		if (current && !previous) {
			return true;
		}
		return false;
	}
	
	public boolean isToggled() {
		if (isBumped()) {
			toggle = !toggle;
		}
		return toggle;
	}
	
	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
	

}
