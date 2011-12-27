package ch.nonameweb.bukkit.plugins.minepvp.tasks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class ClanSpawnTask implements Runnable {
	
	private MinePvP plugin;
	private Clan clan;
	private Player player;
	
	private Integer lastX;
	private Integer lastY;
	private Integer lastZ;
	
	public ClanSpawnTask( MinePvP plugin, Clan clan, Player player, Integer lastX, Integer lastY, Integer lastZ ) {
		this.plugin = plugin;
		this.clan = clan;
		this.player = player;
		
		this.lastX = lastX;
		this.lastY = lastY;
		this.lastZ = lastZ;
	}
	
	@Override
	public void run() {
		
		if ( player.getLocation().equals( new Location(plugin.getServer().getWorld("world"), lastX, lastY, lastZ) ) ) {
			
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
