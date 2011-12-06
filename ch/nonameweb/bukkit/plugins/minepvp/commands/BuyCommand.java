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
					player.sendMessage("Land wurde gekauft!");
				} else {
					player.sendMessage("Land konnte nicht gekauft werden!");
				}
				
			} else if ( arg[0].equalsIgnoreCase("alertsystem") ) {
				
				if ( plugin.getClanManager().buyUpgradeAlertSystem(player) ) {					
					player.sendMessage("Alarmanlage wurde gekauft!");
				} else {
					player.sendMessage("Alarmanlage wurde nicht gekauft!");
				}
				
				
			} else if ( arg[0].equalsIgnoreCase("clanspawn") ) {
				
				if ( plugin.getClanManager().buyUpgradeTeamSpawn(player) ) {					
					player.sendMessage("ClanSpawn wurde gekauft!");
				} else {
					player.sendMessage("ClanSpawn wurde nicht gekauft!");
				}
				
				
			} else {
				player.sendMessage("Usage : /minepvp buy land|alertsystem|clanspawn");
			}
			
		}
		
	}
	
	
}
