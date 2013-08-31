package org.glydar.paraglydar.command;

import static org.junit.Assert.*;

import org.glydar.paraglydar.command.manager.CommandManager;
import org.glydar.paraglydar.test.DummyPlugin;
import org.glydar.paraglydar.test.NullLogger;
import org.junit.Before;
import org.junit.Test;

public class CommandManagerExecuteTest {

	private CommandManager commandManager;
	private DummyPlugin plugin;
	private TestCommandSender sender;

	@Before
	public void setUp() {
		this.commandManager = new CommandManager(new NullLogger());
		this.sender = new TestCommandSender();
		this.plugin = new DummyPlugin();

		commandManager.register(plugin, new TestCommandSet());
	}

	private CommandOutcome execute(String command, String... args) {
		return commandManager.execute(sender, command, args);
	}

	@Test
	public void testMissingCommand() {
		CommandOutcome outcome = execute("noSuchCommandShouldEverExist");

		assertEquals(CommandOutcome.NOT_HANDLED, outcome);
	}

	@Test
	public void testExecuted() {
		CommandOutcome outcome = execute("executed");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testMinArgs() {
		CommandOutcome outcome = execute("min");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}

	@Test
	public void testMaxArgs() {
		CommandOutcome outcome = execute("max", "arg1");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}

	@Test
	public void testPermission() {
		sender.blackListPermission("testpermission");
		CommandOutcome outcome = execute("permission", "arg1");

		assertEquals(CommandOutcome.NO_PERMISSION, outcome);
	}
}

class TestCommandSet implements CommandSet {

	@Command(name="executed")
	public CommandOutcome executed(CommandSender sender, String[] args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="min", min = 1)
	public CommandOutcome min(CommandSender sender, String[] args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="max", max = 0)
	public CommandOutcome max(CommandSender sender, String[] args) {
		return CommandOutcome.SUCCESS;
	}

	@Command(name="permission", permission = "testpermission")
	public CommandOutcome permission(CommandSender sender, String[] args) {
		return CommandOutcome.SUCCESS;
	}
}
