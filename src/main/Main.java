package main;

import modules.ConsoleInput;

public class Main {
	private ConsoleInput in = new ConsoleInput();

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
		
	}
	
	private void register() {
		
	}
	
	private void highscore() {
		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
