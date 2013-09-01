package org.glydar.paraglydar.command;

import java.util.Arrays;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public final class CommandName {

	private static final Joiner TO_STRING = Joiner.on(' ');

	private String[] parts;

	public static CommandName of(String... arg) {
		String[] parts = new String[arg.length];
		checkAndAddParts(arg, parts, 0);
		return new CommandName(parts);
	}

	private CommandName(String[] parts) {
		this.parts = parts;
	}

	public int size() {
		return parts.length;
	}

	public boolean hasParent() {
		return parts.length > 1;
	}

	public CommandName getParent() {
		if (parts.length == 1) {
			throw new NoParentCommandNameException();
		}

		String[] parentParts = new String[parts.length - 1];
		System.arraycopy(parts, 0, parentParts, 0, parentParts.length);
		return new CommandName(parentParts);
	}

	public CommandName getChild(String... arg) {
		String[] childParts = new String[parts.length + arg.length];
		System.arraycopy(parts, 0, childParts, 0, parts.length);
		checkAndAddParts(arg, childParts, parts.length);
		return new CommandName(childParts);
	}

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

	private static String[] checkAndAddParts(String[] src, String[] dest, int from) {
		Preconditions.checkElementIndex(0, src.length, "Parts cannot be empty");
		for (int i = 0; i < dest.length; i++) {
			Preconditions.checkNotNull(src[i], "Command name parts cannot be null");
			Preconditions.checkArgument(!src[i].isEmpty(), "Command name parts cannot be empty");

			dest[from + i] = src[i].toLowerCase();
		}

		return dest;
	}

	public static class NoParentCommandNameException extends RuntimeException {

		private static final long serialVersionUID = 722666099534967954L;
	}
}
