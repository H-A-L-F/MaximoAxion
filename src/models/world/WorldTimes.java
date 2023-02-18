package models.world;

import java.util.Arrays;
import java.util.Vector;

public enum WorldTimes {
	DAWN(0, "The darkness is fading and the sky is getting brighter - it's dawn."),
	MORNING(1, "The birds are out and the sky is blue - it's morning."),
	NOON(2, "The day is half over and the light is bright - it's noon."),
	DUSK(3, "The world is getting quieter and the air is getting cooler - it's dusk."),
	NIGHT(4, "The light is dim and the shadows are long - it's night."),
	MIDNIGHT(5, "The world is dark and quiet as the shadow thrives - it's midnight.");
	
	private final int order;
	private final String message;
	
	WorldTimes(int order, String message) {
		this.order = order;
		this.message = message;
	}
	
	public static Vector<WorldTimes> getWorldTimes() {
		return new Vector<>(Arrays.asList(DAWN, MORNING, NOON, DUSK, NIGHT, MIDNIGHT));
	}
	
	public int getOrder() {
		return this.order;
	}
	
	public String getMessage() {
		return this.message;
	}
}
