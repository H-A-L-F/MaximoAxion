package models.player;

public class PlayerActionHandler {

	private Player player;

	public PlayerActionHandler(Player player) {
		super();
		this.player = player;
	}

	public void actionHandler(PlayerActions a) {
		System.out.println(a.getMessage());
		switch (a) {
		case EXPLORE:
			// do something
			break;
		case REST:
			// do something
			break;
		case WOOD_GATHER:
			// do something
			break;
		case BUILD_SHELTER:
			// do something
			break;
		case IMPROVE_SHELTER:
			// do something
			break;
		case FOOD_GATHER:
			// do something
			break;
		case FOOD_CONSUME:
			// do something
			break;
		case WATER_GATHER:
			// do something
			break;
		case WATER_CONSUME:
			// do something
			break;
		case HERBS_GATHER:
			// do something
			break;
		case HERBS_CONSUME:
			// do something
			break;
		case ATTACK:
			// do something
			break;
		case DEFEND:
			// do something
			break;
		case FLEE:
			// do something
			break;
		default:
			// default action
			break;
		}
	}

}
