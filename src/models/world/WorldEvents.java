package models.world;

import java.util.Arrays;
import java.util.Vector;

public enum WorldEvents {
	WORLD_NORMAL("The world is peaceful and everything seems to be in order."),

	VOLCANIC_ERUPTION("The ground is shaking and the sky is darkened by ash - a volcano has erupted!"),
	MASSIVE_THUNDERSTORM("The sky is dark and lightning is striking everywhere - a massive thunderstorm is raging!"),
	MASSIVE_BIRD_MIGRATION("The sky is filled with birds of all kinds, moving in a massive migration."),
	MASSIVE_EARTHQUAKE("The ground is shaking violently and buildings are collapsing - a massive earthquake is underway!"),
	
	SOLAR_ECLIPSE("The sun is obscured by the moon, casting an eerie shadow on the world - it's a solar eclipse!"),
	SCORCHING_SUN("The sun is burning hot and the air is stifling - it's a scorching day!"),

	BLOOD_MOON("The moon is red and the sky is eerie - it's a blood moon!"),
	MASSIVE_BATS_INVASION("The sky is filled with swarms of bats, covering the world in darkness."),
	ALIEN_INVASION("Strange objects are falling from the sky and people are running in fear - it's an alien invasion!"),
	BLACKOUT("The world is dark and silent, as a massive blackout has plunged everything into darkness.");
		
	private final String message;
	
	WorldEvents(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public static Vector<WorldEvents> getEventsNone() {
		return new Vector<>(Arrays.asList(WORLD_NORMAL));
	}
	
	public static Vector<WorldEvents> getEventsNeutral() {
		return new Vector<>(Arrays.asList(VOLCANIC_ERUPTION, MASSIVE_THUNDERSTORM, MASSIVE_BIRD_MIGRATION, MASSIVE_EARTHQUAKE));
	}
	
	public static Vector<WorldEvents> getEventsDay() {
		return new Vector<>(Arrays.asList(SOLAR_ECLIPSE, SCORCHING_SUN));
	}
	
	public static Vector<WorldEvents> getEventsNight() {
		return new Vector<>(Arrays.asList(BLOOD_MOON, MASSIVE_BATS_INVASION, ALIEN_INVASION, BLACKOUT));
	}
	
}
