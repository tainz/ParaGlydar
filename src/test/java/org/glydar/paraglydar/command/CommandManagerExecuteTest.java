package org.glydar.paraglydar.command;

import static org.junit.Assert.*;

import org.glydar.paraglydar.command.manager.CommandManager;
import org.glydar.paraglydar.models.Player;
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
	}

	private <S extends CommandSet> S register(S set) {
		commandManager.register(plugin, set);
		return set;
	}

	private CommandOutcome execute(String command, String... args) {
		return commandManager.execute(sender, command, args);
	}

	@Test
	public void testMissingCommand() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("noSuchCommandShouldEverExist");

		assertEquals(CommandOutcome.NOT_HANDLED, outcome);
	}

	@Test
	public void testExecuted() {
		register(new TestCommandSet());
		CommandOutcome outcome = execute("executed");

		assertEquals(CommandOutcome.SUCCESS, outcome);
	}

	@Test
	public void testPermission() {
		register(new TestCommandSet());
		sender.blackListPermission("testpermission");
		CommandOutcome outcome = execute("permission", "arg1");

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
		TestArgsCommandSet commands = register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("none");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertNull(commands.arg1);
		assertNull(commands.arg2);
		assertNull(commands.rest);
	}

	@Test
	public void testMandatoryArgs() {
		TestArgsCommandSet commands = register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("mandatory", "arg1", "arg2");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertEquals("arg1", commands.arg1);
		assertEquals("arg2", commands.arg2);
		assertNull(null, commands.rest);
	}

	@Test
	public void testRestArgs() {
		TestArgsCommandSet commands = register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("rest", "arg1", "arg2");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertNull(commands.arg1);
		assertNull(commands.arg2);
		assertArrayEquals(new String[] { "arg1", "arg2" }, commands.rest);
	}

	@Test
	public void testMandotoryRestArgs() {
		TestArgsCommandSet commands = register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("mandatoryrest", "arg1", "arg2", "arg3", "arg4");

		assertEquals(CommandOutcome.SUCCESS, outcome);
		assertEquals("arg1", commands.arg1);
		assertEquals("arg2", commands.arg2);
		assertArrayEquals(new String[] { "arg3", "arg4" }, commands.rest);
	}

	@Test
	public void testMinArgs() {
		register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("mandatory");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}

	@Test
	public void testMaxMandatoryArgs() {
		register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("mandatory", "arg1", "arg2", "arg3");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}

	@Test
	public void testMaxRestArgs() {
		register(new TestArgsCommandSet());
		CommandOutcome outcome = execute("rest", "arg1", "arg2", "arg3");

		assertEquals(CommandOutcome.WRONG_USAGE, outcome);
	}
}

class TestCommandSet implements CommandSet {

	@Command(name="executed")
	public CommandOutcome executed(CommandSender sender, String... args) {
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
}

class TestArgsCommandSet implements CommandSet {

	String arg1;
	String arg2;
	String[] rest;

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
