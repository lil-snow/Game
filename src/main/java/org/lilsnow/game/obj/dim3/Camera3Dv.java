package org.lilsnow.game.obj.dim3;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera3Dv {
	
	private Vector3f position, rotation;
	private final Matrix4f projection = new Matrix4f ();
	
	public Camera3Dv () {
		position = new Vector3f ();
		rotation = new Vector3f ();
		// projection.ortho (-8.0f, 8.0f, -4.5f, 4.5f, -1.0f, 10.0f, true);
		projection.perspective ((float) Math.toRadians (90), 16.0f / 9.0f, 1.0f, 100.0f);
	}
	
	public void move(Vector3f v) {
		position.add (v.mul (-1));
	}
	
	public void rotate(Vector3f v) {
		rotation.add (v);
	}
	
	public void set_rotation(Vector3f r) {
		rotation = r;
	}
	
	public void set_position(Vector3f p) {
		position = p;
	}
	
	public Matrix4f get_vp() {
		Matrix4f view = new Matrix4f ();
		view.translate (position);
		view.rotate (rotation.x, new Vector3f (1.0f, 0.0f, 0.0f));
		view.rotate (rotation.y, new Vector3f (0.0f, 1.0f, 0.0f));
		view.rotate (rotation.z, new Vector3f (0.0f, 0.0f, 1.0f));
		Matrix4f m = new Matrix4f ();
		projection.mul(view, m);
		return m;
	}
	
}
