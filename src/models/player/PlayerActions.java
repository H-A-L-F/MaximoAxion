package models.player;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

import models.world.WorldEvents;

public enum PlayerActions {
	EXPLORE,
	REST,
	
	WOOD_GATHER,
	BUILD_SHELTER,
	IMPROVE_SHELTER,
	
	FOOD_GATHER,
	FOOD_CONSUME,
	
	WATER_GATHER,
	WATER_CONSUME,
	
	HERBS_GATHER,
	HERBS_CONSUME,
	
	ATTACK,
	DEFEND,
	FLEE;
	
	public static Vector<PlayerActions> getFreeActions() {
		return new Vector<>(Arrays.asList(EXPLORE, REST));
	}
	
	public static Vector<PlayerActions> getWoodActions() {
		return new Vector<>(Arrays.asList(WOOD_GATHER, BUILD_SHELTER, IMPROVE_SHELTER));
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
	
	enum ActionTypes {
		FREE,
		WOOD,
		FOOD,
		WATER,
		HERBS,
		ENCOUNTER
	}
}
