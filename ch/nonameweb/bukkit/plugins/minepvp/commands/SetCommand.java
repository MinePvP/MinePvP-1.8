package ch.nonameweb.bukkit.plugins.minepvp.commands;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class SetCommand {

	MinePvP plugin;
	SimpleClans simpleClans;
	
	public SetCommand() {
		this.plugin = MinePvP.getInstance();
		this.simpleClans = this.plugin.getSimpleClans();
	}
	
	public void execute( Player player, String[] arg ) {
		
		ClanPlayer clanPlayer = simpleClans.getClanManager().getClanPlayer(player);
		
		if ( clanPlayer.isLeader() ) {
			
			if ( arg[0].equalsIgnoreCase("clanspawn") ) {
				
				if ( plugin.getClanManager().getClanByPlayer(player).getClanSpawn() == true ) {
					if ( plugin.getClanManager().setClanSpawn(player) ) {
						player.sendMessage(ChatColor.GREEN + "ClanSpawn wurde versetzt.");
					} else {
						player.sendMessage(ChatColor.RED + "ClanSpawn wurde nicht versetzt.");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Ihr besitzst noch keinen ClanSpawn.");
				}
				
			} else if ( arg[0].equalsIgnoreCase("flag") ) {
				
				if ( plugin.getClanManager().moveClanFlag(player) ) {					
					player.sendMessage(ChatColor.GREEN + "Flagge wurde versetzt.");
				} else {
					player.sendMessage(ChatColor.RED + "Flagge konnte nicht versetzt werden.");
				}	
				
			} else {
				player.sendMessage(ChatColor.RED + "Benutzung : /minepvp set clanspawn|flag");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "Du bist nicht Leader.");
		}
		
	}
	
	
}
