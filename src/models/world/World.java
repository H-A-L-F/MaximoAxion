package models.world;

import java.util.Vector;

import main.GameMaster;

public class World {
	public int day;
	
	public WorldTimes wTime;
	public WorldStatus wStatus;
	public Vector<WorldEvents> wEvents;
	private WorldTimeStateController wTimeController;
	private WorldEventStateController wEventController;
	
	private GameMaster gm;
	
	public World(GameMaster gm) {
		super();
		this.gm = gm;
		
		initWorld();
	}
	
	public void initWorld() {
		this.day = 0;
		
		this.wTime = WorldTimes.MORNING;
		this.wTimeController = new WorldTimeStateController(this, wTime);
	
		this.wEvents = new Vector<>();
		this.wEventController = new WorldEventStateController(this, WorldEvents.WORLD_NORMAL);
	}
	
	public void startWorld() {
		this.wTimeController.start();
		this.wEventController.start();
	}
	
	public void notifyStatusChange() {
		gm.notifyStatusChange();
	}
	
	public void printWorld() {
		printTime();
		printStatus();
		printEvents();
	}
	
	public void printDay() {
		System.out.println("Day " + day);
		System.out.println("------");
	}
	
	private void printTime() {
		System.out.println(wTime.getMessage());
	}
	
	private void printStatus() {
		
	}
	
	public void pause() {
		wTimeController.stop();
		wEventController.stop();
	}
	
	public void resume() {
		wTimeController.start();
		wEventController.start();
	}
	
	private void printEvents() {
		for (WorldEvents e : wEvents) {
			System.out.println(e.getMessage());
		}
	}
	
	public void switchTime(WorldTimes time) {
		this.wTime = time;
		
		if(time == WorldTimes.DAWN) {
			wEventController.procDay();
		} else if(time == WorldTimes.DUSK) {
			wEventController.procNight();
		}
	}
	
	public void dayPass() {
		day++;
		
		gm.dayPass();
	}
}
