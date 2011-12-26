package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class ClanSpawnCommand {

	MinePvP plugin;
	
	/**
	 * 
	 */
	public ClanSpawnCommand() {
		plugin = MinePvP.getInstance();
	}
	
	public void execute( Player player, String[] arg ) {
		
		Clan clan = plugin.getClanManager().getClanByPlayer(player);
		
		if ( clan != null ) {
			
			if ( plugin.getClanManager().hasPlayerAFlag(player) == false ) {
				if ( clan.getClanSpawn() == true ) {
					player.sendMessage(ChatColor.GREEN + "Teleportiert euch zum Clanspawn ClanSpawn!");
					player.teleport( new Location( plugin.getServer().getWorld("world") , clan.getBaseX() -2, clan.getBaseY(), clan.getBaseZ() ) );
				} else {
					player.sendMessage(ChatColor.GOLD + "Euer Clan besitzt noch keinen ClanSpawn!");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Du bist im besitz einer Flagge und kannst dich nicht Teleportieren");
			}
			
			
		} else {
			player.sendMessage(ChatColor.RED + "Euer Clab bestitz noch kein Land oder du bist in keinem Clan!");
		}
		
	}
	
}
