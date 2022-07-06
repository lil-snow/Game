package org.lilsnow.game.core.input;

import static org.lilsnow.game.utils.ArrayUtils.contains_only;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class InputCallbacks {

	public static void mouse_pos_callback(long ptr, double xpos, double ypos) {
		Mouse.last_x = Mouse.x_pos;
		Mouse.last_y = Mouse.y_pos;
		Mouse.x_pos = (float) xpos;
		Mouse.y_pos = (float) ypos;
		
		if (!contains_only(Mouse.buttons, false)) {
			Mouse.dragging = true;
		}
	}
	
	public static void mouse_button_callback(long ptr, int button, int action, int mods) {
		if (button < Mouse.NUM_BUTTONS) {
			if (action == GLFW_PRESS) {
				Mouse.buttons[button] = true;
			} else {
				Mouse.buttons[button] = false;
				Mouse.dragging = false;
			}
		}
	}
	
	public static void mouse_scroll_callback(long ptr, double x_off, double y_off) {
		Mouse.scroll_x = (float) x_off;
		Mouse.scroll_y = (float) y_off;
	}

}
