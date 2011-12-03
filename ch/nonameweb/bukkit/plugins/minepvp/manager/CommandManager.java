package ch.nonameweb.bukkit.plugins.minepvp.manager;

import net.sacredlabyrinth.phaed.simpleclans.Helper;

import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.commands.BuyCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.HelpCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.InfoCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.ReloadCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.UpgradeCommand;

public class CommandManager {

	private MinePvP plugin;
	
	// Commands	
	private InfoCommand infoCommand;
	private BuyCommand buyCommand;
	private UpgradeCommand upgradeCommand;
	private ReloadCommand reloadCommand;
	private HelpCommand helpCommand;
	
	/**
	 * 
	 */
	public CommandManager() {
		this.plugin = MinePvP.getInstance();
		
		infoCommand = new InfoCommand();
		buyCommand = new BuyCommand();
		upgradeCommand = new UpgradeCommand();
		reloadCommand = new ReloadCommand();
		helpCommand = new HelpCommand();
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
	        
			if ( subcommand.equalsIgnoreCase("info") ) {
	        	infoCommand.execute(player, subargs);
	        } else if ( subcommand.equalsIgnoreCase("buy") ) {
	        	buyCommand.execute(player, subargs);
	        } else if ( subcommand.equalsIgnoreCase("upgrade") ) {
	        	upgradeCommand.execute(player, subargs);
	        } else if ( subcommand.equalsIgnoreCase("reload") ) {
	        	reloadCommand.execute(player, subargs);
		    } else if ( subcommand.equalsIgnoreCase("help") ) {
	        	helpCommand.execute(player, subargs);
		    } else {
	        	player.sendMessage("Usage: /minepvp info|buy|upgrade");
	        }
			
		}
		catch (Exception ex)
        {
			plugin.log("CommandManager - Fehler : " + ex.getMessage() );
        }
				
	}
	
}
