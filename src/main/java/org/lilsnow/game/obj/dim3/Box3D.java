package org.lilsnow.game.obj.dim3;

import org.joml.Vector3f;

public class Box3D extends Model3D {
	
	private Vector3f size;
	
	public Box3D(Vector3f size) {
		this.size = size;
	}
	
	public float[] get_vertices() {
		Vector3f rel = size.div (2);
		return new float[] {
						
					// Position							  // Normal
						-rel.x,  rel.y, -rel.z,    0.0f,  0.0f, -1.0f,    0.0f, 1.0f,  // front-top-left
						-rel.x, -rel.y, -rel.z,    0.0f,  0.0f, -1.0f, 		0.0f, 0.0f, // front-bottom-left
						 rel.x, -rel.y, -rel.z,    0.0f,  0.0f, -1.0f,    1.0f, 0.0f, // front-bottom-right
						 rel.x,  rel.y, -rel.z,    0.0f,  0.0f, -1.0f,    1.0f, 1.0f, // front-top-right
						
						 rel.x,  rel.y, -rel.z,    1.0f,  0.0f,  0.0f,    0.0f, 1.0f, // right-top-left
						 rel.x, -rel.y, -rel.z,    1.0f,  0.0f,  0.0f,    0.0f, 0.0f, // right-bottom-left
						 rel.x, -rel.y,  rel.z,    1.0f,  0.0f,  0.0f,    1.0f, 0.0f, // right-bottom-right
						 rel.x,  rel.y,  rel.z,    1.0f,  0.0f,  0.0f,    1.0f, 1.0f, // right-top-right
						
						 rel.x,  rel.y,  rel.z,    0.0f,  0.0f,  1.0f,    0.0f, 1.0f, // back-top-left
						 rel.x, -rel.y,  rel.z,    0.0f,  0.0f,  1.0f,    0.0f, 0.0f, // back-bottom-left
						-rel.x, -rel.y,  rel.z,    0.0f,  0.0f,  1.0f,    1.0f, 0.0f, // back-bottom-right
						-rel.x,  rel.y,  rel.z,    0.0f,  0.0f,  1.0f,    1.0f, 1.0f, // back-top-right
						
						-rel.x,  rel.y,  rel.z,   -1.0f,  0.0f,  0.0f,    0.0f, 1.0f, // left-top-left
						-rel.x, -rel.y,  rel.z,   -1.0f,  0.0f,  0.0f,    0.0f, 0.0f, // left-bottom-left
						-rel.x, -rel.y, -rel.z,   -1.0f,  0.0f,  0.0f,    1.0f, 0.0f, // left-bottom-right
						-rel.x,  rel.y, -rel.z,   -1.0f,  0.0f,  0.0f,    1.0f, 1.0f, // left-top-right
						
						-rel.x,  rel.y,  rel.z,    0.0f,  1.0f,  0.0f,    0.0f, 1.0f, // top-top-left
						-rel.x,  rel.y, -rel.z,    0.0f,  1.0f,  0.0f,    0.0f, 0.0f, // top-bottom-left
						 rel.x,  rel.y, -rel.z,    0.0f,  1.0f,  0.0f,    1.0f, 0.0f, // top-bottom-right
						 rel.x,  rel.y,  rel.z,    0.0f,  1.0f,  0.0f,    1.0f, 1.0f, // top-top-right
						
						-rel.x, -rel.y, -rel.z,    0.0f, -1.0f,  0.0f,    0.0f, 1.0f, // bottom-top-left
						-rel.x, -rel.y,  rel.z,    0.0f, -1.0f,  0.0f,    0.0f, 0.0f, // bottom-bottom-left
						 rel.x, -rel.y,  rel.z,    0.0f, -1.0f,  0.0f,    1.0f, 0.0f, // bottom-bottom-right
						 rel.x, -rel.y, -rel.z,    0.0f, -1.0f,  0.0f,    1.0f, 1.0f, // bottom-top-right
						
 		};
	}
	
	@Override
	public int[] get_indices () {
		return new int[] {
						0, 1, 2, // Front Plane
						0, 2, 3,
						
						4, 5, 6, // Right Plane
						4, 6, 7,
						
						8, 9, 10, // Back Plane
						8, 10, 11,
						
						12, 13, 14, // Left Plane
						12, 14, 15,
						
						16, 17, 18, // Top Plane
						16, 18, 19,
						
						20, 21, 22,
						20, 22, 23
		};
	}
	
	@Override
	public Triangle3Dv[] get_triangles() {
		
		// TODO: Implement Triangles
		return new Triangle3Dv[0];
	}
	
	public Plane3D[] get_planes() {
		// TODO: Implement Planes
		return new Plane3D[0];
	}
	
}
