package models.player;

import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

import models.world.World;
import models.world.WorldEvents;

public class PlayerActionController {
	
	// PLAYER ACTIONS
	public Vector<PlayerActions> availActions;
	public Map<ActionTypes, Vector<PlayerActions>> actions;
	
	// STATUS
	private Vector<ActionStatus> status;
	
	private Player player;
	private World world;

	public PlayerActionController(Player player, World world) {
		super();
		this.player = player;
		this.world = world;
		this.status = new Vector<>();
		
		initActions();
	}
	
	private void initActions() {
		this.availActions = player.availActions;
		this.actions = player.actions;
		
		// UNTUK SEKARANG GAADA FREE
//		availActions.addAll(actions.get(ActionTypes.FREE));
		
		availActions.addAll(actions.get(ActionTypes.WOOD));
		availActions.addAll(actions.get(ActionTypes.FOOD));
		availActions.addAll(actions.get(ActionTypes.WATER));
		availActions.addAll(actions.get(ActionTypes.HERBS));
		
		// UNTUK SEKARANG GAADA ENCOUNTER
//		availActions.addAll(actions.get(ActionTypes.ENCOUNTER));
	}
	
	public void update() {
		boolean waterFlag = false;
		boolean gatherFlag = false;
		for (WorldEvents e : world.wEvents) {
			actionFilter(e);
			if(e == WorldEvents.SOLAR_ECLIPSE || e == WorldEvents.BLACKOUT) gatherFlag = true;
			if(e == WorldEvents.SCORCHING_SUN) waterFlag = true;
		}
		
		if(!waterFlag) player.availActions.add(PlayerActions.WATER_GATHER);
		if(!gatherFlag) player.availActions.addAll(Arrays.asList(PlayerActions.WOOD_GATHER, PlayerActions.FOOD_GATHER, PlayerActions.WATER_GATHER, PlayerActions.HERBS_GATHER));
	}
	
	private void actionFilter(WorldEvents e) {
		switch (e) {
		case SCORCHING_SUN:
			status.add(ActionStatus.NO_WATER);
			player.availActions.removeIf(a -> a == PlayerActions.WATER_GATHER);
			break;
		case BLACKOUT:
			status.add(ActionStatus.NO_GATHER);
			player.availActions.removeIf(a -> a == PlayerActions.FOOD_GATHER || a == PlayerActions.WOOD_GATHER || a == PlayerActions.WATER_GATHER || a == PlayerActions.HERBS_GATHER);
			break;
		case SOLAR_ECLIPSE:
			status.add(ActionStatus.NO_GATHER);
			player.availActions.removeIf(a -> a == PlayerActions.FOOD_GATHER || a == PlayerActions.WOOD_GATHER || a == PlayerActions.WATER_GATHER || a == PlayerActions.HERBS_GATHER);
			break;
		default:
			break;
		}
	}
	
	enum ActionStatus {
		NO_GATHER,
		NO_WATER;
	}
}
