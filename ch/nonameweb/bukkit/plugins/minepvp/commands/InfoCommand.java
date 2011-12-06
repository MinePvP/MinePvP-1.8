package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoCommand {
	
	/**
	 * 
	 */
	public InfoCommand() {

	}
	
	public void execute( Player player, String[] arg ) {
		
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		player.sendMessage(ChatColor.GREEN + "                            INFO");
		player.sendMessage(ChatColor.WHITE + "Entwickler : " + ChatColor.GRAY + "surtic86");
		player.sendMessage(ChatColor.WHITE + "Version : 0.3");
		player.sendMessage(ChatColor.WHITE + "Webseite : " + ChatColor.GRAY + "http://www.minepvp.ath.cx");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		
	}
	
}
