package models.world;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ScheduledFuture;

import modules.ControlledThread;
import modules.Lib;
import modules.StateTask;

public class WorldEventStateController extends WorldStateController<WorldEvents> {
	private Vector<WorldEvent> timedEvent;
	private WorldEvents procEvent;
	private int timedEventDuration;
	private int procChance;
	
	private Map<EventTypes, Vector<WorldEvents>> availProc;
	private Vector<EventProccer> eProccers;
	private EventTypes time;
	
	private final Map<EventTypes, Vector<WorldEvents>> events;
	
	@Override
	public void start() {
//		stateTask.resume();
		
		eProccers.forEach(e -> {
			e.resume();
		});
	}

	@Override
	public void stop() {
//		stateTask.pause();
		
		eProccers.forEach(e -> {
			e.pause();
		});
	}
	
	public void procDay() {
		time = EventTypes.DAY;
		
		removeProcEvent();
		if(Lib.RNG(procChance)) {
//			Collections.shuffle(events.get(EventTypes.DAY));
//			setProcEvent(events.get(EventTypes.DAY).get(0));
			setProcEvent(WorldEvents.SCORCHING_SUN);
		}
	}
	
	public void procNight() {
		time = EventTypes.NIGHT;
		
		removeProcEvent();
		if(Lib.RNG(procChance)) {
//			Collections.shuffle(events.get(EventTypes.NIGHT));
//			setProcEvent(events.get(EventTypes.NIGHT).get(0));
			setProcEvent(WorldEvents.BLACKOUT);
		}
	}
	
	private void addEvent() {
		int bi = Lib.RNG(0, 1);
		
		Collections.shuffle(events.get(EventTypes.NEUTRAL));
		
		WorldEvent e = null;
		if(bi == 0) e = new WorldEvent(world, events.get(EventTypes.NEUTRAL).get(0), timedEventDuration);
		else if(time == EventTypes.DAY) e = new WorldEvent(world, events.get(EventTypes.DAY).get(0), timedEventDuration);
		else if(time == EventTypes.NIGHT) e = new WorldEvent(world, events.get(EventTypes.NIGHT).get(0), timedEventDuration);
		
		final WorldEvent eClone = e;
		
		timedEvent.add(eClone);
		world.wEvents.add(eClone.getEvent());
		
//		tp.taskAtRateAndDuration(0, 1, timedEventDuration, e);
		ScheduledFuture<?> scheduledFuture = tp.scheduleTaskAtRate(0, 1, eClone);
		if(eClone.getEvent() == WorldEvents.SOLAR_ECLIPSE || eClone.getEvent() == WorldEvents.BLACKOUT) {
			world.gm.player.addGather();
		} else if(eClone.getEvent() == WorldEvents.SCORCHING_SUN) {
			world.gm.player.addWaterGather();
		} else {
			tp.schedule(timedEventDuration, () -> {
				eClone.stop();
				scheduledFuture.cancel(true);
				world.wEvents.remove(eClone.getEvent());
				timedEvent.remove(eClone);
			});
		}
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
//				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NONE, WorldEvents.getEventsNone()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NEUTRAL, WorldEvents.getEventsNeutral()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.DAY, WorldEvents.getEventsDay()),
				  new AbstractMap.SimpleEntry<EventTypes, Vector<WorldEvents>>(EventTypes.NIGHT, WorldEvents.getEventsNight())
				);
		this.availProc = events;
		this.availProc.get(EventTypes.DAY).remove(WorldEvents.SCORCHING_SUN);
		this.availProc.get(EventTypes.NIGHT).remove(WorldEvents.BLACKOUT);
		
		init();
		initProccer();
	}
	
	private void init() {
		this.timedEvent = new Vector<>();
		this.timedEventDuration = 10;
		this.procChance = 5;
		this.procEvent = WorldEvents.WORLD_NORMAL;
		this.time = EventTypes.DAY;
	}
	
	private void initProccer() {
		this.eProccers = new Vector<>();
		this.eProccers.add(new EventProccer());
		
		eProccers.forEach(e -> {
			tp.scheduleTaskAtRate(0, 1, e);
			e.pause();
		});
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
	
	class EventProccer extends StateTask<WorldEvents> {

		public EventProccer() {
			super("Event Proccer");
			this.timePassed = 0;
		}

		@Override
		public void update() {
			timePassed += 0.5;
			
			int rand = Lib.RNG(1, 100);
			if(rand <= timePassed) {
				timePassed = 0;
				
				addEvent();
			}
		}
		
	}
	
}

enum EventTypes {
	NONE,
	NEUTRAL,
	DAY,
	NIGHT
}