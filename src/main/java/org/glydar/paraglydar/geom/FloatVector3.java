package org.glydar.paraglydar.geom;

import com.google.common.primitives.Floats;

public class FloatVector3 implements Comparable<FloatVector3> {

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

	public FloatVector3 add(float value) {
		return new FloatVector3(x + value, y + value, z + value);
	}

	public FloatVector3 add(float ox, float oy, float oz) {
		return new FloatVector3(x + ox, y + oy, z + oz);
	}

	public FloatVector3 add(FloatVector3 other) {
		return new FloatVector3(x + other.x, y + other.y, z + other.z);
	}

	public FloatVector3 subtract(float value) {
		return new FloatVector3(x - value, y - value, z - value);
	}

	public FloatVector3 subtract(float ox, float oy, float oz) {
		return new FloatVector3(x - ox, y - oy, z - oz);
	}

	public FloatVector3 subtract(FloatVector3 other) {
		return new FloatVector3(x - other.x, y - other.y, z - other.z);
	}

	public FloatVector3 multiply(float value) {
		return new FloatVector3(x * value, y * value, z * value);
	}

	public FloatVector3 multiply(float ox, float oy, float oz) {
		return new FloatVector3(x * ox, y * oy, z * oz);
	}

	public FloatVector3 multiply(FloatVector3 other) {
		return new FloatVector3(x * other.x, y * other.y, z * other.z);
	}

	public FloatVector3 divide(float value) {
		return new FloatVector3(x / value, y / value, z / value);
	}

	public FloatVector3 divide(float ox, float oy, float oz) {
		return new FloatVector3(x / ox, y / oy, z / oz);
	}

	public FloatVector3 divide(FloatVector3 other) {
		return new FloatVector3(x / other.x, y / other.y, z / other.z);
	}

	public double length() {
		return Math.sqrt(lengthSq());
	}

	public float lengthSq() {
		return x * x + y * y + z * z;
	}

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
}
