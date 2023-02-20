package models.player;

import java.util.Map;
import java.util.Vector;
import java.util.stream.IntStream;

import main.GameMaster;
import modules.Lib;

public class Player {
	// STATS
	private int maxHealth = 100;
	private int maxHunger = 100;
	private int maxThirst = 100;
	public int health;
	public int hunger;
	public int thirst;
	
	// RESOURCES
	public int wood;
	public int food;
	public int water;
	public int herb;
	
	// ACTIONS
	public Vector<PlayerActions> availActions;
	public Map<ActionTypes, Vector<PlayerActions>> actions;
	
	private GameMaster gm ;
	private PlayerActionController actController;
	
	public Player(GameMaster gm) {
		this.health = 100;
		this.hunger = 100;
		this.thirst = 100;
		
		this.wood = 0;
		this.food = 0;
		this.water = 0;
		this.herb = 0;
		
		this.availActions = new Vector<>();
		this.actions = PlayerActions.getActions();
		
		this.gm = gm;
		this.actController = new PlayerActionController(this, gm.world);
	}
	
	public void handleInput(String str) {
		
	}
	
	public void notifyStatusChange() {
		actController.update();
	}
	
	public void printStats() {
		// Health: [||||||||||||||||||||] (100%)		
		System.out.printf("Health: [%20s] (%d)\n", Lib.intToBar(health, maxHealth), Lib.getPercent(health, maxHealth));
		System.out.printf("Hunger: [%20s] (%d)\n", Lib.intToBar(hunger, maxHunger), Lib.getPercent(hunger, maxHunger));
		System.out.printf("Thirst: [%20s] (%d)\n", Lib.intToBar(thirst, maxThirst), Lib.getPercent(thirst, maxThirst));
	}
	
	public void printResources() {
		System.out.println("Resources:");
		System.out.println("- Wood: " + wood);
		System.out.println("- Food: " + food);
		System.out.println("- Water: " + water);
		System.out.println("- Herb: " + herb);
	}
	
	public void printActions() {
		IntStream.range(0, availActions.size())
			.forEach(i -> {
				System.out.printf("%d. %s\n", i + 1, availActions.get(i).getMessage());
			});
	}
}
