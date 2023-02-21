package models.player;

import modules.Lib;

public class PlayerActionHandler {

	private Player player;

	public PlayerActionHandler(Player player) {
		super();
		this.player = player;
	}

	public void actionHandler(PlayerActions a) {
		System.out.println(a.getMessage());
		switch (a) {
		case EXPLORE:
			explore();
			break;
		case REST:
			rest();
			break;
		case WOOD_GATHER:
			gatherWood();
			break;
		case BUILD_SHELTER:
			buildShelter();
			break;
		case IMPROVE_SHELTER:
			improveShelter();
			break;
		case FOOD_GATHER:
			gatherFood();
			break;
		case FOOD_CONSUME:
			consumeFood();
			break;
		case WATER_GATHER:
			gatherWater();
			break;
		case WATER_CONSUME:
			consumeWater();
			break;
		case HERBS_GATHER:
			gatherHerbs();
			break;
		case HERBS_CONSUME:
			consumeHerbs();
			break;
		case ATTACK:
			attack();
			break;
		case DEFEND:
			defend();
			break;
		case FLEE:
			flee();
			break;
		default:
			System.out.println("Invalid action");
			break;
		}
	}

	private void explore() {
		// code to explore
	}

	private void rest() {
		// code to rest
	}

	// UNTUK GATHER SEMUANYA GENERATE RANDOM KEMUDIAN INCREMENT.
	// MIN DAN MAX VALUE DIPENGARUHI WORLD STATUS.
	private void gatherWood() {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		player.wood += rand;
	}

	private void buildShelter() {
		// code to build shelter
	}

	private void improveShelter() {
		// code to improve shelter
	}

	private void gatherFood() {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		player.food += rand;
	}
	
	private void consumeFood() {
		// code to consume food
	}

	private void gatherWater() {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		player.water += rand;
	}

	private void consumeWater() {
		// code to consume water
	}

	private void gatherHerbs() {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		player.herb += rand;
	}

	private void consumeHerbs() {
		// code to consume herbs
	}

	private void attack() {
		// code to attack
	}

	private void defend() {
		// code to defend
	}

	private void flee() {
		// code to flee
	}
}
