package modules;

public abstract class ControlledThread implements Runnable {
	private Thread t;
	private String threadName;
	private volatile boolean running = true;
	private volatile boolean paused = false;

	public ControlledThread(String threadName) {
		super();
		this.threadName = threadName;
		start();
	}

	public void run() {
//		while (running) {
//			synchronized (this) {
//				while (paused) {
//					try {
//						wait();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				update();
//			}
//		}
		if (running) {
			synchronized (this) {
				if (paused) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
