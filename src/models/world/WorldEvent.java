package models.world;

import modules.ControlledThread;
import modules.Lib;

class WorldEvent extends ControlledThread {
	public final WorldEvents event;
	public int timer;
	public int damage;
	
	private World world;
	
	public WorldEvent(World world, WorldEvents event, int timer) {
		super("World Event - " + event);
		this.world = world;
		this.event = event;
		this.timer = timer;
		
		setDamage();
	}
	
	private void setDamage() {
		switch (event) {
		case VOLCANIC_ERUPTION:
			this.damage = Lib.RNG(1, 5);
			break;
		case MASSIVE_THUNDERSTORM:
			this.damage = Lib.RNG(3, 7);
			break;
		case MASSIVE_BIRD_MIGRATION:
			this.damage = Lib.RNG(1, 3);
			break;
		case MASSIVE_EARTHQUAKE:
			this.damage = Lib.RNG(1, 4);
			break;
		case SOLAR_ECLIPSE:
			this.damage = Lib.RNG(4, 8);
			break;
		case BLOOD_MOON:
			this.damage = Lib.RNG(5, 10);
			break;
		case MASSIVE_BATS_INVASION:
			this.damage = Lib.RNG(3, 5);
			break;
		case ALIEN_INVASION:
			this.damage = Lib.RNG(5, 8);
			break;
		default:
			break;
		}
	}
	
	public WorldEvents getEvent() {
		return this.event;
	}
	
	@Override
	public void update() {
		if(world.gm.player.health <= 0) {
			this.stop();
			return;
		}
		world.harmPlayer(damage);
		world.eventMsgs.add("You took " + damage + " from the ongoing event.");
		world.notifyStatusChange();
	}
}