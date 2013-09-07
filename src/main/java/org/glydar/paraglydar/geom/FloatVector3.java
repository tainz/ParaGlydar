package org.glydar.paraglydar.geom;

import com.google.common.primitives.Floats;

public class FloatVector3 implements Vector3<Float, FloatVector3> {

	private final float x;
	private final float y;
	private final float z;

	public FloatVector3() {
		this(0f, 0f, 0f);
	}

	public FloatVector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public FloatVector3 setX(float newX) {
		return new FloatVector3(newX, y, z);
	}

	public FloatVector3 setY(float newY) {
		return new FloatVector3(x, newY, z);
	}

	public FloatVector3 setZ(float newZ) {
		return new FloatVector3(x, y, newZ);
	}

	public FloatVector3 setXY(float newX, float newY) {
		return new FloatVector3(newX, newY, z);
	}

	public FloatVector3 setXZ(float newX, float newZ) {
		return new FloatVector3(newX, y, newZ);
	}

	public FloatVector3 setYZ(float newY, float newZ) {
		return new FloatVector3(x, newY, newZ);
	}

	public FloatVector3 add(float value) {
		return new FloatVector3(x + value, y + value, z + value);
	}

	public FloatVector3 add(float ox, float oy, float oz) {
		return new FloatVector3(x + ox, y + oy, z + oz);
	}

	@Override
	public FloatVector3 add(FloatVector3 other) {
		return new FloatVector3(x + other.x, y + other.y, z + other.z);
	}

	public FloatVector3 subtract(float value) {
		return new FloatVector3(x - value, y - value, z - value);
	}

	public FloatVector3 subtract(float ox, float oy, float oz) {
		return new FloatVector3(x - ox, y - oy, z - oz);
	}

	@Override
	public FloatVector3 subtract(FloatVector3 other) {
		return new FloatVector3(x - other.x, y - other.y, z - other.z);
	}

	public FloatVector3 multiply(float value) {
		return new FloatVector3(x * value, y * value, z * value);
	}

	public FloatVector3 multiply(float ox, float oy, float oz) {
		return new FloatVector3(x * ox, y * oy, z * oz);
	}

	@Override
	public FloatVector3 multiply(FloatVector3 other) {
		return new FloatVector3(x * other.x, y * other.y, z * other.z);
	}

	public FloatVector3 divide(float value) {
		return new FloatVector3(x / value, y / value, z / value);
	}

	public FloatVector3 divide(float ox, float oy, float oz) {
		return new FloatVector3(x / ox, y / oy, z / oz);
	}

	@Override
	public FloatVector3 divide(FloatVector3 other) {
		return new FloatVector3(x / other.x, y / other.y, z / other.z);
	}

	@Override
	public double length() {
		return Math.sqrt(lengthSq());
	}

	public float lengthSq() {
		return x * x + y * y + z * z;
	}

	@Override
	public double distance(FloatVector3 other) {
		return Math.sqrt(distanceSq(other));
	}

	public float distanceSq(FloatVector3 other) {
		float dx = x - other.x;
		float dy = y - other.y;
		float dz = z - other.z;
		return dx * dx + dy * dy + dz * dz;
	}

	@Override
	public int compareTo(FloatVector3 o) {
		return Floats.compare(lengthSq(), o.lengthSq());
	}

	@Override
	public IntVector3 toIntVector3() {
		return new IntVector3((int) x, (int) y, (int) z);
	}

	@Override
	public LongVector3 toLongVector3() {
		return new LongVector3((long) x, (long) y, (long) z);
	}

	@Override
	public FloatVector3 toFloatVector3() {
		return this;
	}
}
