package models.player;

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
	
	public Player() {
		this.health = 100;
		this.hunger = 100;
		this.thirst = 100;
		
		this.wood = 0;
		this.food = 0;
		this.water = 0;
		this.herb = 0;
	}
	
	public void printStats() {
//		Health: [||||||||||||||||||||] (100%)		
		System.out.printf("Health: [%20s] (%d%)\n", Lib.intToBar(health, maxHealth), Lib.getPercent(health, maxHealth));
		System.out.printf("Hunger: [%20s] (%d%)\n", Lib.intToBar(hunger, maxHunger), Lib.getPercent(hunger, maxHunger));
		System.out.printf("Thirst: [%20s] (%d%)\n", Lib.intToBar(thirst, maxThirst), Lib.getPercent(thirst, maxThirst));
	}
	
	public void printResources() {
		System.out.println("Resources:");
		System.out.println("- Wood: " + wood);
		System.out.println("- Food: " + food);
		System.out.println("- Water: " + water);
		System.out.println("- Herb: " + herb);
	}
	
	public void printActions() {
		
	}
}
