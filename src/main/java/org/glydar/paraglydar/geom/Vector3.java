package org.glydar.paraglydar.geom;

public interface Vector3<N extends Number, V extends Vector3<N, V>> extends Comparable<V> {

	double length();

	double distance(V other);

	V add(V other);

	V subtract(V other);

	V multiply(V other);

	V divide(V other);

	IntVector3 toIntVector3();

	LongVector3 toLongVector3();

	FloatVector3 toFloatVector3();
}
