package org.glydar.paraglydar.data;

public class Item {
	byte type, subtype;
	long modifier; //Uint
	long minusModifier; //Uint
	byte rarity, material, flags;
	short level; //ushort
	ItemUpgrade[] upgrades;
	long upgradeCount; //unsigned

	public Item(Item i) {
		this.type = i.getType();
		this.subtype = i.getSubtype();
		this.modifier = i.getModifier();
		this.minusModifier = i.getMinusModifier();
		this.rarity = i.getRarity();
		this.material = i.getMaterial();
		this.flags = i.getFlags();
		this.level = i.getLevel();
		this.upgrades = new ItemUpgrade[i.getUpgrades().length];
		for (int j = 0; j < i.getUpgrades().length; j++) {
			this.upgrades[j] = new ItemUpgrade(i.getUpgrades()[j]);
		}
		this.upgradeCount = i.getUpgradeCount();
	}

	public Item() {
		upgrades = new ItemUpgrade[32];
		for (int i = 0; i < 32; i++)
			upgrades[i] = new ItemUpgrade();
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getSubtype() {
		return subtype;
	}

	public void setSubtype(byte subtype) {
		this.subtype = subtype;
	}

	public long getModifier() {
		return modifier;
	}

	public void setModifier(long modifier) {
		this.modifier = modifier;
	}

	public long getMinusModifier() {
		return minusModifier;
	}

	public void setMinusModifier(long minusModifier) {
		this.minusModifier = minusModifier;
	}

	public byte getRarity() {
		return rarity;
	}

	public void setRarity(byte rarity) {
		this.rarity = rarity;
	}

	public byte getMaterial() {
		return material;
	}

	public void setMaterial(byte material) {
		this.material = material;
	}

	public byte getFlags() {
		return flags;
	}

	public void setFlags(byte flags) {
		this.flags = flags;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public ItemUpgrade[] getUpgrades() {
		return upgrades;
	}

	public void setUpgrades(ItemUpgrade[] upgrades) {
		this.upgrades = (ItemUpgrade[]) upgrades;
	}

	public long getUpgradeCount() {
		return upgradeCount;
	}

	public void setUpgradeCount(long upgradeCount) {
		this.upgradeCount = upgradeCount;
	}
}
