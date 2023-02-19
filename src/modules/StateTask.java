package modules;

public abstract class StateTask<T> extends ControlledThread {
	public int timePassed;
	
	public StateTask(String threadName) {
		super(threadName);
	}
	
}
