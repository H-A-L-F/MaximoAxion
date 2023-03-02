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
		String res = in.getStrWMSG("", 1, gm.player.availActions.size());

		if (!running) return;

		try {
			int p = Integer.parseInt(res);
			if (p >= gm.player.availActions.size())
				return;
		} catch (Exception e) {
		}

		im.handleInput(res);
	}

}
