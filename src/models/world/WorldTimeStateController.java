package models.world;

import java.util.Vector;

import modules.StateTask;

public class WorldTimeStateController extends WorldStateController<WorldTimes> {
	private Vector<WorldTimes> times;
	
	public WorldTimeStateController(World world, WorldTimes initState) {
		super(world, initState);
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
		this.stateTask = new StateTask<WorldTimes>("WorldTimeTask") {
			
			public void switchTime() {
				int idx = world.wTime.getOrder() + 1;
				
				if(idx >= times.size()) {
					idx = 0;
					world.dayPass();
				}
				
				WorldTimes temp = times.get(idx);
				world.switchTime(temp);
			}

			@Override
			public void update() {
				timePassed++;
				System.out.println(timePassed);
				if(timePassed >= 3) {
					timePassed = 0;
					switchTime();
				}
			}
		};
		
		tp.scheduleTaskAtRate(0, 1, stateTask);
		stateTask.pause();
	}
	
}
