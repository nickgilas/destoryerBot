package bot.action;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.Move.MoveType;

//TODO - make this class thread safe
public class Drive implements Movement {

	// TODO - change to enum and update methods for this type
	public float MAX_SPEED;
	public final float MEDIUM_SPEED;
	public final float SLOW_SPEED;

	private RegulatedMotor leftMotor = Motor.A;
	private RegulatedMotor rightMotor = Motor.B;

	private Move motor = new Move(true, 1.0F, 1.0F);

	private static Drive me;

	private Drive() {
		MAX_SPEED = leftMotor.getMaxSpeed();
		MEDIUM_SPEED = MAX_SPEED / 2;
		SLOW_SPEED = MAX_SPEED / 3;
	}

	public static Drive getInstance() {
		if (me == null) {
			me = new Drive();
		}
		return me;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see moverBot.action.Movement#forward(int)
	 */
	@Override
	public void forward(float speed) {
		setSpeedOnMotors(speed);
		leftMotor.forward();
		rightMotor.forward();
		motor.setValues(MoveType.TRAVEL, 7F, 0, true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see moverBot.action.Movement#backward(int)
	 */
	@Override
	public void backward(float speed) {
		setSpeedOnMotors(speed);
		leftMotor.backward();
		rightMotor.backward();
		motor.setValues(MoveType.TRAVEL, 7F, 180, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see moverBot.action.Movement#stop()
	 */
	@Override
	public float stop() {
		leftMotor.stop();
		rightMotor.stop();
		return motor.getDistanceTraveled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see moverBot.action.Movement#turnLeft()
	 */
	@Override
	public void turnLeft() throws Exception {
		setSpeedOnMotors(MEDIUM_SPEED);
		leftMotor.backward();
		rightMotor.forward();
		motor.setValues(MoveType.ROTATE, 2, 90, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see moverBot.action.Movement#turnRight()
	 */
	@Override
	public void turnRight() throws Exception {
		setSpeedOnMotors(MEDIUM_SPEED);
		leftMotor.forward();
		rightMotor.backward();
		motor.setValues(MoveType.ROTATE, 2, -90, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see moverBot.action.Movement#turnAround()
	 */
	@Override
	public void turnAround() throws Exception {
		setSpeedOnMotors(MAX_SPEED);
		leftMotor.forward();
		rightMotor.backward();
		stop();
	}

	private void setSpeedOnMotors(float speed) {
		leftMotor.setSpeed((int) speed);
		rightMotor.setSpeed((int) speed);
	}

}
