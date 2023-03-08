package modules;

public abstract class StateTask<T> extends ControlledThread {
	public double timePassed;
	
	public StateTask(String threadName) {
		super(threadName);
	}
	
}
