package ch.nonameweb.bukkit.plugins.minepvp.tasks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class LogoutTask implements Runnable {
	
	private MinePvP plugin;
	private String clanString;
	private String playerString;
	
	private Integer lastX;
	private Integer lastY;
	private Integer lastZ;
	
	public LogoutTask( MinePvP plugin, String clanString, String playerString, Integer lastX, Integer lastY, Integer lastZ ) {
		this.plugin = plugin;
		this.clanString = clanString;
		this.playerString = playerString;
		
		this.lastX = lastX;
		this.lastY = lastY;
		this.lastZ = lastZ;
	}
	
	@Override
	public void run() {
		
		Player player = plugin.getServer().getPlayer(playerString);
		Clan clan = plugin.getClanManager().getClanByName(clanString);
		
		if ( lastX == player.getLocation().getBlockX() && lastY == player.getLocation().getBlockY() && lastZ == player.getLocation().getBlockZ() ) {
			
			if ( clan.getClanSpawnX() != 0 && clan.getClanSpawnZ() != 0 ) {
				player.teleport( clan.getClanSpawnLocation() );
			} else {
				player.teleport( new Location( plugin.getServer().getWorld("world") , clan.getBaseX() -2, clan.getBaseY(), clan.getBaseZ() ) );
			}
			
		} else {
			player.sendMessage(ChatColor.GOLD + "Teleporierung abgebrochen, du hast dich bewegt.");
		}
		
		
		
		
	}

}
