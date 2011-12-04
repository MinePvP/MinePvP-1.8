package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class TeamSpawnCommand {

	MinePvP plugin;
	
	/**
	 * 
	 */
	public TeamSpawnCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		Clan clan = plugin.getClanManager().getClanByPlayer(player);
		
		if ( clan != null ) {
			
			if ( clan.getTeamSpawn() == true ) {
				player.teleport( clan.getBaseLocation() );
			} else {
				player.sendMessage("Euer Clan besitzt noch keinen TeamSpawn!");
			}			
			
		}
		
	}
	
}
