package main;

import models.player.Player;
import models.user.User;
import models.world.World;
import modules.Database;
import modules.Lib;

public class GameMaster {
	public World world;
	public Player player;
	private GameInput in;
	
	public User user;
	private Database db = Database.getInstance();
	
	public GameMaster(User user) {
		this.user = user;
	}
	
	public void initGame() {
		this.world = new World(this);
		this.player = new Player(this);
		this.in = new GameInput(this);
	}
	
	private void updateScreen() {
		Lib.clear();
		world.printWorld();
		System.out.println();
		player.printActions();
	}
	
	private void startScreen() {
		dayPass();
	}
	
	public void input(String str) {
		int res = Integer.parseInt(str);
		if(res == 0) pauseGame();
		else player.handleInput(res);
	}
	
	private void pauseGame() {
		
	}
	
	public void notifyStatusChange() {
		player.notifyStatusChange();
		updateScreen();
	}
	
	public void dayPass() {
		Lib.clear();
		world.printDay();
		System.out.println();
		world.printWorld();
		System.out.println();
		player.printStats();
		System.out.println();
		player.printResources();
		System.out.println();
		player.printActions();
	}
	
	public void newGame() {
		initGame();
		play();
	}
	
	public void play() {
		startScreen();
		world.startWorld();
		in.start();
	}
	
	public void exit() {
		db.saveDatabase();
	}
}
