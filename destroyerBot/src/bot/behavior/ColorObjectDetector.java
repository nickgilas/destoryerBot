package bot.behavior;

import lejos.hardware.Sound;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;
import bot.BrickConfig;

public class ColorObjectDetector implements Behavior {

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action() {
		System.out.println("Detecting color of object");
		EV3ColorSensor colorSensor = new EV3ColorSensor(BrickConfig.COLOR_SENSOR_PORT);

		int colorId = colorSensor.getColorID();
		System.out.println("Found color ID: " + colorId);

		switch (colorId) {
		case Color.BLUE:
			System.out.println("Found blue color");
		case Color.RED:
			System.out.println("Found red color");
		case Color.GREEN:
			System.out.println("Found green color");
		default:
			System.out.println("Unknown colorId: " + colorId);
		}

		colorSensor.close();
		Sound.beep();
	}

	@Override
	public void suppress() {

	}

}
