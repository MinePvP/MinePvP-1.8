package ch.nonameweb.bukkit.plugins.minepvp.tasks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class StopAttackTask implements Runnable {
	
	private MinePvP plugin;
	private String clanString;
	private String clan2String;

	public StopAttackTask( MinePvP plugin, String clanString, String clan2String ) {
		this.plugin = plugin;
		this.clanString = clanString;
		this.clan2String = clan2String;
	}
	
	@Override
	public void run() {
		
		Clan clan = plugin.getClanManager().getClanByName(clanString);
		Clan clan2 = plugin.getClanManager().getClanByName(clan2String);
		
		plugin.getClanManager().getClanByName( clan2String ).setAttackedClan( null );
		plugin.getClanManager().getClanByName( clan2String ).setAttack(false);
		
		plugin.getClanManager().sendClanMessage(clan, ChatColor.RED + "Der Angriff ist vorbei.");
		plugin.getClanManager().sendClanMessage(clan2, ChatColor.GREEN + "Der Angriff ist vorbei.");
		
		
	}

}
