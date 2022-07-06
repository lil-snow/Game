package org.lilsnow.game.gfx;

import org.lilsnow.game.utils.Mathf;

public class ColorRGBA {
	
	public float r, g, b, a;
	
	public ColorRGBA(int r, int g, int b, int a) {
		this.r = Mathf.normalize (r, 0, 255);
		this.g = Mathf.normalize (g, 0, 255);
		this.b = Mathf.normalize (g, 0, 255);
		this.a = Mathf.normalize (g, 0, 255);
	}
	
	public ColorRGBA(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
//	public float r() {}

	
}
