package org.glydar.paraglydar.data;

import java.util.BitSet;

import com.google.common.base.Charsets;

import org.glydar.paraglydar.geom.FloatVector3;
import org.glydar.paraglydar.geom.LongVector3;
import org.glydar.paraglydar.geom.Orientation;
import org.glydar.paraglydar.models.Entity;

/* Structures and data discovered by mat^2 (http://github.com/matpow2) */

public class EntityData {

	private Entity entity;

	private long id;
	private BitSet bitSet;

	private LongVector3 position;
	private Orientation orientation;

	private FloatVector3 velocity;

	private FloatVector3 accel;

	private FloatVector3 extraVel;

	private float lookPitch;
	private long physicsFlags; //Uint
	private byte hostileType;
	private long entityType; //Uint
	private byte currentMode;
	private long lastShootTime; //Uint
	private long hitCounter; //Uint
	private long lastHitTime; //Uint
	private Appearance app;
	private byte flags1;
	private byte flags2;
	private long rollTime; //Uint
	private int stunTime;
	private long slowedTime; //Uint
	private long makeBlueTime; //Uint
	private long speedUpTime; //Uint
	private float slowPatchTime;
	private byte classType;
	private byte specialization;
	private float chargedMP;

	private FloatVector3 rayHit;

	private float HP;
	private float MP;

	private float blockPower;
	private float maxHPMultiplier;
	private float shootSpeed;
	private float damageMultiplier;
	private float armorMultiplier;
	private float resistanceMultiplier;
	private long level;  //Uint
	private long currentXP; //Uint
	private Item itemData;
	private Item[] equipment;

	private long iceBlockFour; //Uint
	private long[] skills;
	private String name;

	private long na1; //Uint
	private long na2; // |
	private byte na3;
	private long na4;
	private long na5;
	private long nu1;
	private long nu2;
	private long nu3;
	private long nu4;
	private long nu5;
	private long nu6;
	private byte nu7;
	private byte nu8;
	private long parentOwner;
	private long nu11;
	private long nu12;
	private LongVector3 spawnPosition;
	private long nu20;
	private long nu21;
	private long nu22;
	private byte nu19;

	private int debugCap;

	public EntityData() {
		bitSet = new BitSet(64);
		position = new LongVector3();
		velocity = new FloatVector3();
		accel = new FloatVector3();
		extraVel = new FloatVector3();
		rayHit = new FloatVector3();
		app = new Appearance();
		itemData = new Item();
		equipment = new Item[13];
		for (int i = 0; i < 13; i++)
			equipment[i] = new Item();
		spawnPosition = new LongVector3();
		skills = new long[11];
	}

	public EntityData(EntityData e) {
		this.id = e.getId();
		this.bitSet = e.getBitSet();
		this.position = e.getPosition();
		this.orientation = e.getOrientation();
		this.velocity = e.getVelocity();
		this.accel = e.getAccel();
		this.extraVel = e.getExtraVel();
		this.lookPitch = e.getLookPitch();
		this.physicsFlags = e.getPhysicsFlags();
		this.hostileType = e.getHostileType();
		this.entityType = e.getEntityType();
		this.currentMode = e.getCurrentMode();
		this.lastShootTime = e.getLastShootTime();
		this.hitCounter = e.getHitCounter();
		this.lastHitTime = e.getLastHitTime();
		this.app = new Appearance(e.getApp());
		this.flags1 = e.getFlags1();
		this.flags2 = e.getFlags2();
		this.rollTime = e.getRollTime();
		this.stunTime = e.getStunTime();
		this.slowedTime = e.getSlowedTime();
		this.makeBlueTime = e.getMakeBlueTime();
		this.speedUpTime = e.getSpeedUpTime();
		this.slowPatchTime = e.getSlowPatchTime();
		this.classType = e.getClassType();
		this.specialization = e.getSpecialization();
		this.chargedMP = e.getChargedMP();
		this.rayHit = e.getRayHit();
		HP = e.getHP();
		MP = e.getMP();
		this.blockPower = e.getBlockPower();
		this.maxHPMultiplier = e.getMaxHPMultiplier();
		this.shootSpeed = e.getShootSpeed();
		this.damageMultiplier = e.getDamageMultiplier();
		this.armorMultiplier = e.getArmorMultiplier();
		this.resistanceMultiplier = e.getResistanceMultiplier();
		this.level = e.getLevel();
		this.currentXP = e.getCurrentXP();
		this.itemData = new Item(e.getItemData());
		this.equipment = new Item[e.getEquipment().length];
		for (int j = 0; j < e.getEquipment().length; j++) {
			this.equipment[j] = new Item(e.getEquipment()[j]);
		}
		this.iceBlockFour = e.getIceBlockFour();
		this.skills = e.getSkills();
		this.name = e.getName();
		this.na1 = e.getNa1();
		this.na2 = e.getNa2();
		this.na3 = e.getNa3();
		this.na4 = e.getNa4();
		this.na5 = e.getNa5();
		this.nu1 = e.getNu1();
		this.nu2 = e.getNu2();
		this.nu3 = e.getNu3();
		this.nu4 = e.getNu4();
		this.nu5 = e.getNu5();
		this.nu6 = e.getNu6();
		this.nu7 = e.getNu7();
		this.nu8 = e.getNu8();
		this.parentOwner = e.getParentOwner();
		this.nu11 = e.getNu11();
		this.nu12 = e.getNu12();
		this.spawnPosition = e.getSpawnPosition();
		this.nu20 = e.getNu20();
		this.nu21 = e.getNu21();
		this.nu22 = e.getNu22();
		this.nu19 = e.getNu19();
		this.debugCap = e.getDebugCap();
	}

