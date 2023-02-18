package modules;

public abstract class StateTask<T> extends ControlledThread {
	public T state;
	public int timePassed;
	
	public StateTask(String threadName, T state) {
		super(threadName);
		this.state = state;
	}
	
	public void switchState(T state) {
		this.state = state;
	}
}
