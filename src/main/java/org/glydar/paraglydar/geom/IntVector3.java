package org.glydar.paraglydar.geom;

public class IntVector3 implements Comparable<IntVector3> {

	private final int x;
	private final int y;
	private final int z;

	public IntVector3() {
		this(0, 0, 0);
	}

	public IntVector3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public IntVector3 add(int value) {
		return new IntVector3(x + value, y + value, z + value);
	}

	public IntVector3 add(int ox, int oy, int oz) {
		return new IntVector3(x + ox, y + oy, z + oz);
	}

	public IntVector3 add(IntVector3 other) {
		return new IntVector3(x + other.x, y + other.y, z + other.z);
	}

	public IntVector3 subtract(int value) {
		return new IntVector3(x - value, y - value, z - value);
	}

	public IntVector3 subtract(int ox, int oy, int oz) {
		return new IntVector3(x - ox, y - oy, z - oz);
	}

	public IntVector3 subtract(IntVector3 other) {
		return new IntVector3(x - other.x, y - other.y, z - other.z);
	}

	public IntVector3 multiply(int value) {
		return new IntVector3(x * value, y * value, z * value);
	}

	public IntVector3 multiply(int ox, int oy, int oz) {
		return new IntVector3(x * ox, y * oy, z * oz);
	}

	public IntVector3 multiply(IntVector3 other) {
		return new IntVector3(x * other.x, y * other.y, z * other.z);
	}

	public IntVector3 divide(int value) {
		return new IntVector3(x / value, y / value, z / value);
	}

	public IntVector3 divide(int ox, int oy, int oz) {
		return new IntVector3(x / ox, y / oy, z / oz);
	}

	public IntVector3 divide(IntVector3 other) {
		return new IntVector3(x / other.x, y / other.y, z / other.z);
	}

	public double length() {
		return Math.sqrt(lengthSq());
	}

	public int lengthSq() {
		return x * x + y * y + z * z;
	}

	public double distance(IntVector3 other) {
		return Math.sqrt(distanceSq(other));
	}

	public int distanceSq(IntVector3 other) {
		int dx = x - other.x;
		int dy = y - other.y;
		int dz = z - other.z;
		return dx * dx + dy * dy + dz * dz;
	}

	@Override
	public int compareTo(IntVector3 o) {
		return lengthSq() - o.lengthSq();
	}
}
