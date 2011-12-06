package ch.nonameweb.bukkit.plugins.minepvp.listener;

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
	
	/**
	 * 
	 */
	public MinePvPBlockListener() {
		plugin = MinePvP.getInstance();
		clanManager = plugin.getClanManager();		
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
							
							//player.getInventory().getHelmet().setTypeId(0);
							
							// Flag von Clan wieder herstellen
							clanManager.resetFlag(player);
							
							// Punkte geben
							clanManager.addPoints(player);
							
							player.sendMessage("Du hast die Flagge erfolgreich abgegben!");
							
							// Das gegnerische Team Informieren das Ihre Flagge erfolgreich abgegebn wurde,
							clanManager.sendClanMessage(clanLand, "Der Spieler " + player.getName() + " konnte erfolgreich erure Flagge zur Clan Base bringen!");
						} else {
							// Protection der Flagge fals Spieler keine Flagge hat.
							event.setCancelled(true);
						}
						
					} else {
						
						// TODO Protection von den anderen Blöcken rund um die Flagge
						
						if ( clanManager.isBlockAroundAFlag(block) ) {
							event.setCancelled(true);
						}
						
					}
					
				} else {
					
					// Wird auf die Flagge geschlagen?
					if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
						
						// überprüfen ob die mindest anzahl spieler des gegner teams online sind
						if ( clanManager.isMinPlayerOnline( clanLand ) ) {
							// Flag Block Entfernen
							block.setTypeId(0);
							
							// Helm Item wird im Inventar verstaut
							if ( player.getInventory().getHelmet() != null ) {
								player.getInventory().addItem( player.getInventory().getHelmet() );
							}

							// Flagge wird als Helm gesetzt
							ItemStack itemStack = new ItemStack(35);
							player.getInventory().setHelmet( itemStack );
							
							clanManager.setPlayerHasFlag(player, clanLand);
							
							player.sendMessage("Du hast die Flagge von " + clanLand.getName() + " gestohlen!");
							
							// Das gegnerrische Team benachrichtigen das Ihre Flagge erbeutet wurde
							clanManager.sendClanMessage(clanLand, "Die Flagge wurde von " + player.getName() + " erbeutet!");
						} else {
							
							player.sendMessage("Es sind zu wenige Spieler aus dem gegnerischem Team online!");
							
							event.setCancelled(true);
							
						}
						
					} else {
						
					}
					
					// Protecten von Chests und Furnance
					if ( block.getTypeId() == 54 || block.getTypeId() == 61 || block.getTypeId() == 62 ) {						
						event.setCancelled(true);
					}
									
				}
				
			} else {
				
			}
			
		} else {
						
		}
		
	}
	
	public void onBlockBreak( BlockBreakEvent event ) {
		
		Player player = event.getPlayer();
		
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
