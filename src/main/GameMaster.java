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
		this.world = new World(this);
		this.player = new Player();
	}
	
	private void updateScreen() {
		world.printWorld();
		player.printActions();
	}
	
	private void startScreen() {
		world.printDay();
		System.out.println();
	}
	
	public void notifyStatusChange() {
		updateScreen();
	}
	
	public void newGame() {
		initGame();
		play();
	}
	
	public void play() {
		startScreen();
		world.startWorld();
	}
	
	public void exit() {
		db.saveDatabase();
	}
}
