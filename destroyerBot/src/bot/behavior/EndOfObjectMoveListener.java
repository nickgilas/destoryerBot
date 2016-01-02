package bot.behavior;

import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;

public class EndOfObjectMoveListener implements MoveListener {

	private static final float FULL_CIRCLE = 365F;

	private float angleTurned = 0;

	@Override
	public void moveStarted(Move event, MoveProvider mp) {
		angleTurned += event.getAngleTurned();
		System.out.println("EndOfObjectMoveListener.moveStarted");

	}

	@Override
	public void moveStopped(Move event, MoveProvider mp) {
		System.out.println("EndOfObjectMoveListener.moveStopped");
	}

}
