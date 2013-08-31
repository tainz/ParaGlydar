package org.glydar.paraglydar.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.glydar.paraglydar.permissions.Permission;

class TestCommandSender implements CommandSender {

	private Set<String> permissionsBlacklist = new HashSet<>();
	private List<String> messages = new ArrayList<>();

	@Override
	public boolean hasPermission(String permission) {
		return !permissionsBlacklist.contains(permission);
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return !permissionsBlacklist.contains(permission.getPermission());
	}

	@Override
	public String getName() {
		return "*Test*";
	}

	@Override
	public void sendMessage(String message) {
		messages.add(message);
	}

	public void blackListPermission(String permission) {
		permissionsBlacklist.add(permission);
	}

	public List<String> getReceivedMessage() {
		return messages;
	}
}
