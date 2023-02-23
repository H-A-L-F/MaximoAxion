package models.player;

import java.util.Map;
import java.util.Vector;
import java.util.stream.IntStream;

import constants.ConsoleColors;
import main.GameMaster;
import models.world.World;
import modules.Lib;

public class Player {
	// STATS
	public int maxHealth = 1000;
	public int maxHunger = 100;
	public int maxThirst = 100;
	public int maxShelter = 1000;
	public int health;
	public int hunger;
	public int thirst;
	public int shelter;
	
	// RESOURCES
	public int wood;
	public int food;
	public int water;
	public int herb;
	
	public Vector<String> messages;
	
	// ACTIONS
	public Vector<PlayerActions> availActions;
	public Map<ActionTypes, Vector<PlayerActions>> actions;
	
	// ATTRIBUTES
	public int damageAddons;
	
	private GameMaster gm;
	private World world;
	private PlayerActionController actController;
	private PlayerActionHandler actHandler;
	
	public Player(GameMaster gm, World world) {
		this.health = maxHealth;
		this.hunger = maxHunger;
		this.thirst = maxThirst;
		this.shelter = 0;
		
		this.wood = 0;
		this.food = 0;
		this.water = 0;
		this.herb = 0;
		
		this.messages = new Vector<>();
		
		this.availActions = new Vector<>();
		this.actions = PlayerActions.getActions();
		
		this.damageAddons = 0;
		
		this.gm = gm;
		this.world = world;
		this.actController = new PlayerActionController(this, gm.world);
		this.actHandler = new PlayerActionHandler(this, world);
	}
	
	public void handleInput(int in) {
		int res = in - 1;
		actHandler.actionHandler(availActions.get(res));
	}
	
	public void handleDamage(int dmg) {
		this.health -= dmg;
		
		if (health <= 0) gm.exit();
	}
	
	public void notifyStatusChange() {
		actController.update();
	}
	
	public void pause() {
		// TODO
	}
	
	public void resume() {
		// TODO
	}
	
	public void printMessages() {
		messages.forEach(m -> System.out.println(m));
		
		messages.clear();
	}
	
	public void printStats() {
		// Health: [||||||||||||||||||||] (100%)		
		System.out.printf(ConsoleColors.RED + "Health :" + ConsoleColors.RESET + " [" + ConsoleColors.GREEN + "%-20s" + ConsoleColors.RESET + "] (" + ConsoleColors.PURPLE + "%d%%" + ConsoleColors.RESET + ")\n", Lib.intToBar(health, maxHealth), Lib.getPercent(health, maxHealth));
		System.out.printf(ConsoleColors.RED + "Hunger :" + ConsoleColors.RESET + " [" + ConsoleColors.GREEN + "%-20s" + ConsoleColors.RESET + "] (" + ConsoleColors.PURPLE + "%d%%" + ConsoleColors.RESET + ")\n", Lib.intToBar(hunger, maxHunger), Lib.getPercent(hunger, maxHunger));
		System.out.printf(ConsoleColors.RED + "Thirst :" + ConsoleColors.RESET + " [" + ConsoleColors.GREEN + "%-20s" + ConsoleColors.RESET + "] (" + ConsoleColors.PURPLE + "%d%%" + ConsoleColors.RESET + ")\n", Lib.intToBar(thirst, maxThirst), Lib.getPercent(thirst, maxThirst));
		System.out.printf(ConsoleColors.RED + "Shelter:" + ConsoleColors.RESET + " [" + ConsoleColors.GREEN + "%-20s" + ConsoleColors.RESET + "] (" + ConsoleColors.PURPLE + "%d%%" + ConsoleColors.RESET + ")\n", Lib.intToBar(shelter, maxShelter), Lib.getPercent(shelter, maxShelter));
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
		String message = String.format("Enter 0 to pause\nWhat do you want to do? [1..%d]: ", availActions.size());
		System.out.printf(message);
	}
}
