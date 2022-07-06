package org.lilsnow.game.obj.dim3;

import org.joml.Vector3f;

public class Triangle3D extends Model3D {

	private Vector3f v1, v2, v3;
	
	public Triangle3D (Vector3f v1, Vector3f v2, Vector3f v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	@Override
	public float[] get_vertices () {
		return new float[] {
					v1.x, v1.y, v1.z,
					v2.x, v2.y, v2.z,
					v3.x, v3.y, v3.z
		};
	}
	
	@Override
	public int[] get_indices () {
		return new int[] {
						0, 1, 2
		};
	}
}
