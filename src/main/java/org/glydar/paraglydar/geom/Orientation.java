package org.glydar.paraglydar.geom;

public class Orientation {

	private final float roll;
	private final float pitch;
	private final float yaw;

	public Orientation(float roll, float pitch, float yaw) {
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}
}
