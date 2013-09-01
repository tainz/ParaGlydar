package org.glydar.paraglydar.command;

import org.glydar.paraglydar.models.Player;

public class TestCommandSet implements CommandSet {

	String arg1;
	String arg2;
	String[] rest;

	@Command(name="executed")
	public CommandOutcome executed(CommandSender sender, String... args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="UPperCAseD")
	public CommandOutcome uppercased(CommandSender sender, String... args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name={"nested", "command"})
	public CommandOutcome nestedCommand(CommandSender sender, String... args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="withalias", aliases = "conflict")
	public CommandOutcome withalias(CommandSender sender, String... args) {
		return CommandOutcome.ERROR;
	}

	@Command(name="conflict")
	public CommandOutcome conflict(CommandSender sender, String... args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="permission", permission = "testpermission")
	public CommandOutcome permission(CommandSender sender, String... args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="player")
	public CommandOutcome player(Player sender, String... args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="testsender")
	public CommandOutcome testsender(TestCommandSender sender, String[] args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="none")
	public CommandOutcome none(CommandSender sender) {
		this.arg1 = null;
		this.arg2 = null;
		this.rest = null;
		return CommandOutcome.SUCCESS;
	}

	@Command(name="mandatory")
	public CommandOutcome mandatory(CommandSender sender, String arg1, String arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.rest = null;
		return CommandOutcome.SUCCESS;
	}

	@Command(name="rest", maxArgs = 2)
	public CommandOutcome rest(CommandSender sender, String... rest) {
		this.arg1 = null;
		this.arg2 = null;
		this.rest = rest;
		return CommandOutcome.SUCCESS;
	}

	@Command(name="mandatoryrest")
	public CommandOutcome mandatoryrest(CommandSender sender, String arg1, String arg2, String... rest) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.rest = rest;
		return CommandOutcome.SUCCESS;
	}
}
