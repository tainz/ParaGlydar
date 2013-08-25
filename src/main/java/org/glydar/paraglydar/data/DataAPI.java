package org.glydar.paraglydar.data;

public abstract class DataAPI {

	//Creating Vectors
	public abstract Vector3 Vector3();
	
	public abstract Vector3 Vector3(float x, float y, float z);
	
	public abstract <T extends Number> Vector3 Vector3(Class<T> type);
	
	public abstract <T extends Number> Vector3 Vector3(T x, T y, T z);
	
	public abstract Vector3 Vector3(Vector3 v);
	
	//Creating EntityData
	public abstract EntityData EntityData();
	
	public abstract EntityData EntityData(EntityData e);
}
