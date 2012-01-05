package ch.nonameweb.bukkit.plugins.minepvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ch.nonameweb.bukkit.plugins.minepvp.Clan;
import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;
import ch.nonameweb.bukkit.plugins.minepvp.tasks.ResetAttackTask;
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
			
			clan2 = plugin.getClanManager().getClanByName( arg[0].toString() );
						
			
			if ( clan != null ) {
				
				if ( clan2 != null ) {
					
					if ( plugin.getClanManager().isMinPlayerOnline(clan2) ) {
						
						if ( clan2.getAttackedClan() == null ) {
							
							if ( clan2.getAttacked() == false ) {
								
								long time = plugin.getSettingsManager().getAttackTime();
								long delay = plugin.getSettingsManager().getAttackDelay();
								long reset = plugin.getSettingsManager().getAttackResetTime();
								
								plugin.getClanManager().sendClanMessage(clan, ChatColor.GREEN + "Ihr könnt das Königreich " + clan2.getName() + " in " + delay + "min angreifen.");
								plugin.getClanManager().sendClanMessage(clan2, ChatColor.RED + "Ihr könnt in " + delay + "min vom Königreich " + clan.getName() + " angegriffen werden.");
								
								clan.addAttack();
								clan2.addAttacked();
								
								time = ( time * 60 ) * 20L;
								delay = ( delay * 60 ) * 20L;
								time = time + delay;
								reset = reset + time;
								
								clan2.setAttackedClan(clan);
								
								
								plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new StartAttackTask(plugin, clan.getName(), clan2.getName() ), delay);
								plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new StopAttackTask(plugin, clan.getName(), clan2.getName() ), time);
								plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new ResetAttackTask(plugin, clan.getName(), clan2.getName() ), reset);
								
							} else {
								player.sendMessage(ChatColor.GOLD + "Dieses Königreich kann zurzeit nicht angegriffen werden da es erst vor kurzem von einem anderem Königreich angegriffen wurde.");
							}							
							
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
