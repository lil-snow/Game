package org.lilsnow.game.core.input;

public class Mouse {
	
	private Mouse () {}
	
	protected static final int NUM_BUTTONS = 5;
	protected static boolean[] buttons = new boolean[NUM_BUTTONS];
	protected static boolean[] buttonsLast = new boolean[NUM_BUTTONS];
	
	protected static float scroll_x, scroll_y;
	protected static float x_pos, y_pos, last_x, last_y;
	protected static boolean dragging;
	
	public static boolean isButton (int keyCode) {
		return buttons[keyCode];
	}
	
	public static boolean isButtonDown (int keyCode) {
		return !buttonsLast[keyCode] && buttons[keyCode];
	}
	
	public static boolean isButtonUp (int keyCode) {
		return buttonsLast[keyCode] && !buttons[keyCode];
	}
	
	public static void end () {
		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttonsLast[i] = buttons[i];
		}
		
		scroll_x = 0.0f;
		scroll_y = 0.0f;
		last_x = x_pos;
		last_y = y_pos;
	}
	
	public static float get_x() {
		return x_pos;
	}
	
	public static float get_y() {
		return y_pos;
	}
	
	public static float get_dx() {
		return x_pos - last_x;
	}
	
	public static float get_dy() {
		return y_pos - last_y;
	}
	
	public static float get_scroll_x() {
		return scroll_x;
	}
	
	public static float get_scroll_y() {
		return scroll_y;
	}
	
	public static boolean is_dragging() {
		return dragging;
	}
	
}