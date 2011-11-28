package ch.nonameweb.bukkit.plugins.minepvp.listener;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.manager.ClanManager;

public class MinePvPBlockListener extends BlockListener{

	private MinePvP plugin;
	private ClanManager clanManager;
	private SimpleClans simpleClans;
	
	/**
	 * 
	 */
	public MinePvPBlockListener() {
		plugin = MinePvP.getInstance();
		clanManager = plugin.getClanManager();
		simpleClans = plugin.getSimpleClans();
		
	}	
	
	/**
	 * 
	 */
	public void onBlockDamage( BlockDamageEvent event) {
		
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Clan clanLand = clanManager.getClanByLocation( event.getBlock().getLocation() );
		
		if ( simpleClans.getClanManager().getAnyClanPlayer( player.getName() ).getClan() != null ) {
			
			if ( clanLand != null ) {
				
				if ( clanLand.getName().equalsIgnoreCase( simpleClans.getClanManager().getClanByPlayerName( player.getName() ).getName() ) ) {
					
					if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
						
						if ( clanManager.hasPlayerAFlag(player) ) {
							
							// Flag wegnehmen
							player.getInventory().setHelmet(null);
							
							// Flag von Clan wieder herstellen
							clanManager.resetFlag(player);
							
							// Punkte geben
							clanManager.addPoints(player);
						}
						
					}
					
				} else {
					
					if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
						
						// Flag Block Entfernen
						block.setTypeId(0);
						
						ItemStack itemStack = new ItemStack(35);
						
						player.getInventory().setHelmet( itemStack );
						
						clanManager.setPlayerHasFlag(player, clanLand);
						
					} else {
						player.damage( plugin.getConfig().getInt("Global.Settings.Land.DMGonBlockDMG") );
						player.setFoodLevel( ( player.getFoodLevel() - plugin.getConfig().getInt("Global.Settings.Land.DMGonBlockDMG") ) );
					}
					
				}
				
			}
			
		} else {
			if ( clanLand != null ) {
				player.damage( plugin.getConfig().getInt("Global.Settings.Land.DMGonBlockDMG") );
				player.setFoodLevel( ( player.getFoodLevel() - plugin.getConfig().getInt("Global.Settings.Land.DMGonBlockDMG") ) );
			}
			
		}
		
	}

	
}
