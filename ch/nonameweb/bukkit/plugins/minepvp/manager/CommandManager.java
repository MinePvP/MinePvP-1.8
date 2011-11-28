package ch.nonameweb.bukkit.plugins.minepvp.manager;

import net.sacredlabyrinth.phaed.simpleclans.Helper;

import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.commands.InfoCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.LandCommand;

public class CommandManager {

	private MinePvP plugin;
	
	// Commands
	private LandCommand landCommand;
	private InfoCommand infoCommand;
	
	/**
	 * 
	 */
	public CommandManager() {
		this.plugin = MinePvP.getInstance();
		
		landCommand = new LandCommand();
		infoCommand = new InfoCommand();
	}
	
	/**
	 * 
	 * @param player
	 * @param args
	 */
	public void processMinePvP( Player player, String[] args ) {
		
		try {
		
			String subcommand = args[0];
	        String[] subargs = Helper.removeFirst(args);
	        
			if ( subcommand.equalsIgnoreCase("land") ) {
	            landCommand.execute(player, subargs);
	        } else if ( subcommand.equalsIgnoreCase("info") ) {
	        	infoCommand.execute(player, subargs);
	        } else {
	        	player.sendMessage("Usage: /minepvp land | ctb");
	        }
			
		}
		catch (Exception ex)
        {
			plugin.log("CommandManager - Fehler : " + ex.getMessage() );
        }
				
	}
	
	public LandCommand getLandCommand() {
		return this.landCommand;
	}
	
	public InfoCommand getInfoCommand() {
		return this.infoCommand;
	}
	
}
