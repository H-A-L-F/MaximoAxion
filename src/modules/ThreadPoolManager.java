package modules;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolManager {
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
}
