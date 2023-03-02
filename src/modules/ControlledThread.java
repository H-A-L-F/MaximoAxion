package modules;

public abstract class ControlledThread implements Runnable {
	public Thread t;
	private String threadName;
	protected volatile boolean running = true;
	protected volatile boolean paused = false;

	public ControlledThread(String threadName) {
		super();
		this.threadName = threadName;
		start();
	}

	public void run() {
		if (running) {
			synchronized (this) {
				if (paused) {
					return;
				}
				update();
			}
		}
	}
	
	public abstract void update();
	
	public void start() {
		if (t == null) {
			t = new Thread(this, this.threadName);
			t.start();
		}
	}

	public void pause() {
		paused = true;
	}

	public synchronized void resume() {
		paused = false;
		notify();
	}

	public void stop() {
		running = false;
	}
}
