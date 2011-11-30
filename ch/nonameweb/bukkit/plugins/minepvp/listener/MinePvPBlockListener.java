package ch.nonameweb.bukkit.plugins.minepvp.listener;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
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
		
		// Ist es in einen gebit von einem Clan?
		if ( clanLand != null ) {
		
			// Hat der Spieler einen Clan?
			if ( clanManager.hasPlayerAClan(player) ) {
				
				// Ist er im gleichen Clan wie das Land?
				if ( clanLand.getName().equalsIgnoreCase( clanManager.getClanNameByPlayer(player) ) ) {
					
					// Wird auf den Flaggen Block gehauen?
					if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
						
						// hat der Spieler eine Flagge?
						if ( clanManager.hasPlayerAFlag(player) ) {
							
							// Flag wegnehmen TODO Helm zurücksetzen
							player.getInventory().setHelmet(null);
							
							// Flag von Clan wieder herstellen
							clanManager.resetFlag(player);
							
							// Punkte geben
							clanManager.addPoints(player);
							
							player.sendMessage("Du hast die Flagge erfolgreich abgegben!");
							
							// Das gegnerische Team Informieren das Ihre Flagge erfolgreich abgegebn wurde,
							clanManager.sendClanMessage(clanLand, "Der Spieler " + player.getName() + " konnte erfolgreich erure Flagge zur Clan Base bringen!");
						} else {
							// TODO Der Spieler hat keien Flagge Protection von der Flage
						}
						
					} else {
						
						// TODO Protection von den anderen Blöcken rund um die Flagge
						
					}
					
				} else {
					
					// Wird auf die Flagge geschlagen?
					if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
						
						// überprüfen ob die mindest anzahl spieler des gegner teams online sind
						if ( clanManager.isMinPlayerOnline( clanLand ) ) {
							// Flag Block Entfernen
							block.setTypeId(0);
							
							// Helm Item wird im Inventar verstaut
							player.getInventory().addItem( player.getInventory().getHelmet() );
							
							// Flagge wird als Helm gesetzt
							ItemStack itemStack = new ItemStack(35);
							player.getInventory().setHelmet( itemStack );
							
							clanManager.setPlayerHasFlag(player, clanLand);
							
							player.sendMessage("Du hast die Flagge von " + clanLand.getName() + " gestohlen!");
							
							// Das gegnerrische Team benachrichtigen das Ihre Flagge erbeutet wurde
							clanManager.sendClanMessage(clanLand, "Die Flagge wurde von " + player.getName() + " erbeutet!");
						} else {
							
							player.sendMessage("Es sind zu wenige Spieler aus dem gegnerischem Team online!");
						}
						
					} else {
						
					}
									
				}
				
			} else {
				
			}
			
		} else {
			
		}
		
	}
	
	public void onBlockBreak( BlockBreakEvent event ) {
		
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Clan clanLand = clanManager.getClanByLocation( event.getBlock().getLocation() );
		
		
		// Ist es in einen gebit von einem Clan?
		if ( clanLand != null ) {
		
			// Hat der Spieler einen Clan?
			if ( clanManager.hasPlayerAClan(player) ) {
				
				// Ist er im gleichen Clan wie das Land?
				if ( clanLand.getName().equalsIgnoreCase( clanManager.getClanNameByPlayer(player) ) ) {
					
					
					
				} else {
					clanManager.playerDamage(player);
				}
				
			} else {
				clanManager.playerDamage(player);			
			}
			
		} else {
			
		}
		
	}
	
	
	public void onBlockPlace( BlockPlaceEvent event ) {
		
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Clan clanLand = clanManager.getClanByLocation( event.getBlock().getLocation() );
		
		// Ist es in einen gebit von einem Clan?
		if ( clanLand != null ) {
		
			// Hat der Spieler einen Clan?
			if ( clanManager.hasPlayerAClan(player) ) {
				
				// Ist er im gleichen Clan wie das Land?
				if ( clanLand.getName().equalsIgnoreCase( clanManager.getClanNameByPlayer(player) ) ) {
					
					
					
				} else {
					clanManager.playerDamage(player);
				}
				
			} else {
				clanManager.playerDamage(player);			
			}
			
		} else {
			
		}
		
		
		
	}


	
}
