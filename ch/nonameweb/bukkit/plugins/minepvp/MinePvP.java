package ch.nonameweb.bukkit.plugins.minepvp;

import java.util.Arrays;
import java.util.logging.Logger;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ch.nonameweb.bukkit.plugins.minepvp.listener.MinePvPBlockListener;
import ch.nonameweb.bukkit.plugins.minepvp.listener.MinePvPEntityListener;
import ch.nonameweb.bukkit.plugins.minepvp.listener.MinePvPPlayerListener;
import ch.nonameweb.bukkit.plugins.minepvp.manager.ClanManager;
import ch.nonameweb.bukkit.plugins.minepvp.manager.CommandManager;


public class MinePvP extends JavaPlugin {
	
	// SimpleClans MinePvP Plugin
	private static MinePvP plugin;
	
	// SimpleClans Plugin
	private SimpleClans simpleClans;
	
	// Managers
	private CommandManager commandManager;
	private ClanManager clanManager;
	
	// Listeners
	private MinePvPPlayerListener playerListener;
	private MinePvPBlockListener blockListener;
	private MinePvPEntityListener entityListener;
	
	// Logger
	private Logger logger = Logger.getLogger("Minecraft");
	
	// Debug
	private Boolean debug;
	
	@Override
	public void onEnable() {
		
		// Plugin Instanz speichern
		this.plugin = this;
		
		// SimpleClans Plugin laden
		Plugin plug = getServer().getPluginManager().getPlugin("SimpleClans");

	    if (plug != null)
	    {
	        simpleClans = ((SimpleClans) plug);
	    }
	    
	    // Debug
	    debug = getConfig().getBoolean("Global.Settings.Debug", false);
	    
	    // Default Settings
	    loadConfig();
	    
	    // Managers
	    commandManager = new CommandManager();
	    clanManager = new ClanManager();
	    
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
		saveConfig();
		
		log("disabled");
		
	}
	
	public void log( String message ) {
		logger.info(ChatColor.AQUA + "[MinePvP] : " + message);
	}
	
	private void loadConfig() {
		
		getConfig().addDefault("Global.Settings.Debug", false);
		
		// Sonstiges
		getConfig().addDefault("Global.Settings.Land.DMGonBlockDMG", 2);
		
		// Land Kaufen Upgraden Kosten
	    getConfig().addDefault("Global.Settings.Land.ErsteStufe.Radius", 20);
	    getConfig().addDefault("Global.Settings.Land.ErsteStufe.Kosten", 40);
	    getConfig().addDefault("Global.Settings.Land.ErsteStufe.MinSpieler", 3);
	    
	    getConfig().addDefault("Global.Settings.Land.MinAbstandZwischenClans", 1000);
	    
	    // Stufen
	    String[] listStufenRadius = {"30", "40", "50", "60", "70", "80", "90", "100", "110", "120"};
	    String[] listStufenKosten = {"5", "5", "10", "10", "20", "20", "30", "30", "40", "40"};
	    
	    getConfig().addDefault("Global.Settings.Land.Stufen.Radius", Arrays.asList( listStufenRadius ));
	    getConfig().addDefault("Global.Settings.Land.Stufen.Kosten", Arrays.asList( listStufenKosten ));
	    
	    // Capture the Block
	    getConfig().addDefault("Global.Settings.CTB.PointsPerBlock", 2);
	    getConfig().addDefault("Global.Settings.CTB.MinPlayerOnline", 3);
	    
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void registerEvents() {
		
		// Player
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_TELEPORT, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_BUCKET_EMPTY, playerListener, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Lowest, this);
        
        // Ent
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
}
