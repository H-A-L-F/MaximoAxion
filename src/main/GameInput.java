package main;

import modules.ConsoleInput;
import modules.ControlledThread;

public class GameInput extends ControlledThread {

	private InputManager im;
	private GameMaster gm;
	private ConsoleInput in;

	public GameInput(InputManager im, GameMaster gm, ConsoleInput in) {
		super("Game input");
		this.im = im;
		this.gm = gm;
		this.in = in;
	}

	@Override
	public void run() {
		while (running) {
			synchronized (this) {
				while (paused) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				update();
			}
		}
	}

	@Override
	public void update() {
		String res = in.getStrWMSG("");
		
//		if(!running) {
//			System.out.println("DALAM UPDATE INPUT");
////			im.main.menuHome();
//			return;
//		}
		
		if(!gm.player.isAlive) {
			im.exitGame();
		} else {
			try {
				int p = Integer.parseInt(res);
				if (p > gm.player.availActions.size() || p < 1)
					return;
			} catch (Exception e) {
				return;
			}

			
			im.handleInput(res);
		}
	}
}
