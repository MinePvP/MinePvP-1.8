package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class HelpCommand {

	MinePvP plugin;
	
	/**
	 * 
	 */
	public HelpCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		player.sendMessage(ChatColor.GREEN + "                            Hilfe");
		player.sendMessage(ChatColor.WHITE + "/minepvp info " + ChatColor.GRAY + "// Giebt auskunft Ÿber das Plugin");
		player.sendMessage(ChatColor.WHITE + "/minepvp help " + ChatColor.GRAY + "// Giebt auskunft Ÿber die verschiedenen Befehle");
		player.sendMessage(ChatColor.WHITE + "/minepvp reload " + ChatColor.GRAY + "// Reloadet die Config von MinePvP");
		player.sendMessage(ChatColor.WHITE + "/minepvp buy land | alertsystem | clanspawn | moat" + ChatColor.GRAY + "// Kaufen von Land, Alarmanlage, ClanSpawn oder dem Wassergraben");
		player.sendMessage(ChatColor.WHITE + "/minepvp upgrade land | alertsystem" + ChatColor.GRAY + "// Upgraden des Landes oder der Alarmanlage");
		player.sendMessage(ChatColor.WHITE + "/minepvp clanspawn " + ChatColor.GRAY + "// Teleportiert einem zum TeamSpawn(Base)");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		
	}
	
}
