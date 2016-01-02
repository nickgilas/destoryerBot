package bot;

import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import bot.action.IrScanner;
import bot.behavior.ColorObjectDetector;
import bot.behavior.ObjectEngager;
import bot.behavior.ProximityDectector;

public class BotStarter {

	public static void main(String[] args) throws Exception {
		System.out.println("BotStarter initiated");

		IrScanner irScanner = new IrScanner(SensorPort.S1);

		// behaviors
		ProximityDectector objectFinder = new ProximityDectector(irScanner);
		ObjectEngager objectEngager = new ObjectEngager(irScanner);
		ColorObjectDetector colorDetector = new ColorObjectDetector();

		// listeners
		// objectFinder.addObjectFoundListener(objectEngager);
		// objectEngager.addObjectFoundListener(colorDetector);

		// Behavior array for arbitrator
		Behavior[] behave = { colorDetector, objectEngager, objectFinder };

		Arbitrator arb = new Arbitrator(behave);

		// arb.start();

		objectFinder.action();

		System.out.println("BotStarted exited");

	}
}
