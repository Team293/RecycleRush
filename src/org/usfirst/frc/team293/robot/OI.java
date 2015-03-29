package org.usfirst.frc.team293.robot;

import SpikeLibrary.SpikeAxis;
import SpikeLibrary.SpikeButton;
import SpikeLibrary.SpikeLED;
import SpikeLibrary.SpikeLEDButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.Arm;
import subsystems.DriveTrain;
import subsystems.Elevator;
import subsystems.PDP;
import subsystems.Slurper;


public class OI {
	//Joysticks
	private static final Joystick leftJoystick = new Joystick(Ports.leftJoystick);
	private static final Joystick rightJoystick = new Joystick(Ports.rightJoystick);
	private static final Joystick launchpad = new Joystick(Ports.launchpad);

	private static final SpikeLEDButton elevator0B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator1B = new SpikeLEDButton(launchpad, Ports.elevator1BInput, Ports.elevator1BOutput);
	private static final SpikeLEDButton elevator2B = new SpikeLEDButton(launchpad, Ports.elevator2BInput, Ports.elevator2BOutput);
	private static final SpikeLEDButton elevator3B = new SpikeLEDButton(launchpad, Ports.elevator3BInput, Ports.elevator3BOutput);
	private static final SpikeLEDButton elevator4B = new SpikeLEDButton(launchpad, Ports.elevator4BInput, Ports.elevator4BOutput);
	private static final SpikeLEDButton elevator5B = new SpikeLEDButton(launchpad, Ports.elevator5BInput, Ports.elevator5BOutput);
	private static final SpikeButton canOn2TotesB = new SpikeButton(leftJoystick, Ports.canOn2TotesB);
	private static final SpikeLEDButton elevatorDownB = new SpikeLEDButton(launchpad, Ports.elevatorDownB, Ports.elevatorDownBOutput);
	private static final SpikeLEDButton elevatorUpB = new SpikeLEDButton(launchpad, Ports.elevatorUpB, Ports.elevatorUpBOutput);
	private static final SpikeButton joyElevatorDownB = new SpikeButton(rightJoystick, 8);
	private static final SpikeButton joyElevatorUpB = new SpikeButton(rightJoystick, 9);
	private static final SpikeButton oneToteB = new SpikeButton(rightJoystick, Ports.trigger);
	private static boolean b5wasHeld = false;

	private static final SpikeButton slurperManualB = new SpikeButton(launchpad, Ports.lever);
	private static final SpikeLEDButton toggleSlurperB = new SpikeLEDButton(launchpad, Ports.toggleSlurperBInput, Ports.toggleSlurperBOutput);

	private static final SpikeAxis softNob = new SpikeAxis(launchpad, Ports.nobA);

	private static final SpikeButton slowDriveB = new SpikeButton(leftJoystick, Ports.trigger);

	private static final SpikeLED lStrip = new SpikeLED(launchpad, Ports.lIndicatorStrip);
	private static final SpikeLED rStrip = new SpikeLED(launchpad, Ports.rIndicatorStrip);

	public static void controlDriveTrain() {
		if (slowDriveB.isHeld()) {
			DriveTrain.slowDrive(-leftJoystick.getY(), -rightJoystick.getY());
		} else {
			DriveTrain.tankDrive(-leftJoystick.getY(), -rightJoystick.getY());
		}
	}

	public static void controlSlurper() {		
		if (Slurper.isBack()) {
			toggleSlurperB.setOutput(true);
		} else if (!Slurper.isForward()) {
			toggleSlurperB.flash(true);
		} else {
			toggleSlurperB.setOutput(false);
		}
		
		if (slurperManualB.isHeld()) {
			Slurper.manualMove(toggleSlurperB.isBumped());
		} else {
			Slurper.autoMove();
		}
	}
	
	private static final SpikeButton fSlurperB = new SpikeButton(leftJoystick, 11);
	private static final SpikeButton bSlurperB = new SpikeButton(leftJoystick, 10);
	
	public static void controlSlurperRaw() {
		if (fSlurperB.isHeld()) {
			Slurper.move(0.5, 0.5);
		} else if (bSlurperB.isHeld()) {
			Slurper.move(-0.5, -0.5);
		} else {
			Slurper.move(0, 0);
		}
	}

	public static void monitorElevatorB(SpikeLEDButton button, double position) {
		if (Elevator.getTargetPosition() == position && Elevator.onTarget()) {
			button.setOutput(true);
		} else if (Elevator.getTargetPosition() == position) {
			button.flash(true);
		} else {
			button.setOutput(false);
		}
	}

	public static void controlElevator() {
		lStrip.setOutput(Slurper.lToteLimit.get());
		rStrip.setOutput(Slurper.rToteLimit.get());
		Slurper.display();
		elevatorUpB.setOutput(elevatorUpB.isHeld());
		elevatorDownB.setOutput(elevatorDownB.isHeld());
		if (softNob.get() < 0) {
			Elevator.setSoftMode(true);
		} else {
			Elevator.setSoftMode(false);
		}
		if (elevatorUpB.isHeld() || elevatorDownB.isHeld() || joyElevatorUpB.isHeld() || joyElevatorDownB.isHeld()) {
			if (elevatorUpB.isHeld() || joyElevatorUpB.isHeld()) {
				Elevator.updateManualPosition(true);
			} else if(elevatorDownB.isHeld() || joyElevatorDownB.isHeld()) {
				Elevator.updateManualPosition(false);
			}
		} else {
			if (oneToteB.isBumped()) {
				Elevator.toggleOneTote();
			} else if (elevator0B.isBumped()) {
				Elevator.setPresetPosition(0);
			} else if (elevator1B.isBumped()) {
				Elevator.setPresetPosition(1);
			} else if (elevator2B.isBumped()) {
				Elevator.setPresetPosition(2);
			} else if (elevator3B.isBumped()) {
				Elevator.setPresetPosition(3);
			} else if (elevator4B.isBumped()) {
				Elevator.setPresetPosition(4);
			} else if (canOn2TotesB.isBumped()) {
				Elevator.setPresetPosition(5);
			}

			if (elevator5B.isHeld()) {
				b5wasHeld = true;
			}
			SmartDashboard.putBoolean("5BwasHeld", b5wasHeld);
		}
		Elevator.periodicPControl();

		monitorElevatorB(elevator0B, Elevator.positions[0]);
		monitorElevatorB(elevator1B, Elevator.positions[1]);
		monitorElevatorB(elevator2B, Elevator.positions[2]);
		monitorElevatorB(elevator3B, Elevator.positions[3]);
		monitorElevatorB(elevator4B, Elevator.positions[4]);
		monitorElevatorB(elevator5B, Elevator.positions[5]);

	}

	public static void controlPDP() {
		PDP.monitor();
	}

}
