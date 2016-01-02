package bot.action;

public interface Movement {

	public abstract void forward(float speed);

	public abstract void backward(float speed);

	public abstract float stop();

	public abstract void turnLeft() throws Exception;

	public abstract void turnRight() throws Exception;

	public abstract void turnAround() throws Exception;

}