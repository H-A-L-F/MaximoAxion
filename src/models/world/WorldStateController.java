package models.world;

import modules.StateTask;
import modules.ThreadPoolManager;

public abstract class WorldStateController<T> {
	protected ThreadPoolManager tp = ThreadPoolManager.getInstance();
	protected World world;
	
	protected StateTask<T> stateTask;
	
	public WorldStateController(World world, T initState) {
		this.world = world;
		initStateTask(initState);
	}
	
	protected abstract void initStateTask(T initState);
	public abstract void start();
	public abstract void stop();
}
