package org.usfirst.frc.team293.robot;

public class Ports {
	public static final int 

	//joysticks
	leftJoystick = 0,
	rightJoystick = 1,
	launchpad = 2,

	//rightjoystick
	trigger = 1,

	//launchpad
	lever = 11,
	toggleSlurperBInput = 4,
	elevatorUpB = 3,
	elevatorDownB = 5,
	elevator0BInput = 2,
	elevator1BInput = 10,
	elevator2BInput = 9,
	elevator3BInput = 8,
	elevator4BInput = 6,
	elevator5BInput = 1,

	//LEDs
	lIndicatorStrip = 1,
	rIndicatorStrip = 2,
	toggleSlurperBOutput = 4,
	elevatorUpBOutput = 5,
	elevatorDownBOutput = 3,
	elevator0BOutput = 11,
	elevator1BOutput = 10,
	elevator2BOutput = 9,
	elevator3BOutput = 8,
	elevator4BOutput = 7,
	elevator5BOutput = 6,

	nobA = 6,

	//Elevator
	elevatorEncoder1 = 4,
	elevatorEncoder2 = 5,
	elevatorTopLimit = 8,
	elevatorBottomLimit = 7,
	//Slurpers
	lbLimit = 12,
	rbLimit = 10,
	lfLimit = 2, //EDITED FROM 2
	rfLimit = 0,  //EDITED FROM 0
	lOpticalLimit = 3,//EDITED FROM 3
	rOpticalLimit = 1,//EDITED FROM 1
	lToteLimit = 6,
	rToteLimit = 9,
	//drivetrain encoders.
	leftDriveEncoder1 = 13,
	leftDriveEncoder2 = 14,
	rightDriveEncoder1 = 15,
	rightDriveEncoder2 = 16,
	

	//analog sensors
	armPot = 1,
	gyro = 0,

	//motors
	leftDrive = 0,
	rightDrive = 1,
	elevator = 2,
	arm = 3,
	lBelt=4,
	rBelt=3;


}
