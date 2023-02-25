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
//		String message = String.format("0 to pause\nWhat do you want to do? [1..%d]", gm.player.availActions.size());
////		String res = in.getStrWMSG(message, 1, gm.player.availActions.size());
//		// TODO HARDCODE FIX
//		String res = in.getStrWMSG("", 1, gm.player.availActions.size());
//
//		try {
//			int p = Integer.parseInt(res);
//			if(p >= gm.player.availActions.size()) return;
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		gm.input(res);

		if(!running) {
			in.close();
		} else {
			String res = in.getStrWMSG("", 1, gm.player.availActions.size());

			try {
				int p = Integer.parseInt(res);
				if (p >= gm.player.availActions.size())
					return;
			} catch (Exception e) {
			}

			im.handleInput(res);
		}
	}

}
