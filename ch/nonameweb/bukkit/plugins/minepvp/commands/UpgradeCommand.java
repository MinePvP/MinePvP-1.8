package ch.nonameweb.bukkit.plugins.minepvp.commands;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

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
					player.sendMessage("Land wurde upgegradet!");
				} else {
					player.sendMessage("Land konnte nicht Upgraden!");
				}

			} else if ( arg[0].equalsIgnoreCase("alertsystem") ) {
				
				if ( plugin.getClanManager().buyUpgradeAlertSystem(player) ) {
					player.sendMessage("Alarmanlage wurde gekauft!");
				}			
			
			} else {
				player.sendMessage("Usage : /minepvp upgrade land");
			}
			
		}
		
	}
	
	
}
