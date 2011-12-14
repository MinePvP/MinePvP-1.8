package ch.nonameweb.bukkit.plugins.minepvp.commands;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class UpgradeCommand {

	MinePvP plugin;
	SimpleClans simpleClans;
	
	public UpgradeCommand() {
		this.plugin = MinePvP.getInstance();
		this.simpleClans = plugin.getSimpleClans();
	}
	
	public void execute( Player player, String[] arg ) {
		
		ClanPlayer clanPlayer = simpleClans.getClanManager().getClanPlayer(player);
		
		if ( clanPlayer.isLeader() ) {
			
			if ( arg[0].equalsIgnoreCase("land") ) {
				
				if ( plugin.getClanManager().upgradeClan(player) ) {
					player.sendMessage(ChatColor.GREEN + "Land wurde upgegradet.");
				} else {
					player.sendMessage(ChatColor.RED + "Land konnte nicht upgegradet werden.");
				}

			} else if ( arg[0].equalsIgnoreCase("alertsystem") ) {
				
				if ( plugin.getClanManager().buyUpgradeAlertSystem(player) ) {
					player.sendMessage(ChatColor.GREEN + "Alarmanlage wurde upgegraded");
				} else {
					player.sendMessage(ChatColor.RED + "Alarmanlage konnte nicht upgegradet werden.");
				}
			
			} else {
				player.sendMessage(ChatColor.RED + "Benutzung : /minepvp upgrade land|alertsystem");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "Du bist nicht Leader.");
		}
		
	}
	
	
}
