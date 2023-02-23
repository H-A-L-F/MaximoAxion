package main;

import modules.ConsoleInput;
import modules.ControlledThread;

public class GameInput extends ControlledThread {

	private GameMaster gm;
	private ConsoleInput in;

	public GameInput(GameMaster gm) {
		super("Game input");
		this.gm = gm;
		this.in = new ConsoleInput();
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
		String message = String.format("0 to pause\nWhat do you want to do? [1..%d]", gm.player.availActions.size());
//		String res = in.getStrWMSG(message, 1, gm.player.availActions.size());
		// TODO HARDCODE FIX
		String res = in.getStrWMSG("", 1, gm.player.availActions.size());

		try {
			int p = Integer.parseInt(res);
			if(p >= gm.player.availActions.size()) return;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		gm.input(res);
	}

}
