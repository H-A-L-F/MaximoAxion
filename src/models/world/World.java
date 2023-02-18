package models.world;

public class World {
	public WorldTimes wTime;
	public WorldStatus wStatus;
	public WorldEvents wEvents;
	private WorldTimeStateController wTimeController;
	
	public World() {
		super();
	}
	
	public void initWorld() {
		this.wTime = WorldTimes.MORNING;
		this.wTimeController = new WorldTimeStateController(wTime);
	}
	
	public void startWorld() {
		this.wTimeController.start();
	}
}
