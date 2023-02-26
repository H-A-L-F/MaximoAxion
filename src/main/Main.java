package main;

import java.util.List;
import java.util.Optional;

import models.user.User;
import modules.ConsoleInput;
import modules.Database;
import modules.Lib;

public class Main {
	private volatile boolean paused;
	private MainState state;
	private ConsoleInput in;
	
	private Database db = Database.getInstance();
	private GameMaster gm;
	private InputManager im;
	
	public Main(InputManager im) {
		this.paused = false;
		this.state = MainState.MAIN;
		this.im = im;
	}

	public void printTitle() {
		System.out.println(
				"\r\n" + "███╗░░░███╗░█████╗░██╗░░██╗██╗███╗░░░███╗░█████╗░░█████╗░██╗░░██╗██╗░█████╗░███╗░░██╗\r\n"
						+ "████╗░████║██╔══██╗╚██╗██╔╝██║████╗░████║██╔══██╗██╔══██╗╚██╗██╔╝██║██╔══██╗████╗░██║\r\n"
						+ "██╔████╔██║███████║░╚███╔╝░██║██╔████╔██║██║░░██║███████║░╚███╔╝░██║██║░░██║██╔██╗██║\r\n"
						+ "██║╚██╔╝██║██╔══██║░██╔██╗░██║██║╚██╔╝██║██║░░██║██╔══██║░██╔██╗░██║██║░░██║██║╚████║\r\n"
						+ "██║░╚═╝░██║██║░░██║██╔╝╚██╗██║██║░╚═╝░██║╚█████╔╝██║░░██║██╔╝╚██╗██║╚█████╔╝██║░╚███║\r\n"
						+ "╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝╚═╝░░░░░╚═╝░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░╚════╝░╚═╝░░╚══╝\r\n");
	}

	private void optHome() {
		System.out.println("1. Play");
		System.out.println("2. Register");
		System.out.println("3. Highscore");
		System.out.println("4. Exit");
	}

	private void menuHome() {
		boolean run = true;
		int opt = 0;

		Lib.clear();
		while (run) {
			if (paused) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						// Handle interruption
					}
				}
			}

			optHome();
			opt = in.getIntWMSG(">> ", 1, 4);

			switch (opt) {
			case 1:
				play();
				break;
			case 2:
				register();
				break;
			case 3:
				highscore();
				break;
			default:
				run = false;
				db.saveDatabase();
				break;
			}
		}
	}

	private void play() {
		String username = in.getStrWMSG("Enter username [5..20]: ", 5, 20);
		String password = in.getStrWMSG("Enter password [5..20]: ", 5, 20);

		Optional<User> res = db.findUser(username, password);
		if (res.isPresent()) {
			
			pause();
			im.startGame(res.get());
			return;
		}

		System.out.println("User not found...");
		in.pressEnter();
	}

	private void register() {
		String username;
		while (true) {
			username = in.getStrWMSG("Enter username [5..20]: ", 5, 20);
			if (!db.findUsername(username))
				break;
		}

		String password = in.getStrWMSG("Enter password [5..20]: ", 5, 20);

		db.addUser(new User(username, password));
	}

	private void highscore() {
		List<User> users = db.getTopScore(3);

		System.out.println("========Highscore========");
		for (User user : users) {
			System.out.printf("%-20s: %3d\n", user.getUsername(), user.getDay());
		}
		System.out.printf("\n\n\n");

		in.pressEnter();
	}
	
	public synchronized void pause() {
	    paused = true;
	}

	public synchronized void unpause() {
	    paused = false;
	    notifyAll();
	}
	
//	public void handlePlay(String input) {
//		if(state == MainState.PLAY_USERNAME) String username = in.getStrWMSG("Enter username [5..20]: ", 5, 20);
//		String password = in.getStrWMSG("Enter password [5..20]: ", 5, 20);
//
//		Optional<User> res = db.findUser(username, password);
//		if (res.isPresent()) {
//			gm = new GameMaster(this, res.get());
//			gm.newGame();
//			pause();
//			return;
//		}
//
//		System.out.println("User not found...");
//		in.pressEnter();
//	}
//	
//	public void handleInput(int input) {
//		switch (input) {
//		case 1:
//			state = MainState.PLAY_USERNAME;
//			handlePlay();
//			break;
//		case 2:
//			register();
//			break;
//		case 3:
//			highscore();
//			break;
//		default:
//			run = false;
//			db.saveDatabase();
//			break;
//		}
//	}
//	
//	public void handleInput(String input) {
//		if(state == MainState.MAIN) handleInput(Integer.parseInt(input));
//		
//		switch (state) {
//		case value:
//			
//			break;
//		default:
//			break;
//		}
//	}
	
	public void startMain(ConsoleInput in) {
		this.in = in;
		printTitle();
		in.pressEnter();
		menuHome();
	}
	
	public void restartMain(ConsoleInput in) {
		this.in = in;
//		printTitle();
//		System.out.println("Press [Enter] to continue..");
		menuHome();
	}
	
	enum MainState {
		MAIN,
		PLAY_USERNAME,
	}
}
