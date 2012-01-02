package ch.nonameweb.bukkit.plugins.minepvp.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
							// Flag von Clan wieder herstellen
							clanManager.resetFlag(player);
							
							// Punkte geben
							clanManager.addPoints(player);
							
							player.sendMessage(ChatColor.GREEN + "Du hast die Flagge erfolgreich abgegben.");
							
							// Das gegnerische Team Informieren das Ihre Flagge erfolgreich abgegebn wurde,
							clanManager.sendClanMessage(clanLand, ChatColor.GOLD + "Der Spieler " + player.getName() + " konnte erfolgreich die Flagge abgeben");
						} else {
							// Protection der Flagge fals Spieler keine Flagge hat.
							event.setCancelled(true);
						}
						
					} else {
						
						// TODO Protection von den anderen Blšcken rund um die Flagge
						if ( clanManager.isBlockAroundAFlag(block) ) {
							event.setCancelled(true);
						}
						
					}
					
				} else {
					
					// Wird auf die Flagge geschlagen?
					if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
						
						if ( clanManager.canClanAttackTheClan(player, clanLand) ) {
							
							// Flag Block Entfernen
							block.setTypeId(0);
							
							// Helm Item wird im Inventar verstaut
							if ( player.getInventory().getHelmet().getTypeId() > 0 ) {
								player.getInventory().addItem( player.getInventory().getHelmet() );
							}
							
							// Flagge wird als Helm gesetzt
							ItemStack itemStack = new ItemStack(35);
							player.getInventory().setHelmet( itemStack );
							
							clanManager.setPlayerHasFlag(player, clanLand);
							
							player.sendMessage(ChatColor.GREEN + "Du hast die Flagge von " + clanLand.getName() + " gestohlen.");
							
							// Das gegnerrische Team benachrichtigen das Ihre Flagge erbeutet wurde
							clanManager.sendClanMessage(clanLand, ChatColor.GOLD + "Die Flagge wurde von " + player.getName() + " erbeutet.");
						} else {
							player.sendMessage(ChatColor.GOLD + "Du kannst die Flagge nicht nehmen, da du dich ins Feindliche Kšnigreich geschummelt hast.");
						}
												
					}
					
					// Protecten von Chests und Furnance
					if ( block.getTypeId() == 54 || block.getTypeId() == 61 || block.getTypeId() == 62 ) {						
						event.setCancelled(true);
					}
					
					// Signs
					if ( block.getTypeId() == 63 || block.getTypeId() == 68 ) {
						event.setCancelled(true);
					}
					
					// Planzen
					if ( block.getTypeId() == 83 || block.getTypeId() == 59 ) {
						event.setCancelled(true);
					}
					
									
				}
				
			} else {
				
				
				if ( clanLand.getBaseLocation().equals( event.getBlock().getLocation() ) ) {
					// SPieler hat keinen Clan
					player.sendMessage(ChatColor.GOLD + "Du kannst die Flagge nicht nehmen, da du in keinem Clan bist.");
					event.setCancelled(true);
				} else {
				
					if ( clanManager.isBlockAroundAFlag(block) ) {
						event.setCancelled(true);
					}
					
					// Protecten von Chests und Furnance
					if ( block.getTypeId() == 54 || block.getTypeId() == 61 || block.getTypeId() == 62 ) {						
						event.setCancelled(true);
					}
					
				}
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
		Block block = event.getBlock();
		
		Clan clanLand = clanManager.getClanByLocation( event.getBlock().getLocation() );
		
		// Ist es in einen gebit von einem Clan?
		if ( clanLand != null ) {
		
			// Hat der Spieler einen Clan?
			if ( clanManager.hasPlayerAClan(player) ) {
				
				// Ist er im gleichen Clan wie das Land?
				if ( clanLand.getName().equalsIgnoreCase( clanManager.getClanNameByPlayer(player) ) ) {	
					
				} else {
					
					if ( block.getTypeId() == 46 ) {
						event.setCancelled(true);
					}
					
					clanManager.playerDamage(player);
				}
				
			} else {
				
				if ( block.getTypeId() == 46 ) {
					event.setCancelled(true);
				}
				
				clanManager.playerDamage(player);			
			}
			
		} else {
		
		}
		
	}
	
}
