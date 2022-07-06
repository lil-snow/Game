package org.lilsnow.game.obj.dim3;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class GameObject3D {
	
	private final Matrix4f modelMatrix = new Matrix4f ();
	private final Model3D model;
	
	public GameObject3D (Model3D model) {
		this.model = model;
	}
	
	public GameObject3D (Model3D model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		modelMatrix.translate (position);
		modelMatrix.rotate (rotation.x, 1.0f, 0.0f, 0.0f);
		modelMatrix.rotate (rotation.y, 0.0f, 1.0f, 0.0f);
		modelMatrix.rotate (rotation.z, 0.0f, 0.0f, 1.0f);
		modelMatrix.scale (scale);
	}
	
	public void move(Vector3f v) {
		modelMatrix.translate (v);
	}
	
	public void rotate(Vector3f v) {
		modelMatrix.rotate (v.x, 1.0f, 0.0f, 0.0f);
		modelMatrix.rotate(v.y, 0.0f, 1.0f, 0.0f);
		modelMatrix.rotate (v.z, 0.0f, 0.0f, 1.0f);
	}
	
	public void scale(Vector3f v) {
		modelMatrix.scale (v);
	}
	
	public Matrix4f get_model_matrix() {
		return modelMatrix;
	}
	
	public Model3D get_model() {
		return model;
	}
	
}
