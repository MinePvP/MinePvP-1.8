package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class LandCommand {
	
	MinePvP plugin;
	SimpleClans simpleClans;
	
	/**
	 * 
	 */
	public LandCommand() {
		plugin = MinePvP.getInstance();
		simpleClans = plugin.getSimpleClans();
	}
	
	/**
	 * 
	 * @param player
	 * @param arg
	 */
	public void execute( Player player, String[] arg ) {
		
		ClanPlayer clanPlayer = simpleClans.getClanManager().getClanPlayer(player);
		
		
		// Nur Clan Leader
		if ( clanPlayer.isLeader() ) {
			
			if ( arg[0].equalsIgnoreCase("buy") ) {
				
				if ( plugin.getClanManager().createClan( clanPlayer.getClan().getName(), player) ) {
					player.sendMessage("Land wurde gekauft!");
				} else {
					player.sendMessage("Land konnte nicht gekauft werden!");
				}
				
			} else if ( arg[0].equalsIgnoreCase("upgrade") ) {
				
				if ( plugin.getClanManager().upgradeClan(player) ) {
					player.sendMessage("Land wurde upgegradet!");
				} else {
					player.sendMessage("Land konnte nicht Upgraden!");
				}
				
			} else {
				player.sendMessage("Usage : /minepvp land buy|upgrade");
			}
			
		}
		
		
	}
	
	
}
