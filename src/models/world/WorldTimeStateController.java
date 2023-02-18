package models.world;

import java.util.Vector;

import modules.StateTask;

public class WorldTimeStateController extends WorldStateController<WorldTimes> {
	private Vector<WorldTimes> times;
	
	public WorldTimeStateController(WorldTimes initState) {
		super(initState);
		this.times = WorldTimes.getWorldTimes();
	}
	
	public void start() {
		startTime();
	}
	
	public void stop() {
		stopTime();
	}
	
	private void startTime() {
		stateTask.resume();
	}
	
	private void stopTime() {
		stateTask.stop();
	}
	
	@Override
	protected void initStateTask(WorldTimes initState) {
		this.stateTask = new StateTask<WorldTimes>("WorldTimeTask", initState) {
			
			public void switchTime() {
				WorldTimes temp = times.get(state.getOrder());
				switchState(temp);
				
			}

			@Override
			public void update() {
				timePassed++;
				
				if(timePassed <= 20) {
					timePassed = 0;
					
					switchTime();
				}
			}
		};
		
		tp.scheduleTaskAtRate(0, 1, stateTask);
		stateTask.pause();
	}
	
}
