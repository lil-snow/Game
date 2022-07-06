package org.lilsnow.game.obj.dim3;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class GameObject3Dv {
	
	private Vector3f position, rotation, scale;
	private Model3D model;
	
	public GameObject3Dv (Model3D model) {
		scale = new Vector3f ();
		rotation = new Vector3f ();
		position = new Vector3f ();
	}
	
	public GameObject3Dv (Model3D model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void move(Vector3f v) {
		position.add (v);
	}
	
	public void rotate(Vector3f v) {
		rotation.add (new Vector3f ((float) Math.toRadians(v.x), (float) Math.toRadians (v.y), (float) Math.toRadians (v.z)));
	}
	
	public void scale(Vector3f v) {
		scale.add (v);
	}
	
	public void set_position(Vector3f position) {
		this.position = position;
	}
	
	public void set_rotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void set_scale(Vector3f scale) {
		this.scale = scale;
	}
	
	public Matrix4f get_model_matrix() {
		Matrix4f m = new Matrix4f ();
		m.translate (position);
		m.rotate (rotation.x, 1.0f, 0.0f, 0.0f);
		m.rotate (rotation.y, 0.0f, 1.0f, 0.0f);
		m.rotate (rotation.z, 0.0f, 0.0f, 1.0f);
		m.scale (scale);
		return m;
	}
	
	public Model3D get_model() {
		return model;
	}
	
}
