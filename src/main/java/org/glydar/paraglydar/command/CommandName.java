package org.glydar.paraglydar.command;

import java.util.Arrays;

import org.glydar.paraglydar.plugin.Plugin;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

/**
 * Represents the name of a command with support for nested commands
 * <p>
 * Note: This class is immutable
 */
public final class CommandName {

	private static final Joiner TO_STRING = Joiner.on(' ');

	private String[] parts;

	/**
	 * Creates a new CommandName with the given parts.
	 */
	public static CommandName of(String... arg) {
		String[] parts = new String[arg.length];
		checkAndAddParts(parts, 0, arg);
		return new CommandName(parts);
	}

	/**
	 * @see #of(String...)
	 */
	private CommandName(String[] parts) {
		this.parts = parts;
	}

	/**
	 * Size of this CommandName in terms of parts.
	 * <p>
	 * e.g. "cmd" is 1, "cmd sub" is 2
	 */
	public int size() {
		return parts.length;
	}

	/**
	 * Checks if this name is a top level name (only one part).
	 */
	public boolean isTopLevel() {
		return parts.length == 1;
	}

	/**
	 * Checks if this name has a parent.
	 */
	public boolean hasParent() {
		return parts.length > 1;
	}

	/**
	 * Returns the parent of this name.
	 * @throws NoParentCommandNameException if this name is a top level name.
	 */
	public CommandName getParent() {
		if (parts.length == 1) {
			throw new NoParentCommandNameException();
		}

		String[] parentParts = new String[parts.length - 1];
		System.arraycopy(parts, 0, parentParts, 0, parentParts.length);
		return new CommandName(parentParts);
	}

	/**
	 * Returns a new CommandName with the name of the plugin as the toplevel part
	 * and this name's parts as the other parts.
	 */
	public CommandName getPluginPrefixed(Plugin plugin) {
		String[] prefixedParts = new String[parts.length + 1];
		prefixedParts[0] = plugin.getName().toLowerCase();
		System.arraycopy(parts, 0, prefixedParts, 1, parts.length);
		return new CommandName(prefixedParts);
	}

	/**
	 * Returns this name new CommandName aliased for the given alias.
	 */
	public CommandName getAlias(String alias) {
		String[] aliasParts = new String[parts.length];
		System.arraycopy(parts, 0, aliasParts, 0, aliasParts.length - 1);
		checkAndAddParts(aliasParts, aliasParts.length - 1, alias);
		return new CommandName(aliasParts);
	}

	/**
	 * 
	 * @param arg
	 * @return
	 */
	public CommandName getChild(String... arg) {
		String[] childParts = new String[parts.length + arg.length];
		System.arraycopy(parts, 0, childParts, 0, parts.length);
		checkAndAddParts(childParts, parts.length, arg);
		return new CommandName(childParts);
	}

	/**
	 * Returns a friendly String representing this CommandName.
	 */
	@Override
	public String toString() {
		return TO_STRING.join(parts);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(parts);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CommandName)) {
			return false;
		}

		CommandName other = (CommandName) object;
		return Arrays.equals(parts, other.parts);
	}

	private static String[] checkAndAddParts(String[] dest, int from, String... src) {
		Preconditions.checkElementIndex(0, src.length, "Parts cannot be empty");
		for (int i = 0; i < dest.length; i++) {
			Preconditions.checkNotNull(src[i], "Command name parts cannot be null");
			Preconditions.checkArgument(!src[i].isEmpty(), "Command name parts cannot be empty");

			dest[from + i] = src[i].toLowerCase();
		}

		return dest;
	}

	/**
	 * Thrown when trying to get the parent of a top-level CommandName.
	 */
	public static class NoParentCommandNameException extends RuntimeException {

		private static final long serialVersionUID = 722666099534967954L;
	}
}
