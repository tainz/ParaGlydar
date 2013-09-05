package org.glydar.paraglydar.data;

public interface DataCreator {

	public EntityData createEntityData();

	public EntityData createEntityData(EntityData e);
	
	public Appearance createAppearance();

	public Appearance createAppearance(Appearance a);
}
