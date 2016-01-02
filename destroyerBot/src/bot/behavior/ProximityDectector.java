package bot.behavior;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import bot.action.IrScanner;

public class ProximityDectector implements Behavior {

	private IrScanner scan;
	private boolean continueScanning = true;
	// private Drive drive;
	private DifferentialPilot pilot = new DifferentialPilot(5, 5, Motor.A, Motor.B);

	public ProximityDectector(IrScanner scan) {
		this.scan = scan;
		pilot.addMoveListener(new EndOfObjectMoveListener());
		// drive = Drive.getInstance();
		System.out.println("ObjectFinder initalized");
	}

	@Override
	public boolean takeControl() {
		// default behavior
		return true;
	}

	@Override
	public void action() {

		continueScanning = true;
		// scan 360 degrees, if nothing found then fire object not found event

		pilot.setAcceleration(5);
		pilot.setTravelSpeed(5);
		float travledDegrees = 0;

		while (!scan.objectDetected() && continueScanning == true) {

			pilot.rotateRight();

			Delay.msDelay(500);

			// TODO check for full 360 degree rotation and if so we need to
			// drive forward for a little distance and start scanning again
		}

		pilot.quickStop();
		Sound.beepSequence();

		System.out.println("Found object");

		// while (!Button.ESCAPE.isDown()) {
		// System.out.println("Wait...");
		// }

		moveToCenterOfObject();
		// notifyListeners();
	}

	public void moveToCenterOfObject() {

		// scan to the right and stop at the end of the object
		pilot.setAcceleration(5);
		pilot.setTravelSpeed(5);

		// found the right end of the object
		pilot.quickStop();
		Sound.beepSequence();

		Button.waitForAnyPress();

		Move move = pilot.getMovement();

		pilot.quickStop();
		// move 2 degrees and check for object
		// stop searching after a full 360 degree rotation has occurred. Then
		// drive forward for a bit and start looking again

		pilot.rotate(move.getAngleTurned() / 2);

		pilot.quickStop();
		Sound.beep();
	}

	@Override
	public void suppress() {
		continueScanning = false;
	}

}
