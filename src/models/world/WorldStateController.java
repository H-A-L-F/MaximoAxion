package models.world;

import modules.StateTask;
import modules.ThreadPoolManager;

public abstract class WorldStateController<T> {
	protected ThreadPoolManager tp = ThreadPoolManager.getInstance();
	
	protected StateTask<T> stateTask;
	
	public WorldStateController(T initState) {
		initStateTask(initState);
	}
	
	protected abstract void initStateTask(T initState);
}
