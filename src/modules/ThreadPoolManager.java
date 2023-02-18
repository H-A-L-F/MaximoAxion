package modules;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
	
	private static ThreadPoolManager instance;
	
	private ThreadPoolManager() {
		
	}
	
	public static ThreadPoolManager getInstance() {
		if(instance == null) instance = new ThreadPoolManager();
		return instance;
	}
	
	public ScheduledFuture<?> scheduleTaskAtRate(int start, int delay, ControlledThread task) {
		return executor.scheduleAtFixedRate(task, start, delay, TimeUnit.SECONDS);
	}
}
