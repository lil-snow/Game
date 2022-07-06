package org.lilsnow.game.gfx;

import static org.lilsnow.game.utils.Mathf.*;

public class ColorRGB {
	
	private float r, g, b;
	
	public ColorRGB(int r, int g, int b) {
		this.r = normalize (r, 0, 255);
		this.g = normalize (g, 0, 255);
		this.b = normalize (b, 0, 255);
	}
	
	public ColorRGB(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
}
