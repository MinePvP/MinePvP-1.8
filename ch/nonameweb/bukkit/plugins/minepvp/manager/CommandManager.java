package ch.nonameweb.bukkit.plugins.minepvp.manager;

import net.sacredlabyrinth.phaed.simpleclans.Helper;

import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.commands.BuyCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.HelpCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.InfoCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.ReloadCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.StatsCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.ClanSpawnCommand;
import ch.nonameweb.bukkit.plugins.minepvp.commands.UpgradeCommand;

public class CommandManager {
	
	// Commands	
	private InfoCommand infoCommand;
	private BuyCommand buyCommand;
	private UpgradeCommand upgradeCommand;
	private ReloadCommand reloadCommand;
	private HelpCommand helpCommand;
	private ClanSpawnCommand clanSpawnCommand;
	private StatsCommand statsCommand;
	
	/**
	 * 
	 */
	public CommandManager() {		
		infoCommand = new InfoCommand();
		buyCommand = new BuyCommand();
		upgradeCommand = new UpgradeCommand();
		reloadCommand = new ReloadCommand();
		helpCommand = new HelpCommand();
		clanSpawnCommand = new ClanSpawnCommand();
		statsCommand = new StatsCommand();
	}
	
	/**
	 * 
	 * @param player
	 * @param args
	 */
	public void processMinePvP( Player player, String[] args ) {
		
		
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
	    } else if ( subcommand.equalsIgnoreCase("clanspawn") ) {
        	clanSpawnCommand.execute(player, subargs);
	    } else if ( subcommand.equalsIgnoreCase("stats") ) {
        	statsCommand.execute(player, subargs);
	    } else {
        	player.sendMessage("Usage: /minepvp info|buy|upgrade");
        }
			
		
				
	}
	
}
