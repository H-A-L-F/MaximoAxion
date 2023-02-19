package models.world;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Vector;

import modules.StateTask;

public class WorldEventStateController extends WorldStateController<WorldEvents> {
	private final Map<EventTypes, Vector<WorldEvents>> events;
	
	public WorldEventStateController(World world, WorldEvents initState) {
		super(world, initState);
		this.events = Map.ofEntries(
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NONE, WorldEvents.getEventsNone()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NEUTRAL, WorldEvents.getEventsNeutral()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.DAY, WorldEvents.getEventsDay()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NIGHT, WorldEvents.getEventsNight())
				);
	}
	
	@Override
	protected void initStateTask(WorldEvents initState) {
		this.stateTask = new StateTask<WorldEvents>(null) {
			
			@Override
			public void update() {
				
			}
		};
	}
}

enum EventTypes {
	NONE,
	NEUTRAL,
	DAY,
	NIGHT
}