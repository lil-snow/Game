package org.lilsnow.game.utils;

public class Mathf {
	
	public static float normalize(float n, float min, float max) {
		return (n - min) / (max - min);
	}
	
}
