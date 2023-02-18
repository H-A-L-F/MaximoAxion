package models.world;

import main.GameMaster;

public class World {
	public int day;
	
	public WorldTimes wTime;
	public WorldStatus wStatus;
	public WorldEvents wEvents;
	private WorldTimeStateController wTimeController;
	
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
	}
	
	public void startWorld() {
		this.wTimeController.start();
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
	
	private void printEvents() {
		
	}
	
	public void switchTime(WorldTimes time) {
		this.wTime = time;
		
		notifyStatusChange();
	}
}
