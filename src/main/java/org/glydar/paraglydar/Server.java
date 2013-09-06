package org.glydar.paraglydar;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.glydar.paraglydar.command.manager.CommandManager;
import org.glydar.paraglydar.event.manager.EventManager;
import org.glydar.paraglydar.models.Entity;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.models.World;
import org.glydar.paraglydar.permissions.Permission;

public interface Server {

	/**
	 * Gets the event manager currently used
	 * @return The EventManager that Glydar is currently using
	 */
	public EventManager getEventManager();
	
	/**
	 * Gets the command manager currently used
	 * @return The CommandManager that Glydar is currently using
	 */
	public CommandManager getCommandManager();

	/**
	 * Gets the server's name
	 * @return Name of the server
	 */
	public String getName();

	/**
	 * Gets the server's implementation version
	 * @return Version of the server
	 */
	public String getVersion();

	/**
	 * Gets all currently connected players
	 * @return A collection of all players that are connected to the server
	 */
	public Collection<Player> getConnectedPlayers();

	/**
	 * Gets all currently connected entities
	 * @return A collection of all entities that are connected to the server
	 */
	public Collection<Entity> getConnectedEntities();

	/**
	 * Gets the entity that has the provided id
	 * @param id The id to find an entity with
	 * @return The entity that has that id. Null if not found.
	 */
	public Entity getEntityByEntityID(long id);

	/**
	 * Gets the player that has the provided id
	 * @param id The id to find an player with
	 * @return The player that has that id. Null if not found.
	 */
	public Player getPlayerByEntityID(long id);
	
	/**
	 * Gets the default world of the server that all players automatically connect to
	 * @return The default world
	 */
	public World getDefaultWorld();
	
	/**
	 * Gets all the currently loaded worlds
	 * @return A list containing all currently loaded worlds
	 */
	public List<World> getWorlds();

	/**
	 * Gets the logger for this runtime
	 * @return This runtime's logger
	 */
	public Logger getLogger();

	/**
	 * Returns true if the server is currently running.
	 * @return Whether or not the server is currently running.
	 */
	public boolean isRunning();

	/**
	 * Forcefully shuts down the server
	 */
	public void shutdown();

	/**
	 * Broadcasts the specified message to all currently connected servers
	 * @param message The message to be broadcasted
	 */
	public void broadcastMessage(String message);

	/**
	 * Broadcasts the specified message to all players online with the specified permission
	 * @param message The message to be broadcasted
	 * @param permission The permission to be matched against
	 */
	public void broadcast(String message, String permission);

	/**
	 * Broadcasts the specified message to all players online with the specified permission
	 * @param message The message to be broadcasted
	 * @param permission The permission to be matched against
	 */
	public void broadcast(String message, Permission permission);
	
	/**
	 * Returns true if the server is currently in debug mode
	 * @return Whether or not the server is currently in debug mode
	 */
	public boolean isDebugging();
	
	/**
	 * If debugging is enabled, then the specified message is printed to the console
	 * @param message The debug message to be sent
	 */
	public void debug(String message);
	
	/**
	 * Returns the maximum amount of players that are allowed on the server at any one point in time
	 * @return Maximum amount of players
	 */
	public int getMaxPlayers();
	
	/**
	 * Cheaty way to set the maximum amount of players
	 * @param maxPlayers Maximum amount of players
	 */
	public void setMaxPlayers(int maxPlayers);
	
	/**
	 * Returns the port that the server is currently running on
	 * @return The port
	 */
	public int getPort();
	
}
