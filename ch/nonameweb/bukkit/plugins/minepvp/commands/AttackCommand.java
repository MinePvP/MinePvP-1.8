package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.tasks.StartAttackTask;
import ch.nonameweb.bukkit.plugins.minepvp.tasks.StopAttackTask;

public class AttackCommand {

	MinePvP plugin;
	
	public AttackCommand() {
		
	}
	
	public void execute( Player player, String[] arg ) {
		
		this.plugin = MinePvP.getInstance();
		
		if ( plugin.getClanManager().hasPlayerAClan( player ) ) {
		
			Clan clan = null;
			Clan clan2 = null;
			
			clan = plugin.getClanManager().getClanByPlayer(player);
			
			player.sendMessage("Clan " + arg[0].toString() );
			
			clan2 = plugin.getClanManager().getClanByName( arg[0].toString() );
						
			
			if ( clan != null ) {
				
				if ( clan2 != null ) {
					
					if ( plugin.getClanManager().isMinPlayerOnline(clan2) ) {
						
						if ( clan2.getAttackedClan() == null ) {
							Integer time = plugin.getConfig().getInt("Global.Settings.Attack.Time");
							Integer delay = plugin.getConfig().getInt("Global.Settings.Attack.Delay");
							
							
							plugin.getClanManager().sendClanMessage(clan, "Ihr könnt das Königreich " + clan2.getName() + " in " + delay + " angreifen.");
							plugin.getClanManager().sendClanMessage(clan2, "Ihr könnt in " + delay + " vom Königreich " + clan.getName() + " angegriffen werden.");
							
							time = ( time * 60 ) * 20;
							delay = ( delay * 60 ) * 20;
							delay = delay + time;
							
							plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new StartAttackTask(plugin, clan.getName(), clan2.getName() ), 6000L); // ToDo now is a test delay
							plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new StopAttackTask(plugin, clan.getName(), clan2.getName() ), 42000L); // Test time
							
						} else {
							player.sendMessage(ChatColor.GOLD + "Dieses Königreich wird bereits von Königreich " + clan2.getAttackedClan().getName() + " angegriffen.");
						}						
						
					} else {
						player.sendMessage(ChatColor.GOLD + "Beim anzugreifenden Königreich sind zu wenige Spieler Online.");
					}
									
				} else {
					player.sendMessage(ChatColor.GOLD + "Dies ist kein gültiger Clan.");
				}
			
			}
			
		} else {
			player.sendMessage(ChatColor.GOLD + "Du bist in keinen Clan.");
		}
		
		
	}
	
	
}
