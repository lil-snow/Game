package org.lilsnow.game.obj.dim3;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera3D {
	
	private Matrix4f view = new Matrix4f ();
	private Matrix4f projection = new Matrix4f ();
	
	public Camera3D () {
		projection.ortho (-8.0f, 8.0f, -4.5f, 4.5f, 1.0f, 100.0f, true);
	}
	
	public void move(Vector3f v) {
		view.translate (v.mul (-1));
	}
	
	public void rotateX(float angle) {
		view.rotate ((float) Math.toRadians(angle), new Vector3f (-1.0f, 0.0f, 0.0f));
	}
	
	public void rotateY(float angle) {
		view.rotate ((float) Math.toRadians(angle), new Vector3f (0.0f, -1.0f, 0.0f));
	}
	
	public void rotateZ(float angle) {
		view.rotate ((float) Math.toRadians(angle), new Vector3f (0.0f, 0.0f, -1.0f));
	}
	
	public Matrix4f get_vp() {
		Matrix4f m = new Matrix4f ();
		projection.mul (view, m);
		return m;
	}
	
}
