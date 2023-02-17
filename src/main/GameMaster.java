package main;

import models.player.Player;
import models.user.User;
import models.world.World;
import modules.Database;

public class GameMaster {
	public World world;
	public Player player;
	
	public User user;
	private Database db = Database.getInstance();
	
	public GameMaster(User user) {
		this.user = user;
	}
	
	public void initGame() {
		this.world = new World();
		this.player = new Player();
	}
	
	public void newGame() {
		initGame();
		play();
	}
	
	public void play() {
		
	}
	
	public void exit() {
		db.saveDatabase();
	}
}
