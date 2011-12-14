package ch.nonameweb.bukkit.plugins.minepvp.commands;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class BuyCommand {

	MinePvP plugin;
	SimpleClans simpleClans;
	
	public BuyCommand() {
		this.plugin = MinePvP.getInstance();
		this.simpleClans = this.plugin.getSimpleClans();
	}
	
	public void execute( Player player, String[] arg ) {
		
		ClanPlayer clanPlayer = simpleClans.getClanManager().getClanPlayer(player);
		
		if ( clanPlayer.isLeader() ) {
			
			if ( arg[0].equalsIgnoreCase("land") ) {
				
				if ( plugin.getClanManager().createClan( clanPlayer.getClan().getName(), player) ) {
					player.sendMessage(ChatColor.GREEN + "Land wurde gekauft.");
				} else {
					player.sendMessage(ChatColor.RED + "Land wurde nicht gekauft.");
				}
				
			} else if ( arg[0].equalsIgnoreCase("alertsystem") ) {
				
				if ( plugin.getClanManager().buyUpgradeAlertSystem(player) ) {					
					player.sendMessage(ChatColor.GREEN + "Alarmanlage wurde gekauft.");
				} else {
					player.sendMessage(ChatColor.RED + "Alarmanlage wurde nicht gekauft.");
				}
				
				
			} else if ( arg[0].equalsIgnoreCase("clanspawn") ) {
				
				if ( plugin.getClanManager().buyUpgradeClanSpawn(player) ) {					
					player.sendMessage(ChatColor.GREEN + "ClanSpawn wurde gekauft.");
				} else {
					player.sendMessage(ChatColor.RED + "ClanSpawn wurde nicht gekauft.");
				}
				
				
			} else if ( arg[0].equalsIgnoreCase("moat") ) {
				
				if ( plugin.getClanManager().buyUpgradeMoat(player) ) {					
					player.sendMessage(ChatColor.GREEN + "Wassergraben wurde gekauft.");
				} else {
					player.sendMessage(ChatColor.RED + "Wassergraben wurde nicht gekauft.");
				}
				
				
			} else {
				player.sendMessage(ChatColor.RED + "Benutzung : /minepvp buy land|alertsystem|clanspawn|moat");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "Du bist nicht Leader.");
		}
		
	}
	
	
}
