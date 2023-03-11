package main;

import models.player.Player;
import models.user.User;
import models.world.World;
import modules.Database;
import modules.Lib;

public class GameMaster {
	private GameState state;
	
	public World world;
	public Player player;
	
	public User user;
	private InputManager im;
	private Database db = Database.getInstance();
	
	public GameMaster(InputManager im, User user) {
		this.im = im;
		this.user = user;
	}
	
	public void initGame() {
		this.world = new World(this);
		this.player = new Player(this, world);
	}
	
	private synchronized void updateScreen() {
//		Lib.clear();
//		world.printWorld();
//		System.out.println();
//		player.printActions();
		
		if(state == GameState.EXIT) {
			return;
		}
		
		Lib.clear();
		world.printWorld();
		System.out.println();
		player.printMessages();
		System.out.println();
		player.printStats();
		System.out.println();
		player.printResources();
		System.out.println();
		player.printActions();
	}
	
	public void deathScreen() {
		Lib.clear();
		System.out.println("\r\n"
				+ "██╗░░░██╗░█████╗░██╗░░░██╗  ██████╗░██╗███████╗██████╗░\r\n"
				+ "╚██╗░██╔╝██╔══██╗██║░░░██║  ██╔══██╗██║██╔════╝██╔══██╗\r\n"
				+ "░╚████╔╝░██║░░██║██║░░░██║  ██║░░██║██║█████╗░░██║░░██║\r\n"
				+ "░░╚██╔╝░░██║░░██║██║░░░██║  ██║░░██║██║██╔══╝░░██║░░██║\r\n"
				+ "░░░██║░░░╚█████╔╝╚██████╔╝  ██████╔╝██║███████╗██████╔╝\r\n"
				+ "░░░╚═╝░░░░╚════╝░░╚═════╝░  ╚═════╝░╚═╝╚══════╝╚═════╝░\r\n");
		
		System.out.println("Press Enter..");
	}
	
	private void handleInput(int res) {
		player.handleInput(res);
		updateScreen();
	}
	
	private void startScreen() {
		dayPass();
	}
	
	public void handleInput(String str) {
		int res = Integer.parseInt(str);
		if(res == 0) {
			if(state == GameState.PLAYING) pauseGame();
			else if(state == GameState.PAUSE) resumeGame();
		}
		else handleInput(res);
	}
	
	public synchronized void harmPlayer(int dmg) {
		player.handleDamage(dmg);
	}
	
	private void pauseGame() {
		world.pause();
		player.pause();
		state = GameState.PAUSE;
	}
	
	private void resumeGame() {
		world.resume();
		player.resume();
		state = GameState.PLAYING;
	}
	
	private void stopGame() {
		world.stop();
		player.stop();
		state = GameState.EXIT;
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
	
	private void updateUserScore() {
		user.setDay(world.day);
	}
	
	public void newGame() {
		initGame();
		play();
	}
	
	public void play() {
		startScreen();
		world.startWorld();
//		in.start();
		state = GameState.PLAYING;
	}
	
	public void exit() {
		if(state == GameState.EXIT) return;
		stopGame();
		updateUserScore();
		db.saveDatabase();
		deathScreen();
		im.in.update();
//		im.closeInput();
//		im.exitGame();
	}
}

enum GameState {
	PLAYING,
	PAUSE,
	EXIT
}
