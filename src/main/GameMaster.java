package main;

import models.player.Player;
import models.user.User;
import models.world.World;
import modules.Database;
import modules.Lib;

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
		Lib.clear();
		world.printWorld();
		player.printActions();
	}
	
	private void startScreen() {
		dayPass();
	}
	
	public void notifyStatusChange() {
		updateScreen();
	}
	
	public void dayPass() {
		world.printDay();
		world.printWorld();
		player.printStats();
		player.printResources();
		player.printActions();
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
