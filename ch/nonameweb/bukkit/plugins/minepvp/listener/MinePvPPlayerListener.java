package ch.nonameweb.bukkit.plugins.minepvp.listener;

import net.sacredlabyrinth.phaed.simpleclans.Helper;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.manager.ClanManager;

public class MinePvPPlayerListener extends PlayerListener{

	private MinePvP plugin;
	private ClanManager clanManager;
	
	public MinePvPPlayerListener() {
		plugin = MinePvP.getInstance();
		clanManager = plugin.getClanManager();
	}
	
	public void onPlayerCommandPreprocess( PlayerCommandPreprocessEvent event ) {
		
		if ( event.isCancelled() ) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if ( player == null ) {
			return;
		}
		
		String[] split = event.getMessage().substring(1).split(" ");
		
		if ( split.length == 0 ) {
			return;
		}
		
		String command = split[0];
		
		if ( command.equalsIgnoreCase("minepvp") ) {
			
			player.sendMessage("Command: minepvp");
			
			plugin.getCommandManager().processMinePvP(player, Helper.removeFirst(split));
		}
	}
	
	public void onPlayerMove ( PlayerMoveEvent event ) {
		
		if ( event.isCancelled() ) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if ( player == null ) {
			return;
		}
		
		Clan clanFrom = clanManager.getClanByLocation( event.getFrom() );
		Clan clanTo = clanManager.getClanByLocation( event.getTo() );
		
		if ( clanFrom == null ) {
			
			if ( clanTo != null ) {
				player.sendMessage("Du betrittst das gebiebt von " + clanTo.getName() + "!" );
			}
			
		} else {
			
			if ( clanTo == null ) {
				player.sendMessage("Du verlässt das gebiet von " + clanFrom.getName() + "!");
			}
			
		}
		
	}
	
	
	public void onPlayerQuit ( PlayerQuitEvent event ) {
		
		Player player = event.getPlayer();
		
		if ( player == null ) {
			return;
		}
		
		// Wenn jemand noch eine Flagge hat wird diese Resettet
		if ( player.getInventory().getHelmet().getTypeId() == 35  ) {
			plugin.getClanManager().resetFlag(player);
		}
		
		
		
	}
	
}
