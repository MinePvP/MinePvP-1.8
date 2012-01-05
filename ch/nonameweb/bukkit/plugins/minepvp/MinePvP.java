package ch.nonameweb.bukkit.plugins.minepvp;

import java.util.Arrays;
import java.util.logging.Logger;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ch.nonameweb.bukkit.plugins.minepvp.listener.MinePvPBlockListener;
import ch.nonameweb.bukkit.plugins.minepvp.listener.MinePvPEntityListener;
import ch.nonameweb.bukkit.plugins.minepvp.listener.MinePvPPlayerListener;
import ch.nonameweb.bukkit.plugins.minepvp.manager.ClanManager;
import ch.nonameweb.bukkit.plugins.minepvp.manager.CommandManager;
import ch.nonameweb.bukkit.plugins.minepvp.manager.SettingsManager;


public class MinePvP extends JavaPlugin {
	
	// SimpleClans MinePvP Plugin
	private static MinePvP plugin;
	
	// SimpleClans Plugin
	private SimpleClans simpleClans;
	
	// Managers
	private CommandManager commandManager;
	private ClanManager clanManager;
	private SettingsManager settingsManager;
	
	// Listeners
	private MinePvPPlayerListener playerListener;
	private MinePvPBlockListener blockListener;
	private MinePvPEntityListener entityListener;
	
	// Debug
	private Boolean debug;
	
	@Override
	public void onEnable() {
		
		// Plugin Instanz speichern
		plugin = this;
		
		// SimpleClans Plugin laden
		Plugin plug = getServer().getPluginManager().getPlugin("SimpleClans");

	    if (plug != null)
	    {
	        simpleClans = ((SimpleClans) plug);
	    }
	    
	    // Debug
	    //debug = getConfig().getBoolean("Global.Settings.Debug", false);
	    
	    // Default Settings
	    //loadConfig();
	    settingsManager = new SettingsManager();
	    
	    
	    // Managers
	    commandManager = new CommandManager();
	    clanManager = new ClanManager();
	    clanManager.loadClans();
	    
	    // Listeners
	    playerListener = new MinePvPPlayerListener();
	    blockListener = new MinePvPBlockListener();
	    entityListener = new MinePvPEntityListener();
		
	    registerEvents();
	    
	    log("enabled");
	    
	}
	
	@Override
	public void onDisable() {
		
		
		// ClanManager
		clanManager.saveClans();
		
		
		// Config Speichern
		//saveConfig();	
		settingsManager.save();
		
		log("disabled");
		
	}
	
	public void log( String message ) {
		
		Logger.getLogger("Minecraft").info("[MinePvP] : " + message);
		
		//logger.info(ChatColor.BLUE + "[MinePvP] : " + message);
	}
	
	private void registerEvents() {
		
		// Player
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_TELEPORT, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_KICK, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_BUCKET_EMPTY, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Lowest, this);
        
        // Entity
        getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DEATH, entityListener, Priority.Lowest, this);
		
        // Block
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACE, blockListener, Priority.Lowest, this);

	}
	
	public static MinePvP getInstance()
    {
        return plugin;
    }
	
	public Boolean getDebug() {
		return debug;
	}
	
	public SimpleClans getSimpleClans() {
		return simpleClans;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public ClanManager getClanManager() {
		return clanManager;
	}
	
	public SettingsManager getSettingsManager() {
		return settingsManager;
	}
	
}
