package org.glydar.paraglydar.geom;

public class IntVector3 implements Vector3<Integer, IntVector3> {

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

	public IntVector3 setX(int newX) {
		return new IntVector3(newX, y, z);
	}

	public IntVector3 setY(int newY) {
		return new IntVector3(x, newY, z);
	}

	public IntVector3 setZ(int newZ) {
		return new IntVector3(x, y, newZ);
	}

	public IntVector3 setXY(int newX, int newY) {
		return new IntVector3(newX, newY, z);
	}

	public IntVector3 setXZ(int newX, int newZ) {
		return new IntVector3(newX, y, newZ);
	}

	public IntVector3 setYZ(int newY, int newZ) {
		return new IntVector3(x, newY, newZ);
	}

	public IntVector3 add(int value) {
		return new IntVector3(x + value, y + value, z + value);
	}

	public IntVector3 add(int ox, int oy, int oz) {
		return new IntVector3(x + ox, y + oy, z + oz);
	}

	@Override
	public IntVector3 add(IntVector3 other) {
		return new IntVector3(x + other.x, y + other.y, z + other.z);
	}

	public IntVector3 subtract(int value) {
		return new IntVector3(x - value, y - value, z - value);
	}

	public IntVector3 subtract(int ox, int oy, int oz) {
		return new IntVector3(x - ox, y - oy, z - oz);
	}

	@Override
	public IntVector3 subtract(IntVector3 other) {
		return new IntVector3(x - other.x, y - other.y, z - other.z);
	}

	public IntVector3 multiply(int value) {
		return new IntVector3(x * value, y * value, z * value);
	}

	public IntVector3 multiply(int ox, int oy, int oz) {
		return new IntVector3(x * ox, y * oy, z * oz);
	}

	@Override
	public IntVector3 multiply(IntVector3 other) {
		return new IntVector3(x * other.x, y * other.y, z * other.z);
	}

	public IntVector3 divide(int value) {
		return new IntVector3(x / value, y / value, z / value);
	}

	public IntVector3 divide(int ox, int oy, int oz) {
		return new IntVector3(x / ox, y / oy, z / oz);
	}

	@Override
	public IntVector3 divide(IntVector3 other) {
		return new IntVector3(x / other.x, y / other.y, z / other.z);
	}

	@Override
	public double length() {
		return Math.sqrt(lengthSq());
	}

	public int lengthSq() {
		return x * x + y * y + z * z;
	}

	@Override
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

	@Override
	public IntVector3 toIntVector3() {
		return this;
	}

	@Override
	public LongVector3 toLongVector3() {
		return new LongVector3(x, y, z);
	}

	@Override
	public FloatVector3 toFloatVector3() {
		return new FloatVector3(x, y, z);
	}
}
