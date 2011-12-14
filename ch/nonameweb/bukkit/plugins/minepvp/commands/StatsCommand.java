package ch.nonameweb.bukkit.plugins.minepvp.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class StatsCommand {

	MinePvP plugin;
	
	/**
	 * 
	 */
	public StatsCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		ArrayList<Clan> clans = plugin.getClanManager().getClans();
		
		
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		player.sendMessage(ChatColor.GREEN + "                            Stats");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		player.sendMessage(ChatColor.WHITE + "Clan Name        Erroberte Flaggen");
		
		for ( Clan clan : clans ) {
			player.sendMessage(ChatColor.WHITE + clan.getName() + "              " + ChatColor.RED + clan.getFlags());
		}
		
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		
	}
	
}
