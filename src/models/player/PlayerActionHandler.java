package models.player;

import java.util.Arrays;
import java.util.Vector;

import models.world.World;
import models.world.WorldEvents;
import modules.Lib;

public class PlayerActionHandler {

	private Player player;
	private World world;

	public PlayerActionHandler(Player player, World world) {
		super();
		this.player = player;
		this.world = world;
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
			gather(a);
			break;
		case BUILD_SHELTER:
			buildShelter();
			break;
		case IMPROVE_SHELTER:
			improveShelter();
			break;
		case FOOD_GATHER:
			gather(a);
			break;
		case FOOD_CONSUME:
			consumeFood();
			break;
		case WATER_GATHER:
			gather(a);
			break;
		case WATER_CONSUME:
			consumeWater();
			break;
		case HERBS_GATHER:
			gather(a);
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
	
	private void interruption() {
		int easy = 0;
		int med = 0;
		int hard = 0;
		
		ActionEvents event = ActionEvents.FALL;
		
		switch (world.wTime) {
		case DUSK:
			easy = 50;
			med = 30;
			hard = 20;
			break;
		case NIGHT:
			easy = 30;
			med = 50;
			hard = 20;
			break;
		case MIDNIGHT:
			easy = 20;
			med = 30;
			hard = 50;
			break;
		default:
			break;
		}
		
		int rand = Lib.RNG(1, 100);
		
		if(rand <= easy) {
			event = ActionEvents.FALL;
		} else if(rand <= easy + med) {
			event = ActionEvents.WOLF_ATTACK;
		} else if(rand <= easy + med + hard) {
			event = ActionEvents.SHADOW_ATTACK;
		}
		
		int additionalDmg = Lib.RNG(1, 3);
		int dmg = additionalDmg + event.getDamage();
		String msg = String.format("%syou took %d damage.", event.getMessage(), dmg);
		player.messages.add(msg);
		
		player.health -= dmg;
	}

	private void explore() {
		// code to explore
	}

	private void rest() {
		// code to rest
	}

	// UNTUK GATHER SEMUANYA GENERATE RANDOM KEMUDIAN INCREMENT.
	// MIN DAN MAX VALUE DIPENGARUHI WORLD STATUS.
	private void gather(PlayerActions a) {
		int min = 1;
		int max = 5;
		
		int rand = Lib.RNG(min, max);
		
		StringBuilder msg = new StringBuilder("You obtained " + rand);
		switch (a) {
		case WOOD_GATHER:
			msg.append(" wood.");
			player.wood += rand;
			break;
		case FOOD_GATHER:
			msg.append(" food.");
			player.food += rand;
			break;
		case WATER_GATHER:
			msg.append(" water.");
			player.water += rand;
			break;
		case HERBS_GATHER:
			msg.append(" herb.");
			player.herb += rand;
			break;
		default:
			break;
		}
		
		player.messages.add(msg.toString());
		
		interruption();
	}

	// UNTUK CONSUME SEMUANYA CEK BILA PERLU LEBIH DARI 10 ATAU TIDAK.
	// JIKA PERLU LEBIH HANYA KURANGIN 10, JIKA TIDAK SECUKUPNYA SAJA.
	// JANGAN LUPA SESUAIKAN JUMLAH YANG DIPAKAI DENGAN JUMLAH RESOURCE
	// YANG DIMILIKI PLAYER.
	private void buildShelter() {
		int required = player.maxShelter - player.shelter;
		
		if(required >= 10) required = 10;
		
		if(player.wood >= required) {
			player.wood -= required;
			player.shelter += required;
		} else {
			player.shelter += player.wood;
			player.wood = 0;
		}
	}

	private void improveShelter() {
		// code to improve shelter
	}

	private void consumeFood() {
		int required = player.maxHunger - player.hunger;
		
		if(required >= 10) required = 10;
			
		if(player.food >= required) {
			player.food -= required;
			player.hunger += required;
		} else {
			player.hunger += player.food;
			player.food = 0;
		}
		
	}

	private void consumeWater() {
		int required = player.maxThirst - player.thirst;
		
		if(required >= 10) required = 10;
		
		if(player.water >= required) {
			player.water -= required;
			player.thirst += required;
		} else {
			player.thirst += player.water;
			player.water = 0;
		}
	}

	private void consumeHerbs() {
		int required = player.maxHealth - player.health;
		
		if(required >= 10) required = 10;
		
		if(player.herb >= required) {
			player.herb -= required;
			player.health += required;
		} else {
			player.health += player.herb;
			player.herb = 0;
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

enum ActionEvents {
	// INTERRUPTION
	FALL("It's too dark, you trip sice you can't see clearly - ", 5),
	WOLF_ATTACK("This is a wolf territory and they don't like intruders - ", 8),
	SHADOW_ATTACK("A figure attacks you from the dark - ", 15);
	
	private String msg;
	private int dmg;
	
	ActionEvents(String msg, int dmg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return this.msg;
	}
	
	public int getDamage() {
		return this.dmg;
	}
	
	public static Vector<ActionEvents> getEventsInterrupt() {
		return new Vector<>(Arrays.asList(FALL, WOLF_ATTACK, SHADOW_ATTACK));
	}
}
