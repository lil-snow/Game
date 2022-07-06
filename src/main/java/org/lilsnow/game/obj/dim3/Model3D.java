package org.lilsnow.game.obj.dim3;

public class Model3D {
	
	private float[] vertices;
	private int[] indices;
	
	public Model3D(float[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public Model3D () {}
	
	public float[] get_vertices() { return vertices; }
	
	public int[] get_indices() { return indices; }
	
	public Triangle3Dv[] get_triangles() { return new Triangle3Dv[0]; }
	
}