	public Entity getEntity(){
		return entity;
	}
	
	public void setEntity(Entity e){
		if (entity == null){
			this.entity = e;
		}
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BitSet getBitSet() {
		return bitSet;
	}
	
	public void setBitSet(BitSet b) {
		this.bitSet = b;
	}


	public void fullUpdate() {
		bitSet.set(0, 63);
	}

	public LongVector3 getPosition() {
		return position;
	}

	public void setPosition(LongVector3 pos) {
		this.position = pos;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public FloatVector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(FloatVector3 velocity) {
		this.velocity = velocity;
	}

	public FloatVector3 getAccel() {
		return accel;
	}

	public void setAccel(FloatVector3 accel) {
		this.accel = accel;
	}

	public FloatVector3 getExtraVel() {
		return extraVel;
	}

	public void setExtraVel(FloatVector3 extraVel) {
		this.extraVel = extraVel;
	}

	public float getLookPitch() {
		return lookPitch;
	}

	public void setLookPitch(float lookPitch) {
		this.lookPitch = lookPitch;
	}

	public long getPhysicsFlags() {
		return physicsFlags;
	}

	public void setPhysicsFlags(long physicsFlags) {
		this.physicsFlags = physicsFlags;
	}

	public byte getHostileType() {
		return hostileType;
	}

	public void setHostileType(byte hostileType) {
		this.hostileType = hostileType;
	}

	public long getEntityType() {
		return entityType;
	}

	public void setEntityType(long entityType) {
		this.entityType = entityType;
	}

	public byte getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(byte currentMode) {
		this.currentMode = currentMode;
	}

	public long getLastShootTime() {
		return lastShootTime;
	}

	public void setLastShootTime(long lastShootTime) {
		this.lastShootTime = lastShootTime;
	}

	public long getHitCounter() {
		return hitCounter;
	}

	public void setHitCounter(long hitCounter) {
		this.hitCounter = hitCounter;
	}

	public long getLastHitTime() {
		return lastHitTime;
	}

	public void setLastHitTime(long lastHitTime) {
		this.lastHitTime = lastHitTime;
	}

	public Appearance getApp() {
		return app;
	}

	public void setApp(Appearance app) {
		this.app = (Appearance) app;
	}

	public byte getFlags1() {
		return flags1;
	}

	public void setFlags1(byte flags1) {
		this.flags1 = flags1;
	}

	public byte getFlags2() {
		return flags2;
	}

	public void setFlags2(byte flags2) {
		this.flags2 = flags2;
	}

	public long getRollTime() {
		return rollTime;
	}

	public void setRollTime(long rollTime) {
		this.rollTime = rollTime;
	}

	public int getStunTime() {
		return stunTime;
	}

	public void setStunTime(int stunTime) {
		this.stunTime = stunTime;
	}

	public long getSlowedTime() {
		return slowedTime;
	}

	public void setSlowedTime(long slowedTime) {
		this.slowedTime = slowedTime;
	}

	public long getMakeBlueTime() {
		return makeBlueTime;
	}

	public void setMakeBlueTime(long makeBlueTime) {
		this.makeBlueTime = makeBlueTime;
	}

	public long getSpeedUpTime() {
		return speedUpTime;
	}

	public void setSpeedUpTime(long speedUpTime) {
		this.speedUpTime = speedUpTime;
	}

	public float getSlowPatchTime() {
		return slowPatchTime;
	}

	public void setSlowPatchTime(float slowPatchTime) {
		this.slowPatchTime = slowPatchTime;
	}

	public byte getClassType() {
		return classType;
	}

	public void setClassType(byte classType) {
		this.classType = classType;
	}

	public byte getSpecialization() {
		return specialization;
	}

	public void setSpecialization(byte specialization) {
		this.specialization = specialization;
	}

	public float getChargedMP() {
		return chargedMP;
	}

	public void setChargedMP(float chargedMP) {
		this.chargedMP = chargedMP;
	}

	public FloatVector3 getRayHit() {
		return rayHit;
	}

	public void setRayHit(FloatVector3 rayHit) {
		this.rayHit = rayHit;
	}

	public float getHP() {
		return HP;
	}

	public void setHP(float hP) {
		HP = hP;
	}

	public float getMP() {
		return MP;
	}

	public void setMP(float mP) {
		MP = mP;
	}

	public float getBlockPower() {
		return blockPower;
	}

	public void setBlockPower(float blockPower) {
		this.blockPower = blockPower;
	}

	public float getMaxHPMultiplier() {
		return maxHPMultiplier;
	}

	public void setMaxHPMultiplier(float maxHPMultiplier) {
		this.maxHPMultiplier = maxHPMultiplier;
	}

	public float getShootSpeed() {
		return shootSpeed;
	}

	public void setShootSpeed(float shootSpeed) {
		this.shootSpeed = shootSpeed;
	}

	public float getDamageMultiplier() {
		return damageMultiplier;
	}

	public void setDamageMultiplier(float damageMultiplier) {
		this.damageMultiplier = damageMultiplier;
	}

	public float getArmorMultiplier() {
		return armorMultiplier;
	}

	public void setArmorMultiplier(float armorMultiplier) {
		this.armorMultiplier = armorMultiplier;
	}

	public float getResistanceMultiplier() {
		return resistanceMultiplier;
	}

	public void setResistanceMultiplier(float resistanceMultiplier) {
		this.resistanceMultiplier = resistanceMultiplier;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public long getCurrentXP() {
		return currentXP;
	}

	public void setCurrentXP(long currentXP) {
		this.currentXP = currentXP;
	}

	public Item getItemData() {
		return itemData;
	}

	public void setItemData(Item itemData) {
		this.itemData = (Item) itemData;
	}

	public Item[] getEquipment() {
		return equipment;
	}

	public void setEquipment(Item[] equipment) {
		this.equipment = (Item[]) equipment;
	}

	public long getIceBlockFour() {
		return iceBlockFour;
	}

	public void setIceBlockFour(long iceBlockFour) {
		this.iceBlockFour = iceBlockFour;
	}

	public long[] getSkills() {
		return skills;
	}

	public void setSkills(long[] skills) {
		this.skills = skills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNa1() {
		return na1;
	}

	public void setNa1(long na1) {
		this.na1 = na1;
	}

	public long getNa2() {
		return na2;
	}

	public void setNa2(long na2) {
		this.na2 = na2;
	}

	public byte getNa3() {
		return na3;
	}

	public void setNa3(byte na3) {
		this.na3 = na3;
	}

	public long getNa4() {
		return na4;
	}

	public void setNa4(long na4) {
		this.na4 = na4;
	}

	public long getNa5() {
		return na5;
	}

	public void setNa5(long na5) {
		this.na5 = na5;
	}

	public long getNu1() {
		return nu1;
	}

	public void setNu1(long nu1) {
		this.nu1 = nu1;
	}

	public long getNu2() {
		return nu2;
	}

	public void setNu2(long nu2) {
		this.nu2 = nu2;
	}

	public long getNu3() {
		return nu3;
	}

	public void setNu3(long nu3) {
		this.nu3 = nu3;
	}

	public long getNu4() {
		return nu4;
	}

	public void setNu4(long nu4) {
		this.nu4 = nu4;
	}

	public long getNu5() {
		return nu5;
	}

	public void setNu5(long nu5) {
		this.nu5 = nu5;
	}

	public long getNu6() {
		return nu6;
	}

	public void setNu6(long nu6) {
		this.nu6 = nu6;
	}

	public byte getNu7() {
		return nu7;
	}

	public void setNu7(byte nu7) {
		this.nu7 = nu7;
	}

	public byte getNu8() {
		return nu8;
	}

	public void setNu8(byte nu8) {
		this.nu8 = nu8;
	}

	public long getParentOwner() {
		return parentOwner;
	}

	public void setParentOwner(long parentOwner) {
		this.parentOwner = parentOwner;
	}

	public long getNu11() {
		return nu11;
	}

	public void setNu11(long nu11) {
		this.nu11 = nu11;
	}

	public long getNu12() {
		return nu12;
	}

	public void setNu12(long nu12) {
		this.nu12 = nu12;
	}

	public LongVector3 getSpawnPosition() {
		return spawnPosition;
	}

	public void setSpawnPosition(LongVector3 spawnPosition) {
		this.spawnPosition = spawnPosition;
	}

	public long getNu20() {
		return nu20;
	}

	public void setNu20(long nu20) {
		this.nu20 = nu20;
	}

	public long getNu21() {
		return nu21;
	}

	public void setNu21(long nu21) {
		this.nu21 = nu21;
	}

	public long getNu22() {
		return nu22;
	}

	public void setNu22(long nu22) {
		this.nu22 = nu22;
	}

	public byte getNu19() {
		return nu19;
	}

	public void setNu19(byte nu19) {
		this.nu19 = nu19;
	}

	public int getDebugCap() {
		return debugCap;
	}

	public void setDebugCap(int debugCap) {
		this.debugCap = debugCap;
	}
}
