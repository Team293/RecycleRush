package subsystems;

import edu.wpi.first.wpilibj.I2C;

public class LED {
	private static final I2C arduino = new I2C(I2C.Port.kOnboard, 2); //port 0 = onboard
	private static final int AUTO = 0;
	private static final int AUTOFINISHED = 1;
	
	public void setState(int data) {
		byte[] toSend = new byte[] { (byte) data};
		arduino.transaction(toSend, 1, null, 0);
	}
}
