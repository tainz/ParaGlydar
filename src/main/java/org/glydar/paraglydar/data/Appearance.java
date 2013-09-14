package org.glydar.paraglydar.data;

import org.glydar.paraglydar.geom.FloatVector3;

public interface Appearance {

	public byte getNotUsed1();

	public void setNotUsed1(byte notUsed1);

	public byte getNotUsed2();

	public void setNotUsed2(byte notUsed2);

	public byte getHairR();

	public void setHairR(byte hairR);

	public byte getHairG();

	public void setHairG(byte hairG);

	public byte getHairB();

	public void setHairB(byte hairB);

	public byte getMovementFlags();

	public void setMovementFlags(byte movementFlags);

	public byte getEntityFlags();

	public void setEntityFlags(byte entityFlags);

	public float getScale();

	public void setScale(float scale);

	public float getBoundingRadius();

	public void setBoundingRadius(float boundingRadius);

	public float getBoundingHeight();

	public void setBoundingHeight(float boundingHeight);

	public int getHeadModel();

	public void setHeadModel(int headModel);

	public int getHairModel();

	public void setHairModel(int hairModel);

	public int getHandModel();

	public void setHandModel(int handModel);

	public int getFootModel();

	public void setFootModel(int footModel);

	public int getBodyModel();

	public void setBodyModel(int bodyModel);

	public int getBackModel();

	public void setBackModel(int backModel);

	public int getShoulderModel();

	public void setShoulderModel(int shoulderModel);

	public int getWingModel();

	public void setWingModel(int wingModel);

	public float getHeadScale();

	public void setHeadScale(float headScale);

	public float getHairScale();

	public void setHairScale(float hairScale);

	public float getHandScale();

	public void setHandScale(float handScale);

	public float getFootScale();

	public void setFootScale(float footScale);

	public float getBodyScale();

	public void setBodyScale(float bodyScale);

	public float getBackScale();

	public void setBackScale(float backScale);

	public float getUnknown();

	public void setUnknown(float unknown);

	public float getWingScale();

	public void setWingScale(float wingScale);

	public float getBodyPitch();

	public void setBodyPitch(float bodyPitch);

	public float getArmPitch();

	public void setArmPitch(float armPitch);

	public float getArmRoll();

	public void setArmRoll(float armRoll);

	public float getArmYaw();

	public void setArmYaw(float armYaw);

	public float getFeetPitch();

	public void setFeetPitch(float feetPitch);

	public float getWingPitch();

	public void setWingPitch(float wingPitch);

	public float getBackPitch();

	public void setBackPitch(float backPitch);

	public FloatVector3 getBodyOffset();

	public void setBodyOffset(FloatVector3 bodyOffset);

	public FloatVector3 getHeadOffset();

	public void setHeadOffset(FloatVector3 headOffset);

	public FloatVector3 getHandOffset();

	public void setHandOffset(FloatVector3 handOffset);

	public FloatVector3 getFootOffset();

	public void setFootOffset(FloatVector3 footOffset);

	public FloatVector3 getBackOffset();

	public void setBackOffset(FloatVector3 backOffset);

	public FloatVector3 getWingOffset();

	public void setWingOffset(FloatVector3 wingOffset);
	
	public float getShoulderScale();

	public void setShoulderScale(float shoulderScale);

	public float getWeaponScale();

	public void setWeaponScale(float weaponScale);
}
