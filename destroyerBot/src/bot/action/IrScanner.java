package bot.action;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RangeFinderAdaptor;

public class IrScanner {

	private final EV3IRSensor irSensor;
	private RangeFinderAdaptor adaptor = null;

	// Base line for accurate object detection
	public static final float MAX_RELIABLE_SCAN_DISTANCE = 7.0F;

	private static final float MAX_DISTANCE_FOR_COLOR_DETECTION = 1F;

	public IrScanner(Port irSensorPort) {
		irSensor = new EV3IRSensor(irSensorPort);
		SensorMode sensorMode = irSensor.getDistanceMode();
		adaptor = new RangeFinderAdaptor(sensorMode);
	}

	public synchronized boolean objectDetected() {
		float currentDistance = getCurrentScanDistance();

		// within our scanning range?
		return currentDistance != 0 && (getCurrentScanDistance() <= MAX_RELIABLE_SCAN_DISTANCE);
	}

	public synchronized boolean isWithinColorRange() {
		float currentDistance = getCurrentScanDistance();
		return currentDistance != 0 && (getCurrentDistanceFromObject() <= MAX_DISTANCE_FOR_COLOR_DETECTION);
	}

	public void close() {
		irSensor.close();
	}

	private synchronized float getCurrentScanDistance() {
		float currentDistance = getCurrentDistanceFromObject();
		float[] sample = adaptor.getRanges();

		System.out.println("Scan current distance from sensor adaptor: " + currentDistance);
		float[] irSample = new float[irSensor.sampleSize()];

		// get the raw sample data
		irSensor.getDistanceMode().fetchSample(irSample, 0);

		// calculate average of scanned distances
		float scanDistance = findAverageScanDistance(sample);
		return scanDistance;
	}

	private float findAverageScanDistance(float[] sample) {
		float average = 0;
		for (float scanDistance : sample) {
			average += scanDistance;
		}
		average = average / sample.length;

		System.out.println("Average scan distance: " + average);
		return average;
	}

	private float getCurrentDistanceFromObject() {
		return adaptor.getRange();
	}
}
