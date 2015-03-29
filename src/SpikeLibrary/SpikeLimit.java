package SpikeLibrary;

import edu.wpi.first.wpilibj.DigitalInput;

public class SpikeLimit extends DigitalInput{
	
    boolean toggle = false;
    boolean previous = false;
    boolean current = false;

	
	public SpikeLimit(int port) {
		super(port);
	}
	
	private void update() {
		previous = current;
		current = this.get();
	}
	
	public boolean isHeld() {
		update();
		return current;
	}
	
	public boolean isBumped() {
		update();
		if (current && (!previous)) {
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
}
