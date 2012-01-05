package ch.nonameweb.bukkit.plugins.minepvp.tasks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class ResetAttackTask implements Runnable {
	
	private MinePvP plugin;
	private String clanString;
	private String clan2String;

	public ResetAttackTask( MinePvP plugin, String clanString, String clan2String ) {
		this.plugin = plugin;
		this.clanString = clanString;
		this.clan2String = clan2String;
	}
	
	@Override
	public void run() {
		
		Clan clan = plugin.getClanManager().getClanByName(clanString);
		Clan clan2 = plugin.getClanManager().getClanByName(clan2String);
		
		plugin.getClanManager().getClanByName( clan2String ).setAttacked(false);
		
		plugin.getClanManager().sendClanMessage(clan, ChatColor.RED + "Das Kšnigreich " + clan2.getName() + " kann wieder angegriffen werden.");
		plugin.getClanManager().sendClanMessage(clan2, ChatColor.GREEN + "Euer Kšnigreich kann wieder angegriffen werden.");
		
		
	}

}
