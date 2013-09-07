package org.glydar.paraglydar.geom;

import com.google.common.primitives.Longs;

public final class LongVector3 implements Vector3<Long, LongVector3> {

	private final long x;
	private final long y;
	private final long z;

	public LongVector3() {
		this(0l, 0l, 0l);
	}

	public LongVector3(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}

	public long getZ() {
		return z;
	}

	public LongVector3 setX(long newX) {
		return new LongVector3(newX, y, z);
	}

	public LongVector3 setY(long newY) {
		return new LongVector3(x, newY, z);
	}

	public LongVector3 setZ(long newZ) {
		return new LongVector3(x, y, newZ);
	}

	public LongVector3 setXY(long newX, long newY) {
		return new LongVector3(newX, newY, z);
	}

	public LongVector3 setXZ(long newX, long newZ) {
		return new LongVector3(newX, y, newZ);
	}

	public LongVector3 setYZ(long newY, long newZ) {
		return new LongVector3(x, newY, newZ);
	}

	public LongVector3 add(long value) {
		return new LongVector3(x + value, y + value, z + value);
	}

	public LongVector3 add(long ox, long oy, long oz) {
		return new LongVector3(x + ox, y + oy, z + oz);
	}

	@Override
	public LongVector3 add(LongVector3 other) {
		return new LongVector3(x + other.x, y + other.y, z + other.z);
	}

	public LongVector3 subtract(long value) {
		return new LongVector3(x - value, y - value, z - value);
	}

	public LongVector3 subtract(long ox, long oy, long oz) {
		return new LongVector3(x - ox, y - oy, z - oz);
	}

	@Override
	public LongVector3 subtract(LongVector3 other) {
		return new LongVector3(x - other.x, y - other.y, z - other.z);
	}

	public LongVector3 multiply(long value) {
		return new LongVector3(x * value, y * value, z * value);
	}

	public LongVector3 multiply(long ox, long oy, long oz) {
		return new LongVector3(x * ox, y * oy, z * oz);
	}

	@Override
	public LongVector3 multiply(LongVector3 other) {
		return new LongVector3(x * other.x, y * other.y, z * other.z);
	}

	public LongVector3 divide(long value) {
		return new LongVector3(x / value, y / value, z / value);
	}

	public LongVector3 divide(long ox, long oy, long oz) {
		return new LongVector3(x / ox, y / oy, z / oz);
	}

	@Override
	public LongVector3 divide(LongVector3 other) {
		return new LongVector3(x / other.x, y / other.y, z / other.z);
	}

	@Override
	public double length() {
		return Math.sqrt(lengthSq());
	}

	public long lengthSq() {
		return x * x + y * y + z * z;
	}

	@Override
	public double distance(LongVector3 other) {
		return Math.sqrt(distanceSq(other));
	}

	public long distanceSq(LongVector3 other) {
		long dx = x - other.x;
		long dy = y - other.y;
		long dz = z - other.z;
		return dx * dx + dy * dy + dz * dz;
	}

	@Override
	public int compareTo(LongVector3 o) {
		return Longs.compare(lengthSq(), o.lengthSq());
	}

	@Override
	public IntVector3 toIntVector3() {
		return new IntVector3((int) x, (int) y, (int) z);
	}

	@Override
	public LongVector3 toLongVector3() {
		return this;
	}

	@Override
	public FloatVector3 toFloatVector3() {
		return new FloatVector3(x, y, z);
	}
}
