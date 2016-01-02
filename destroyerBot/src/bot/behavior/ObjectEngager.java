package bot.behavior;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import bot.action.IrScanner;

/**
 * The objective of this behavior class is to move the bot toward an already
 * found object. It will
 *
 */
public class ObjectEngager implements Behavior {

	private IrScanner scan;
	private boolean continueScanning = true;
	private DifferentialPilot pilot = new DifferentialPilot(5, 5, Motor.A, Motor.B);

	public ObjectEngager(IrScanner scan) {
		this.scan = scan;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		// TODO - move forward until object is within color range
		Sound.beep();
		System.out.println("Within color range");

		Button.waitForAnyPress();

	}

	@Override
	public void suppress() {
		continueScanning = false;

	}

}
