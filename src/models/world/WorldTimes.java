package models.world;

import java.util.Arrays;
import java.util.Vector;

public enum WorldTimes {
	DAWN(0),
	MORNING(1),
	NOON(2),
	DUSK(3),
	NIGHT(4),
	MIDNIGHT(5);
	
	private final int order;
	
	WorldTimes(int order) {
		this.order = order;
	}
	
	public static Vector<WorldTimes> getWorldTimes() {
		return new Vector<>(Arrays.asList(DAWN, MORNING, NOON, DUSK, NIGHT, MIDNIGHT));
	}
	
	public int getOrder() {
		return this.order;
	}
}
