package ch.nonameweb.bukkit.plugins.minepvp.commands;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

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
				
			} else if ( arg[0].equalsIgnoreCase("alert") ) {
				// TODO Alert System einbauen
			} else {
				player.sendMessage("Usage : /minepvp buy land|alert");
			}
			
		}
		
	}
	
	
}