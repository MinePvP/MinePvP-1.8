package ch.nonameweb.bukkit.plugins.minepvp.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.manager.ClanManager;

public class MinePvPEntityListener {

	private MinePvP plugin;
	private ClanManager clanManager;
	
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
				plugin.getClanManager().resetFlag( ((Player) ent));
			}
	    }
		
		
	}

	
	
}
