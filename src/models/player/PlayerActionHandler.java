package models.player;

import modules.Lib;

public class PlayerActionHandler {

	private Player player;

	public PlayerActionHandler(Player player) {
		super();
		this.player = player;
	}

	public void actionHandler(PlayerActions a) {
		player.messages.add(a.getMessage());
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

	// UNTUK CONSUME SEMUANYA CEK BILA PERLU LEBIH DARI 10 ATAU TIDAK.
	// JIKA PERLU LEBIH HANYA KURANGIN 10, JIKA TIDAK SECUKUPNYA SAJA.
	// JANGAN LUPA SESUAIKAN JUMLAH YANG DIPAKAI DENGAN JUMLAH RESOURCE
	// YANG DIMILIKI PLAYER.
	private void buildShelter() {
		int required = player.maxShelter - player.shelter;
		
//		if(player.wood <= 0) {
//			System.out.println("Not enough wood!");
//			return;
//		}
		
		if(required >= 10) {
			required = 10;
			if(player.wood >= required) {
				player.wood -= 10;
				player.shelter += 10;
			} else {
				player.shelter += player.wood;
				player.wood = 0;
			}
		} else {
			if(player.wood >= required) {
				player.wood -= required;
				player.shelter += required;
			} else {
				player.shelter += player.wood;
				player.wood = 0;
			}
		}
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
		int required = player.maxHunger - player.hunger;
		
		if(required >= 10) {
			required = 10;
			if(player.food >= required) {
				player.food -= 10;
				player.hunger += 10;
			} else {
				player.hunger += player.food;
				player.food = 0;
			}
		} else {
			if(player.food >= required) {
				player.food -= required;
				player.hunger += required;
			} else {
				player.hunger += player.food;
				player.food = 0;
			}
		}
	}

	private void gatherWater() {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		player.water += rand;
	}

	private void consumeWater() {
		int required = player.maxThirst - player.thirst;
		
		if(required >= 10) {
			required = 10;
			if(player.water >= required) {
				player.water -= 10;
				player.thirst += 10;
			} else {
				player.thirst += player.water;
				player.water = 0;
			}
		} else {
			if(player.water >= required) {
				player.water -= required;
				player.thirst += required;
			} else {
				player.thirst += player.water;
				player.water = 0;
			}
		}
	}

	private void gatherHerbs() {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		player.herb += rand;
	}

	private void consumeHerbs() {
		int required = player.maxHealth - player.health;
		
		if(required >= 10) {
			required = 10;
			if(player.herb >= required) {
				player.herb -= 10;
				player.health += 10;
			} else {
				player.health += player.herb;
				player.herb = 0;
			}
		} else {
			if(player.herb >= required) {
				player.herb -= required;
				player.health += required;
			} else {
				player.health += player.herb;
				player.herb = 0;
			}
		}
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
