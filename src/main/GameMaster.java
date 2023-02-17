package main;

import models.player.Player;
import models.user.User;
import modules.Database;

public class GameMaster {
	public User user;
	public Player player;
	private Database db = Database.getInstance();
	
	public GameMaster(User user) {
		this.user = user;
	}
	
	public void initGame() {
		
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
