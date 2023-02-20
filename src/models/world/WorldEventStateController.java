package models.world;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

import modules.Lib;
import modules.StateTask;

public class WorldEventStateController extends WorldStateController<WorldEvents> {
	private Vector<WorldEvent> timedEvent;
	private WorldEvents procEvent;
	private int timedEventDuration;
	private int procChance;
	
	private final Map<EventTypes, Vector<WorldEvents>> events;
	
	@Override
	public void start() {
		
	}

	@Override
	public void stop() {
		
	}
	
	public void procDay() {
		removeProcEvent();
		if(Lib.RNG(procChance)) {
			Collections.shuffle(events.get(EventTypes.DAY));
			setProcEvent(events.get(EventTypes.DAY).get(0));
		}
	}
	
	public void procNight() {
		removeProcEvent();
		if(Lib.RNG(procChance)) {
			Collections.shuffle(events.get(EventTypes.NIGHT));
			setProcEvent(events.get(EventTypes.NIGHT).get(0));
		}
	}
	
	private void addEvent(WorldEvents event) {
		
	}
	
	private void setProcEvent(WorldEvents event) {
		this.procEvent = event;
		world.wEvents.add(event);
	}
	
	private void removeProcEvent() {
		world.wEvents.remove(procEvent);
		this.procEvent = null;
	}
	
	public WorldEventStateController(World world, WorldEvents initState) {
		super(world, initState);
		this.events = Map.ofEntries(
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NONE, WorldEvents.getEventsNone()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NEUTRAL, WorldEvents.getEventsNeutral()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.DAY, WorldEvents.getEventsDay()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NIGHT, WorldEvents.getEventsNight())
				);
		
		init();
	}
	
	private void init() {
		this.timedEvent = new Vector<>();
		this.timedEventDuration = 15;
		this.procChance = 100;
		this.procEvent = WorldEvents.WORLD_NORMAL;
	}
	
	@Override
	protected void initStateTask(WorldEvents initState) {
		this.stateTask = new StateTask<WorldEvents>("World Events Task") {
			private final int CHANCE = 5;
			
			@Override
			public void update() {
				
			}
		};
	}
	
	class WorldEvent {
		public final WorldEvents event;
		public int timer;
		
		public WorldEvent(WorldEvents event, int timer) {
			super();
			this.event = event;
			this.timer = timer;
		}
	}
}

enum EventTypes {
	NONE,
	NEUTRAL,
	DAY,
	NIGHT
}