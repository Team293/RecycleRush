package subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class PDP {
	private static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static void monitor() {
		//SmartDashboard.putNumber("leftDriveCurrent", pdp.getCurrent(0));
		//SmartDashboard.putNumber("rightDriveCurrent", pdp.getCurrent(1));
		SmartDashboard.putNumber("Mast", pdp.getCurrent(2));
		//SmartDashboard.putNumber("current3", pdp.getCurrent(3));
		//SmartDashboard.putNumber("current4", pdp.getCurrent(4));
		//SmartDashboard.putNumber("current5", pdp.getCurrent(5));*/
		SmartDashboard.putNumber("totalcurrent", pdp.getTotalCurrent());
		
	}

}
