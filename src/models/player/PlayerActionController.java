package models.player;

import java.util.Map;
import java.util.Vector;

import models.world.World;
import models.world.WorldEvents;

public class PlayerActionController {
	
	// PLAYER ACTIONS
	public Vector<PlayerActions> availActions;
	public Map<ActionTypes, Vector<PlayerActions>> actions;
	
	private Player player;
	private World world;

	public PlayerActionController(Player player, World world) {
		super();
		this.player = player;
		this.world = world;
		
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
		for (WorldEvents e : world.wEvents) {
			actionFilter(e);
		}
	}
	
	private void actionFilter(WorldEvents e) {
		switch (e) {
		case SCORCHING_SUN:
			player.availActions.removeIf(a -> a == PlayerActions.WATER_GATHER);
			break;
		case BLACKOUT:
			player.availActions.removeIf(a -> a == PlayerActions.FOOD_GATHER || a == PlayerActions.WOOD_GATHER || a == PlayerActions.WATER_GATHER || a == PlayerActions.HERBS_GATHER);
			break;
		case SOLAR_ECLIPSE:
			player.availActions.removeIf(a -> a == PlayerActions.FOOD_GATHER || a == PlayerActions.WOOD_GATHER || a == PlayerActions.WATER_GATHER || a == PlayerActions.HERBS_GATHER);
			break;
		default:
			break;
		}
	}
}
