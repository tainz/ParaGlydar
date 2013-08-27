package org.glydar.paraglydar.data;


public interface DataCreator {

	public Vector3<Float> createVector3(float x, float y, float z);

	public <T extends Number> Vector3<T> createVector3(Class<T> type);

	public <T extends Number> Vector3<T> createVector3(T x, T y, T z);

	public <T extends Number> Vector3<T> createVector3(Vector3<T> v);

	public EntityData createEntityData();

	public EntityData createEntityData(EntityData e);
	
	public Appearance createAppearance();

	public Appearance createAppearance(Appearance a);
}
