package org.glydar.paraglydar.data;

public class ItemUpgrade {
	byte xOffset;
	byte yOffset;
	byte zOffset;
	byte material;
	long level;

	public ItemUpgrade() {
	}

	public ItemUpgrade(ItemUpgrade i) {
		this.xOffset = i.getxOffset();
		this.yOffset = i.getyOffset();
		this.zOffset = i.getzOffset();
		this.material = i.getMaterial();
		this.level = i.getLevel();
	}

	public byte getxOffset() {
		return xOffset;
	}

	public void setxOffset(byte xOffset) {
		this.xOffset = xOffset;
	}

	public byte getyOffset() {
		return yOffset;
	}

	public void setyOffset(byte yOffset) {
		this.yOffset = yOffset;
	}

	public byte getzOffset() {
		return zOffset;
	}

	public void setzOffset(byte zOffset) {
		this.zOffset = zOffset;
	}

	public byte getMaterial() {
		return material;
	}

	public void setMaterial(byte material) {
		this.material = material;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

}
