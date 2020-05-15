package club.hardcoreminecraft.javase.compasshome;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class playerJoinSetCompass implements Listener {
	
	@EventHandler
	public void playerjoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		//When they join, check if they need their compass location set
		if(methods.isWorldEnabled(player.getWorld().getName()) && player.hasPermission("compasshome.target")) {

			
			if(player.getBedSpawnLocation() != null) {
			player.setCompassTarget(player.getBedSpawnLocation());
			main.plugin.getLogger().info(ChatColor.GOLD +"[Compass Homes] Setting " +player.getName() 
			+"'s compass location to their bed location.");
			}
		}
		
	}
	
	@EventHandler
	public void playerChangeWorld(PlayerChangedWorldEvent event) {
		
		Player player = event.getPlayer();
		
		//Check if the world they changed to is one of the enabled worlds
		if(methods.isWorldEnabled(player.getWorld().getName())) {
			if(player.hasPermission("compasshome.target")) {
		
				if(player.getBedSpawnLocation() != null) {
				
			main.plugin.getLogger().info(ChatColor.GOLD +"[Compass Homes] Setting " +player.getName() 
			+"'s compass location to their bed location.");
			
			player.setCompassTarget(player.getBedSpawnLocation());
				}
		} //If player does not have permission, then we can ignore them. NOTE** DO NOT GIVE PER WORLD PERMISSIONS OR THIS WILL BORK
		//if it is not enabled, change there compass location to spawn
	} else {
		player.setCompassTarget(player.getWorld().getSpawnLocation());
		main.plugin.getLogger().info(ChatColor.GOLD +"[Compass Homes] Setting " +player.getName() 
		+"'s compass location to the world spawn.");
	}
	}
	


}
