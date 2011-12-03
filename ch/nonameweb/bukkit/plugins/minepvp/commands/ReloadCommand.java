package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class ReloadCommand {

	MinePvP plugin;
	
	/**
	 * 
	 */
	public ReloadCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		plugin.getClanManager().reloadClans();
		player.sendMessage( ChatColor.BLUE + "Config wurde neu geladen");
	}
	
}
