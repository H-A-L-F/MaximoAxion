package main;

import models.user.User;
import modules.ConsoleInput;

public class InputManager {
	private ConsoleInput cIn;
	private GameInput in;
	private InputState state;
	
	private Main main;
	private GameMaster gm;
	
	public InputManager() {
		cIn = new ConsoleInput();
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
		in.stop();
		cIn = new ConsoleInput();
//		main.startMain(cIn);
		gm.deathScreen();
		cIn.pressEnter();
		restartMain();
	}
	
	public void restartMain() {
		main.unpause();
//		main.restartMain(cIn);
	}
	
	public static void main(String[] args) {
		new InputManager();
	}
	
	enum InputState {
		MAIN,
		GAME;
	}
}
