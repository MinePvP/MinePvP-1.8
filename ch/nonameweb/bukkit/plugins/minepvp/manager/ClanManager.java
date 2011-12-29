package ch.nonameweb.bukkit.plugins.minepvp.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
	
import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class ClanManager {
	
	MinePvP plugin;
	SettingsManager settingsManager;
	SimpleClans simpleClans;
	
	ArrayList<Clan> clans = new ArrayList<Clan>();;
	
	/**
	 * 
	 */
	public ClanManager() {
		plugin = MinePvP.getInstance();
		settingsManager = plugin.getSettingsManager();
		simpleClans = plugin.getSimpleClans();
	}
	
	/**
	 * 
	 * @param clanName
	 * @param player
	 * @return
	 */
	public Boolean createClan( String clanName, Player player ) {
		
		// Giebt es den Clan noch nicht?
		if ( getClanByName(clanName) == null ) {
			
			// Min Spieler?
			if ( getMinPlayerForBuyLand( player ) ) {
				
				if ( checkClanBaseDistance( player ) ) {
					
					// Gold 
					if ( getPlayerGoldForBuyLand( player ) ) {
						
						Clan clan = new Clan();
						clan.create(clanName, player.getLocation() );
						
						clans.add(clan);
						
						setClanFlag(player);
						
						clan.save(plugin.getConfig());
						loadClans();
						
						return true;
					}
				}
					
			} else {
				player.sendMessage(ChatColor.RED + "Ihr müsst mindestens " + settingsManager.getErsteStufeMinPlayer() + " Spieler sein um Land zu kaufen!");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "Um das Land zu erweitern /minepvp upgrade land");
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public Boolean upgradeClan( Player player ) {
		
		// Clan holen wenn er schon land besitzt
		Clan clan = getClanByPlayer(player);
		
		if (  clan != null ) {
			
			if ( getClanPointsForUpgrade(clan, player) ) {
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	public void loadClans() {
		
		/*
		 * Falls Clans schon befüllt ist wird diese 
		 * zuerst gespeichert und dann neu erstellt
		 */
		if ( clans != null ) {
			saveClans();	
		}
		
		Set<String> clansList = null;
		
		try {
			clansList = plugin.getConfig().getConfigurationSection("Clans").getKeys(false);
		} catch (Exception e) {
			plugin.log("ClanList ");
		}
		
		
		if ( clansList != null ) {

			clans = new ArrayList<Clan>();
			
			// Alle Clans einzeln laden
			for ( String clanName : clansList ) {
				
				Clan clan = new Clan();
				clan.load(clanName, plugin.getConfig());
				
				if ( clan.getStufe() == 0 ) {
					clan.setRadius( settingsManager.getErsteStufeRadius() );
				} else {
					clan.setRadius( settingsManager.getStufenRadius().get( (clan.getStufe() - 1) ) );
				}
				
				plugin.log("Load Clan : " + clan.getName() );
				
				clans.add(clan);
			}
			
		}
		
		saveClans();
		
	}
	
	public void reloadClans() {
		
		
		Set<String> clansList = null;
		
		try {
			clansList = plugin.getConfig().getConfigurationSection("Clans").getKeys(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if ( clansList != null ) {
			
			/*
			 * Falls Clans schon befüllt ist wird diese 
			 * zuerst gespeichert und dann neu erstellt
			 */
			if ( clans != null ) {
				saveClans();
				plugin.reloadConfig();
				
				for ( Clan clan : clans ) {
					
					clan.load(clan.getName(), plugin.getConfig());
					
					if ( clan.getStufe() == 0 ) {
						clan.setRadius( settingsManager.getErsteStufeRadius() );
					} else {
						clan.setRadius( settingsManager.getStufenRadius().get( (clan.getStufe() - 1) ) );
					}
					
					plugin.log("Load Clan : " + clan.getName() );
					
				}
			}
						
		}
		
		saveClans();
		
		
	}
	
	/**
	 * 
	 */
	public void saveClans() {
		
		ArrayList<String> clanNameList = new ArrayList<String>();
		
		if ( clans != null ) {
			
			for ( Clan clan : clans ) {
				clan.save( plugin.getConfig() );
				clanNameList.add(clan.getName());
			}
			
		}
		
		plugin.saveConfig();
	}
	
	
	/**
	 * 
	 * @return
	 */
	private Boolean checkClanBaseDistance( Player player ) {
		
		// Distanz zum Spawn
		if ( plugin.getServer().getWorld("world").getSpawnLocation().distance( player.getLocation() ) > settingsManager.getMinAbstand() ) {
		
			if ( clans.size() >= 1 ) {
				
				for ( Clan clan : clans ) {
					
					Location clanLocation = new Location( plugin.getServer().getWorld("world"), clan.getBaseX(), clan.getBaseY(), clan.getBaseZ());
										
					// Die Distanz zur Base muss einen mindest abstand zur gegner base haben
					// TODO Checken ob das wirklich richtig berechnet wird!!!
					if ( clanLocation.distance( player.getLocation() ) > settingsManager.getMinAbstand() ) {
						return true;
					} else {
						player.sendMessage(ChatColor.GOLD + "Du bist zu nahe am Clan " + clan.getName() + ".");
					}
					
				}
			} else {
				return true;
			}
			
		} else {
			player.sendMessage(ChatColor.GOLD + "Du bist zu nahe am Spawn.");
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public Boolean getMinPlayerForBuyLand( Player player ) {
		
		Integer clanPlayers = plugin.getSimpleClans().getClanManager().getClanByPlayerName(player.getName()).getSize();
		
		if ( settingsManager.getErsteStufeMinPlayer() <= clanPlayers ) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	private Boolean getPlayerGoldForBuyLand( Player player ) {
		
		Integer kosten = settingsManager.getErsteStufeKosten();
		Integer inHnad = player.getInventory().getItemInHand().getAmount();
		
		
		// Item überprüfen das in der Hand ist
		if ( player.getInventory().getItemInHand().getTypeId() == 266 ) {

			// Item menge überprüfen
			if ( inHnad >= kosten ) {
				
				// Gold abziehen
				Integer rest =  inHnad - kosten;
				
				if ( rest == 0 ) {
					player.getInventory().remove( player.getItemInHand() );
				} else {
					player.getInventory().getItemInHand().setAmount( rest );
				}
				
				return true;
				
			} else {
				player.sendMessage( ChatColor.GOLD + "Du brauchst " + kosten + " Goldbarren.");
			}
			
		} else {
			player.sendMessage( ChatColor.GOLD + "Um Land zu kaufen nimm bitte " + kosten + " Goldbarren in die Hand.");
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param clan
	 * @param player
	 * @return
	 */
	private Boolean getClanPointsForUpgrade ( Clan clan, Player player ) {
		
		Integer kosten = settingsManager.getStufenKosten().get( clan.getStufe() );
		Integer points = clan.getPoints();
		
		Integer rest = points - kosten;
		
		if ( rest >= 0 ) {
						
			clan.setPoints( rest );
			clan.setStufe( clan.getStufe() + 1 );
			
			clan.setRadius( settingsManager.getStufenRadius().get( (clan.getStufe() - 1) ) );
			
			saveClans();
			
			return true;
		} else {
			player.sendMessage(ChatColor.GOLD + "Ihr braucht " + kosten + " Punkte um upzugraden.");
		}
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getKostenList() {
		
		ArrayList<Integer> kostenList = new ArrayList<Integer>();
		
		@SuppressWarnings("unchecked")
		List<String> list = plugin.getConfig().getList("Global.Settings.Land.Stufen.Kosten");
		
		for ( String string : list ) {
			
			kostenList.add( Integer.parseInt(string) );
			
		}
		
		
		return kostenList;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getRadiusList() {
		
		ArrayList<Integer> radiusList	= new ArrayList<Integer>();
		
		@SuppressWarnings("unchecked")
		List<String> list = plugin.getConfig().getList("Global.Settings.Land.Stufen.Radius");
		
		for ( String string : list ) {
			radiusList.add( Integer.parseInt( string ) );
		}
		
		return radiusList;
	}
	
	/**
	 * 
	 * @param clanName
	 * @return
	 */
	public Clan getClanByName( String clanName ) {
		
		if ( clans != null ) {
			for ( Clan clan : clans ) {				
				if ( clan.getName().equalsIgnoreCase(clanName) ) {
					return clan;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public Clan getClanByPlayer( Player player ) {
		
		String clanName = null;
		
		if ( plugin.getSimpleClans().getClanManager().getClanByPlayerName( player.getName()) != null ) {
			clanName = plugin.getSimpleClans().getClanManager().getClanByPlayerName(player.getName()).getName();
			
			
			for ( Clan clan : clans ) {
				
				if ( clan.getName().equalsIgnoreCase(clanName) ) {				
					return clan;
				}
			}
		}
		
		
		
		return null;
		
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	private Boolean setClanFlag( Player player ) {
		
		Integer x = player.getLocation().getBlockX();
		Integer y = player.getLocation().getBlockY();
		Integer z = player.getLocation().getBlockZ();
		
		// Boden
		plugin.getServer().getWorld("world").getBlockAt(x, y -1, z).setTypeId(89);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y -1, z).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y -1, z -1).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y -1, z +1).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x +1, y -1, z +1).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x +1, y -1, z -1).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x, y -1, z -1).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x, y -1, z +1).setTypeId(49);
		plugin.getServer().getWorld("world").getBlockAt(x +1, y -1, z).setTypeId(49);
		
		// Halterrung
		plugin.getServer().getWorld("world").getBlockAt(x -1, y, z).setTypeId(85);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y + 1, z).setTypeId(85);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y + 2, z).setTypeId(85);
		plugin.getServer().getWorld("world").getBlockAt(x, y + 2, z).setTypeId(85);
		
		// Flagge
		plugin.getServer().getWorld("world").getBlockAt(x, y + 1, z).setTypeId(35);
		
		getClanByPlayer(player).setBaseLocation( x, y + 1, z );
		
		return true;
	}
	
	
	public Boolean moveClanFlag( Player player ) {
		
		removeClanFlag(player);
		setClanFlag(player);
		
		return true;
	}
	
	public Boolean removeClanFlag( Player player ) {
		
		Integer x = getClanByPlayer(player).getBaseX();
		Integer y = getClanByPlayer(player).getBaseY() - 1;
		Integer z = getClanByPlayer(player).getBaseZ();
		
		// Boden
		plugin.getServer().getWorld("world").getBlockAt(x, y -1, z).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y -1, z).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y -1, z -1).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y -1, z +1).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x +1, y -1, z +1).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x +1, y -1, z -1).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x, y -1, z -1).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x, y -1, z +1).setTypeId(1);
		plugin.getServer().getWorld("world").getBlockAt(x +1, y -1, z).setTypeId(1);
		
		// Halterrung
		plugin.getServer().getWorld("world").getBlockAt(x -1, y, z).setTypeId(0);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y + 1, z).setTypeId(0);
		plugin.getServer().getWorld("world").getBlockAt(x -1, y + 2, z).setTypeId(0);
		plugin.getServer().getWorld("world").getBlockAt(x, y + 2, z).setTypeId(0);
		
		// Flagge
		plugin.getServer().getWorld("world").getBlockAt(x, y + 1, z).setTypeId(0);
		
		return true;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public Clan getClanByLocation( Location location ) {
		
		Integer x = location.getBlockX();
		Integer y = location.getBlockY();
		Integer z = location.getBlockZ();
		
		if ( clans != null ) {
			for ( Clan clan : clans ) {
				
				// X
				Integer clanMinusX = clan.getBaseX() - clan.getRadius();
				Integer clanPlusX = clan.getBaseX() + clan.getRadius();
				
				// Y
				Integer clanMinusY = clan.getBaseY() - clan.getRadius();
				Integer clanPlusY = clan.getBaseY() + clan.getRadius();
				
				// Z
				Integer clanMinusZ = clan.getBaseZ() - clan.getRadius();
				Integer clanPlusZ = clan.getBaseZ() + clan.getRadius();

				// Ist zwischen X
				if ( clanPlusX > x && clanMinusX < x ) {
					
					//if ( clanPlusY > y && clanMinusY < y ) {

						if ( clanPlusZ > z && clanMinusZ < z ) {
							
							return clan;
							
						}
						
					//}
					
				}
				
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param player
	 * @param clan
	 */
	public void setPlayerHasFlag( Player player, Clan clan ) {
		
		for ( Clan clan2 : clans ) {
			
			if ( clan2.equals( clan ) ) {
				clan.setPlayerHasFlag(player);
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public Boolean hasPlayerAFlag( Player player ) {
		
		for ( Clan clan : clans ) {
						
			if ( clan.getPlayerHasFlag() != null ) {
				if ( player.getName().equalsIgnoreCase( clan.getPlayerHasFlag().getName() ) ) {
					if ( clan.getPlayerHasFlag() != null ) {
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param player
	 */
	public void addPoints( Player player ) {
		
		for ( Clan clan : clans ) {
			
			if ( clan.getName().equalsIgnoreCase( plugin.getClanManager().getClanByPlayer(player).getName() ) ) {
				clan.addPoints( settingsManager.getCtfPoints() );
				clan.save(plugin.getConfig());
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param player
	 */
	public void resetFlag( Player player ) {
		
		for ( Clan clan : clans ) {
			
			if ( clan.getPlayerHasFlag() != null ) {
				
				if ( player.getName().equalsIgnoreCase( clan.getPlayerHasFlag().getName() ) ) {
					
					player.getInventory().setHelmet( null );
					clan.resetPlayerHasFlag();
					plugin.getServer().getWorld("world").getBlockAt( clan.getBaseLocation() ).setTypeId(35);
					
				}
				
			}
			
		}
		
	}
	
	public Boolean isMinPlayerOnline( Clan clan ) {
		
		Integer minPlayers = settingsManager.getCtfMinPlayerOnline();
		Integer onlinePlayers = 0;
		
		Player[] players = plugin.getServer().getOnlinePlayers();
		
		for ( Player player : players ) {
			
			if ( simpleClans.getClanManager().getClanByPlayerName( player.getName() ) != null ) {
			
				if ( simpleClans.getClanManager().getClanByPlayerName( player.getName() ).getName().equalsIgnoreCase( clan.getName() ) ) {
					
					onlinePlayers++;
					
					if ( minPlayers <= onlinePlayers ) {
						return true;
					}
					
				}
				
			}
			
		}
		
		if ( minPlayers <= onlinePlayers ) {
			return true;
		}
		
		return false;
	}
	
	public void sendClanMessage( Clan clan, String message) {
		
		List<ClanPlayer> clanPlayers = simpleClans.getClanManager().getAllClanPlayers();
		
		for ( ClanPlayer player : clanPlayers ) {
			
			if ( player.getClan() != null ) {
				if ( player.getClan().getName().equalsIgnoreCase( clan.getName() ) ) {
					
					Player player2 = plugin.getServer().getPlayer( player.getName() );
					
					if ( player2 != null && player2.isOnline() ) {
						player2.sendMessage(message);
					}
				}
			}
			
		}
		
	}
	
	public void playerDamage( Player player ) {
		player.damage( settingsManager.getDamageOnBlockBracke() );
		player.setFoodLevel( ( player.getFoodLevel() - settingsManager.getDamageOnBlockBracke() ) );	
	}
	
	public void playerMoatDamage( Player player ) {
		player.setFoodLevel( ( player.getFoodLevel() - settingsManager.getMoatDamage() ) );	
	}
	
	public Boolean hasPlayerAClan( Player player ) {
		
		
		if ( simpleClans.getClanManager().getAnyClanPlayer(player.getName()) != null ) {
			if ( simpleClans.getClanManager().getAnyClanPlayer( player.getName() ).getClan() != null ) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getClanNameByPlayer( Player player ) {
		
		String name = null;
		
		name = simpleClans.getClanManager().getClanByPlayerName( player.getName() ).getName();
		
		if ( name != null ) {
			return name;
		}
		
		return null;
	}
	
	public Boolean isBlockAroundAFlag( Block block ) {
		
		Clan clan = getClanByLocation( block.getLocation() );
		Block flagBlock = clan.getBaseLocation().getBlock();
		
		/*
		if ( clan.getBaseLocation().distance(flagBlock.getLocation()) <= 2 ) {
			return true;
		}
		*/
		
		// Boden
		if ( block.getX() == flagBlock.getX() && block.getY() == flagBlock.getY() && block.getZ() == flagBlock.getZ() ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() -1 ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() -1 ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() -1 ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() -1 ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() +1 ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() +1 ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() +1 ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() +1 ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() +1 ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() ) && block.getY() == ( flagBlock.getY()  -2 ) && block.getZ() == ( flagBlock.getZ() -1 ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() +1 ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() +1 ) && block.getY() == ( flagBlock.getY() -2 ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} // Halterrung 
		else if ( block.getX() == ( flagBlock.getX() -1 ) && block.getY() == ( flagBlock.getY() -1) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() -1 ) && block.getY() == ( flagBlock.getY() -1 ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() -1 ) && block.getY() == ( flagBlock.getY() +1 ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} else if ( block.getX() == ( flagBlock.getX() ) && block.getY() == ( flagBlock.getY() +1 ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		} // Flagge
		else if ( block.getX() == ( flagBlock.getX() ) && block.getY() == ( flagBlock.getY() ) && block.getZ() == ( flagBlock.getZ() ) ) {
			return true;
		}
		
		
		return false;
	}
	
	
	
	public Boolean buyUpgradeAlertSystem( Player player ) {
		
		Clan clan = getClanByPlayer(player);
		
		if ( clan != null ) {
			
			if ( clan.getAlertsystem() != 3 ) {
				if ( getClanPointsForUpgradeAlertSystem(clan, player) ) {
					clan.save(plugin.getConfig());
					return true;
				}
			} else {
				player.sendMessage(ChatColor.GOLD + "Alarmanlage ist schon voll ausgebaut.");
			}
		
		}		
		return false;
	}
	
	private Boolean getClanPointsForUpgradeAlertSystem( Clan clan, Player player ) {
		
		Integer kosten = settingsManager.getKostenAlertsystem().get( clan.getAlertsystem() );
		Integer points = clan.getPoints();
		
		Integer rest = points - kosten;
		
		if ( rest >= 0 ) {
			
			clan.setPoints( rest );
			clan.setAlertsystem( clan.getAlertsystem() + 1 );
			
			clan.save(plugin.getConfig());
			
			return true;
		} else {
			if ( clan.getAlertsystem() > 0 ) {
				player.sendMessage(ChatColor.GOLD + "Ihr braucht " + kosten + " Punkte um upzugraden.");
			} else {
				player.sendMessage(ChatColor.GOLD + "Ihr braucht " + kosten + " Punkte um zu kaufen.");
			}
			
		}
		
		return false;
	}
	
	public ArrayList<Integer> getKostenListAlertSystem() {
		
		ArrayList<Integer> kostenList = new ArrayList<Integer>();
		
		@SuppressWarnings("unchecked")
		List<String> list = plugin.getConfig().getList("Global.Settings.Land.AlertSystem.Kosten");
		
		for ( String string : list ) {
			
			kostenList.add( Integer.parseInt(string) );
			
		}
		
		
		return kostenList;
	}
	
	public Boolean buyUpgradeClanSpawn( Player player ) {
		
		Clan clan = getClanByPlayer(player);
		Integer kosten = settingsManager.getClanSpawnKosten();
		
		if ( clan != null ) {
			
			if ( clan.getClanSpawn() == false ) {
			
				Integer points = clan.getPoints();
				Integer rest = points - kosten;
				
				if ( rest >= 0 ) {
					clan.setClanSpawn(true);
					clan.setPoints( rest );
					clan.save(plugin.getConfig());
					
					return true;
				} else {
					player.sendMessage(ChatColor.GOLD + "Ihr braucht " + kosten + " Punkte um den ClanSpawn zu kaufen.");
				}
				
			} else {
				player.sendMessage(ChatColor.GOLD + "ClanSpawn schon vorhanden.");
			}
			
		}
		
		return false;
	}

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}
	
	public Boolean buyUpgradeMoat( Player player ) {
		
		Clan clan = getClanByPlayer(player);
		Integer kosten = settingsManager.getMoatKosten();
		
		if ( clan != null ) {
			
			if ( clan.getMoat() == false ) {
				Integer points = clan.getPoints();
				Integer rest = points - kosten;
				
				if ( rest >= 0 ) {
					clan.setMoat(true);
					clan.setPoints( rest );
					clan.save(plugin.getConfig());
					
					return true;
				} else {
					player.sendMessage(ChatColor.GOLD + "Ihr braucht " + kosten + " Punkte um den Wassergraben zu kaufen.");
				}
				
			} else {
				player.sendMessage(ChatColor.GOLD + "Wassergraben schon vorhanden.");
			}
			
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void sortClans() {
		
		 Collections.sort(clans, new Comparator(){
			 
	            public int compare(Object o1, Object o2) {
	                Clan clan1 = (Clan) o1;
	                Clan clan2 = (Clan) o2;
	               return clan1.getFlags().compareTo( clan2.getFlags());
	            }
	 
	        });
	 
		
	}
	
	public Boolean setClanSpawn( Player player ) {
		
		Clan clan = getClanByPlayer(player);
		
		clan.setClanSpawnX( player.getLocation().getBlockX() );
		clan.setClanSpawnY( player.getLocation().getBlockY() );
		clan.setClanSpawnZ( player.getLocation().getBlockZ() );
		
		return true;
	}
	
	
	public Boolean canClanAttackTheClan( Clan clan, Clan clan2 ) {
		
		if ( clan2.getAttackedClan() != null ) {
			
			if ( clan2.getAttackedClan().getName().equalsIgnoreCase( clan.getName() ) ) {
				
				if ( clan2.getAttack() ) {
					return true;
				}
				
			}
			
		}
				
		
		return false;
	}
	
	public Boolean isClanTheAttackedClan( Clan clan, Clan clan2 ) {
		
		if ( clan2.getAttackedClan() != null ) {
			
			if ( clan2.getAttackedClan().getName().equalsIgnoreCase( clan.getName() ) ) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
}
