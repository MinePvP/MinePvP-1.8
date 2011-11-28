package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class InfoCommand {

	MinePvP plugin;
	
	/**
	 * 
	 */
	public InfoCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		player.sendMessage("----------------------------------------");
		player.sendMessage("INFO");
		player.sendMessage("----------------------------------------");
		
	}
	
}
