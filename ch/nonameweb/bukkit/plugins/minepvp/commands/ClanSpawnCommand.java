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
					
					if ( plugin.getClanManager().getClanByLocation( player.getLocation() ) == null || 
						 plugin.getClanManager().getClanByLocation( player.getLocation() ).getName().equalsIgnoreCase( clan.getName() ) ) {
						
						player.sendMessage(ChatColor.GREEN + "Teleportiert euch in 5 Sekunden zum Clanspawn ClanSpawn!");
						
						long startet = System.currentTimeMillis();
						long now = System.currentTimeMillis();
						long end = startet + 5000;
						
						Integer x = player.getLocation().getBlockX();
						Location startLocation = player.getLocation();
						
						while ( now < end ) {
							now = System.currentTimeMillis();
						}
						
						player.sendMessage("Start X :" + startLocation.getBlockX() + "Start X Int :" + x + " End X : " + plugin.getServer().getPlayer(player.getName()).getLocation().getBlockX() );
						
						if ( startLocation.getBlockX() == player.getLocation().getBlockX() && 
							 startLocation.getBlockY() == player.getLocation().getBlockY() &&
							 startLocation.getBlockZ() == player.getLocation().getBlockZ() ) {
							
							if ( clan.getClanSpawnX() != null ) {
								player.teleport( clan.getClanSpawnLocation() );
							} else {
								player.teleport( new Location( plugin.getServer().getWorld("world") , clan.getBaseX() -2, clan.getBaseY(), clan.getBaseZ() ) );
							}
							
						} else {
							player.sendMessage(ChatColor.GOLD + "Teleporierung abgebrochen, du hast dich bewegt.");
						}
						
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
