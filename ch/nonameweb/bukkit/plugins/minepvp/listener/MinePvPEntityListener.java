package ch.nonameweb.bukkit.plugins.minepvp.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.manager.ClanManager;

public class MinePvPEntityListener extends EntityListener {

	private MinePvP plugin;
	private ClanManager clanManager;
	
	/**
	 * 
	 */
	public MinePvPEntityListener() {
		plugin = MinePvP.getInstance();
		clanManager = plugin.getClanManager();
	}
	
	/**
	 * 
	 * @param event
	 */
	public void onEntityDeath ( EntityDeathEvent event ) {
		
		Entity ent = event.getEntity();
		
		// Wenn entity ein Spieler ist
	    if (ent instanceof Player) {
	    	// Wenn jemand noch eine Flagge hat wird diese Resettet    	
			if ( ((Player) ent).getInventory().getHelmet().getTypeId() == 35  ) {
				
				((Player) ent).getInventory().getHelmet().setTypeId(0);
				
				clanManager.resetFlag( ((Player) ent));
			}
			
			
			Player player = (Player) ent;
			
			Clan clan = plugin.getClanManager().getClanByPlayer(player);
			
			if ( clan != null ) {

				clan.addPlayerKill();
				
			}
			
			
	    }
		
	    
	    
		
	}

	
	
}
