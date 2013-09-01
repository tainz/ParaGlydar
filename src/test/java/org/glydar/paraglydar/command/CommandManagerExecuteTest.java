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
		this.plugin = new DummyPlugin();
		this.sender = new TestCommandSender();
	}

	private <S extends CommandSet> S register(S set) {
		commandManager.register(plugin, set);
		return set;
	}

	private CommandOutcome execute(String... args) {
		return commandManager.execute(sender, args);
	}

	@Test
	public void testMissingCommand() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("noSuchCommandShouldEverExist");

		assertEquals(CommandOutcome.NOT_HANDLED, outcome);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRaiseExceptionWithEmptyArgs() {
		register(new TestCommandSet());
		execute("executed", "");
	}

	@Test
	public void testExecuted() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("executed");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testUppercased() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("upPERCAseD");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testNestedCommand() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("nested", "command");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testCallWithPluginPrefix() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("dummyplugin", "executed");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testConflict() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("conflict");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testPermission() {
		register(new TestCommandSet());
		sender.blackListPermission("testpermission");
		CommandOutcome outcome = execute("permission");

		assertEquals(CommandOutcome.NO_PERMISSION, outcome);
	}

	@Test
	public void testPlayerOnly() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("player");

		assertEquals(CommandOutcome.UNSUPPORTED_SENDER, outcome);
	}

	@Test
	public void testTestSenderOnly() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("testsender");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testNoArgs() {
		TestCommandSet commands = register(new TestCommandSet());
		CommandOutcome outcome = execute("none");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertNull(commands.arg1);
		assertNull(commands.arg2);
		assertNull(commands.rest);
	}

	@Test
	public void testMandatoryArgs() {
		TestCommandSet commands = register(new TestCommandSet());
		CommandOutcome outcome = execute("mandatory", "arg1", "arg2");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertEquals("arg1", commands.arg1);
		assertEquals("arg2", commands.arg2);
		assertNull(null, commands.rest);
	}

	@Test
	public void testRestArgs() {
		TestCommandSet commands = register(new TestCommandSet());
		CommandOutcome outcome = execute("rest", "arg1", "arg2");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertNull(commands.arg1);
		assertNull(commands.arg2);
		assertArrayEquals(new String[] { "arg1", "arg2" }, commands.rest);
	}

	@Test
	public void testMandatoryRestArgs() {
		TestCommandSet commands = register(new TestCommandSet());
		CommandOutcome outcome = execute("mandatoryrest", "arg1", "arg2", "arg3", "arg4");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertEquals("arg1", commands.arg1);
		assertEquals("arg2", commands.arg2);
		assertArrayEquals(new String[] { "arg3", "arg4" }, commands.rest);
	}

	@Test
	public void testMinArgs() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("mandatory");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}

	@Test
	public void testMaxMandatoryArgs() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("mandatory", "arg1", "arg2", "arg3");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}

	@Test
	public void testMaxRestArgs() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("rest", "arg1", "arg2", "arg3");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}
}
