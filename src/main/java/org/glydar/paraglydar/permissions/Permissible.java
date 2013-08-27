package org.glydar.paraglydar.permissions;

/**
 * @author YoshiGenius
 */
public interface Permissible {

	public boolean hasPermission(String permission);

	public boolean hasPermission(Permission permission);

}
