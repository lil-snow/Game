package org.lilsnow.game.obj.dim3;

import org.joml.Vector2f;

public class Plane3D extends Model3D {

	private Vector2f size;
	
	public Plane3D(Vector2f size) {
		this.size = size;
	}
	
	@Override
	public float[] get_vertices() {
		Vector2f rel = size.div (2);
		return new float[] {
						-rel.x,  rel.y, 0.0f,    0.0f, 0.0f, 1.0f,    0.0f, 1.0f, // top-left
						-rel.x, -rel.y, 0.0f,    0.0f, 0.0f, 1.0f,    0.0f, 0.0f, // bottom-left
						 rel.x, -rel.y, 0.0f,    0.0f, 0.0f, 1.0f,    1.0f, 0.0f, // bottom-right
						 rel.x,  rel.y, 0.0f,    0.0f, 0.0f, 1.0f,    1.0f, 1.0f, // top-right
		};
	}
	
	@Override
	public int[] get_indices() {
		return new int[] {
						0, 1, 2,
						0, 2, 3
		};
	}
	
}
