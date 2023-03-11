package main;

import models.user.User;
import modules.ConsoleInput;

public class InputManager {
	private ConsoleInput cIn;
	public GameInput in;
	private InputState state;
	
	public Main main;
	public GameMaster gm;
	
	public InputManager() {
		cIn = ConsoleInput.getInstance();
		main = new Main(this);
		
		start();
	}
	
	private void start() {
		state = InputState.MAIN;
		main.startMain(cIn);
	}
	
	public void startGame(User user) {
		state = InputState.GAME;
		gm = new GameMaster(this, user);
		gm.newGame();
		in = new GameInput(this, gm, cIn);
		in.start();
	}
	
	public void handleInput(String in) {
		switch (state) {
		case MAIN:
			restartMain();
			break;
		case GAME:
			gm.handleInput(in);
			break;
		default:
			break;
		}
	}
	
	public void exitGame() {
		state = InputState.MAIN;
		restartMain();
	}
	
	public void closeInput() {
		gm.deathScreen();
		in.stop();
	}
	
	private void restartMain() {
		System.out.println("MASUK");
		in.stop();
		main.unpause();
//		main.restartMain(cIn);
	}
	
	public void notifyMain() {
		main.notifyAll();
	}
	
	public static void main(String[] args) {
		new InputManager();
	}
	
	enum InputState {
		MAIN,
		GAME;
	}
}
