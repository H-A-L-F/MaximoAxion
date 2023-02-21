package models.player;

import java.util.Map;
import java.util.Vector;
import java.util.stream.IntStream;

import main.GameMaster;
import modules.Lib;

public class Player {
	// STATS
	public int maxHealth = 100;
	public int maxHunger = 100;
	public int maxThirst = 100;
	public int maxShelter = 100;
	public int health;
	public int hunger;
	public int thirst;
	public int shelter;
	
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
	private PlayerActionHandler actHandler;
	
	public Player(GameMaster gm) {
		this.health = 100;
		this.hunger = 100;
		this.thirst = 100;
		this.shelter = 0;
		
		this.wood = 0;
		this.food = 0;
		this.water = 0;
		this.herb = 0;
		
		this.availActions = new Vector<>();
		this.actions = PlayerActions.getActions();
		
		this.gm = gm;
		this.actController = new PlayerActionController(this, gm.world);
		this.actHandler = new PlayerActionHandler(this);
	}
	
	public void handleInput(int in) {
		int res = in - 1;
		actHandler.actionHandler(availActions.get(res));
	}
	
	public void notifyStatusChange() {
		actController.update();
	}
	
	public void printStats() {
		// Health: [||||||||||||||||||||] (100%)		
		System.out.printf("Health: [%-20s] (%d%%)\n", Lib.intToBar(health, maxHealth), Lib.getPercent(health, maxHealth));
		System.out.printf("Hunger: [%-20s] (%d%%)\n", Lib.intToBar(hunger, maxHunger), Lib.getPercent(hunger, maxHunger));
		System.out.printf("Thirst: [%-20s] (%d%%)\n", Lib.intToBar(thirst, maxThirst), Lib.getPercent(thirst, maxThirst));
		System.out.printf("Shelter: [%-20s] (%d%%)\n", Lib.intToBar(shelter, maxShelter), Lib.getPercent(shelter, maxShelter));
	}
	
	public void printResources() {
		System.out.println("Resources:");
		System.out.println("- Wood: " + wood);
		System.out.println("- Food: " + food);
		System.out.println("- Water: " + water);
		System.out.println("- Herb: " + herb);
	}
	
	public void printActions() {
		System.out.println("Actions:");
		IntStream.range(0, availActions.size())
			.forEach(i -> {
				System.out.printf("%d. %s\n", i + 1, availActions.get(i).getMenu());
			});
		
		System.out.println();
		String message = String.format("What do you want to do? [1..%d]", availActions.size());
		System.out.println(message);
	}
}
