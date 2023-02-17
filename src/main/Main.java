package main;

import java.util.List;
import java.util.Optional;

import models.user.User;
import modules.ConsoleInput;
import modules.Database;

public class Main {
	private ConsoleInput in = new ConsoleInput();
	private Database db = Database.getInstance();
	private GameMaster gm;

	public Main() {
		menuHome();
	}
	
	public void printTitle() {
		System.out.println("\r\n"
				+ "███╗░░░███╗░█████╗░██╗░░██╗██╗███╗░░░███╗░█████╗░░█████╗░██╗░░██╗██╗░█████╗░███╗░░██╗\r\n"
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
		
		printTitle();
		in.pressEnter();
		while (run) {
			optHome();
			opt = in.getIntWMSG(">> ", 1, 5);
			
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
				break;
			}
		}
	}
	
	private void play() {
		String username = in.getStrWMSG("Enter username [5..20]: ", 5, 20);
		String password = in.getStrWMSG("Enter password [5..20]: ", 5, 20);
		
		Optional<User> res = db.findUser(username, password);
		if(res.isPresent()) {
			gm = new GameMaster(res.get());
			gm.newGame();
		}
		
		System.out.println("User not found...");
		in.pressEnter();
	}
	
	private void register() {
		String username;
		while(true) {
			username = in.getStrWMSG("Enter username [5..20]: ", 5, 20);
			if(!db.findUsername(username)) break;
		}
		
		String password = in.getStrWMSG("Enter password [5..20]: ", 5, 20);
		
		Database.appendData(new User(username, password).serialize());
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
	
	public static void main(String[] args) {
		new Main();
	}
	
}
