package models.player;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

public enum PlayerActions {
	EXPLORE("Venture out", "You venture out into the unknown, searching for resources and adventure."),
	REST("Take a break", "You take a break from your journey and rest for a while, regaining your energy."),

	WOOD_GATHER("Gather wood", "You swing your ax and chop some wood from a nearby tree."),
	BUILD_SHELTER("Build shelter", "You use the wood and other resources you have to build a shelter."),
	IMPROVE_SHELTER("Improve shelter", "You make some improvements to your shelter to make it more comfortable and secure."),

	FOOD_GATHER("Hunt for food", "You search the nearby area for food and manage to catch a rabbit."),
	FOOD_CONSUME("Consume food", "You consume some food and feel your strength returning."),

	WATER_GATHER("Collect water", "You find a stream and collect some water in a container."),
	WATER_CONSUME("Drink water", "You take a drink of water and feel refreshed."),

	HERBS_GATHER("Gather herbs", "You search the area for herbs and find some useful plants."),
	HERBS_CONSUME("Use herbs", "You use the herbs you collected to make a healing potion and drink it."),

	ATTACK("Prepare to attack", "You take up your weapon and prepare to fight your enemies."),
	DEFEND("Prepare to defend", "You take a defensive stance, preparing to protect yourself from incoming attacks."),
	FLEE("Flee from danger", "You run away from danger, hoping to escape and find safety.");
	
	private final String MENU;
	private final String MSG;
	
	PlayerActions(String MENU, String MSG) {
		this.MENU = MENU;
		this.MSG = MSG;
	}
	
	public String getMenu() {
		return this.MENU;
	}
	
	public String getMessage() {
		return this.MSG;
	}
	
	public static Vector<PlayerActions> getFreeActions() {
		return new Vector<>(Arrays.asList(EXPLORE, REST));
	}
	
	public static Vector<PlayerActions> getWoodActions() {
		// TEMPORARY REMOVE THE IMPROVE SHELTER
//		return new Vector<>(Arrays.asList(WOOD_GATHER, BUILD_SHELTER, IMPROVE_SHELTER));
		return new Vector<>(Arrays.asList(WOOD_GATHER, BUILD_SHELTER));
	}
	
	public static Vector<PlayerActions> getFoodActions() {
		return new Vector<>(Arrays.asList(FOOD_GATHER, FOOD_CONSUME));
	}
	
	public static Vector<PlayerActions> getWaterActions() {
		return new Vector<>(Arrays.asList(WATER_GATHER, WATER_CONSUME));
	}
	
	public static Vector<PlayerActions> getHerbsActions() {
		return new Vector<>(Arrays.asList(HERBS_GATHER, HERBS_CONSUME));
	}
	
	public static Vector<PlayerActions> getEncounterActions() {
		return new Vector<>(Arrays.asList(ATTACK, DEFEND, FLEE));
	}
	
	public static Map<ActionTypes, Vector<PlayerActions>> getActions() {
		return Map.ofEntries(
				  new AbstractMap.SimpleEntry<ActionTypes, Vector<PlayerActions>>(ActionTypes.FREE, getFreeActions()),
				  new AbstractMap.SimpleEntry<ActionTypes, Vector<PlayerActions>>(ActionTypes.WOOD, getWoodActions()),
				  new AbstractMap.SimpleEntry<ActionTypes, Vector<PlayerActions>>(ActionTypes.FOOD, getFoodActions()),
				  new AbstractMap.SimpleEntry<ActionTypes, Vector<PlayerActions>>(ActionTypes.WATER, getWaterActions()),
				  new AbstractMap.SimpleEntry<ActionTypes, Vector<PlayerActions>>(ActionTypes.HERBS, getHerbsActions()),
				  new AbstractMap.SimpleEntry<ActionTypes, Vector<PlayerActions>>(ActionTypes.ENCOUNTER, getEncounterActions())
				);
	}
}
