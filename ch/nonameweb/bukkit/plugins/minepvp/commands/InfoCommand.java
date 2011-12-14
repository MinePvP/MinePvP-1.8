package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class InfoCommand {
	
	private MinePvP plugin;
	
	public InfoCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		if ( plugin.getClanManager().hasPlayerAClan(player) ) {
			Clan clan = plugin.getClanManager().getClanByPlayer(player);
			
			if ( clan != null ) {
				player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
				player.sendMessage(ChatColor.GREEN + "                            INFO " + clan.getName());
				player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
				player.sendMessage(ChatColor.WHITE + "Punkte : " + ChatColor.GRAY + clan.getPoints());
				player.sendMessage(ChatColor.WHITE + "Flags : " + ChatColor.GRAY + clan.getFlags());
				player.sendMessage(ChatColor.WHITE + "Landstufe : " + ChatColor.GRAY + clan.getStufe() + ChatColor.WHITE + " Radius : " + ChatColor.GRAY + clan.getRadius());
				player.sendMessage(ChatColor.WHITE + "Alertsystemstufe : " + ChatColor.GRAY + clan.getAlertsystem());
				player.sendMessage(ChatColor.WHITE + "Moat : " + ChatColor.GRAY + clan.getMoat());
				player.sendMessage(ChatColor.WHITE + "ClanSpawn : " + ChatColor.GRAY + clan.getClanSpawn());
				player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
			} else {
				player.sendMessage(ChatColor.GOLD + "Euer Clan hat noch kein Land.");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "Du bist in keinem Clan.");
		}
		
		
		
	}
	
}
