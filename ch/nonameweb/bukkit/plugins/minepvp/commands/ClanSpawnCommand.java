package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.tasks.ClanSpawnTask;

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
					
					if ( plugin.getClanManager().getClanByLocation( player.getLocation() ) == null || 
						 plugin.getClanManager().getClanByLocation( player.getLocation() ).getName().equalsIgnoreCase( clan.getName() ) ) {
						
						player.sendMessage(ChatColor.GOLD + "Du wirst in 5 Sekunden geportet, bitte bewege dich nicht.");
						
						plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new ClanSpawnTask(plugin, clan.getName(), player.getName(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()), 100L);
						
						
					} else {
						player.sendMessage(ChatColor.GOLD + "In einem feindlichen Kšnigreich geht das nicht.");
					}
					
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
