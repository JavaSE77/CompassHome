package club.hardcoreminecraft.javase.compasshome;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.md_5.bungee.api.ChatColor;

public class playerCompassEvent implements Listener {
	


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCompass(PlayerInteractEvent event) {
		
		if(!methods.isWorldEnabled(event.getPlayer().getWorld().getName())) return;
		
		Player player = event.getPlayer();
		
		if(player.getItemInHand().getType() == Material.COMPASS) {
			if((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				
				handleItemChange(player);	
				event.setCancelled(true);
			} 

		}
		
		
		
	}
	
	
	public void changeItemInHand(Player player) {
		ItemStack hand = methods.getNamedCompass();
		player.setItemInHand(hand);
		
	}
	
	public void handleItemChange(Player player) {
		
		if(player.getItemInHand().equals(methods.getNamedCompass())) {
			teleportPlayertoBed(player);
		} else {
			changeItemInHand(player);
		}
		
	}
	

	
	public void teleportPlayertoBed(final Player player) {
		player.getItemInHand().setAmount(0);
		
		int delay = main.plugin.getConfig().getInt("teleportDelay");
		
		//Check if we need to send player a message about the teleport delay
		if(delay > 0) {
			if (main.plugin.getConfig().getBoolean("cancelTeleportOnMove")) 	
				player.sendMessage(ChatColor.GOLD + "You will be teleported to your bed in " + delay + " seconds! Do not move!");
			else 
		player.sendMessage(ChatColor.GOLD + "You will be teleported to your bed in " + delay + " seconds!");
	}
		
		try {
			
			
			main.tpQueue.put(player.getUniqueId(), new BukkitRunnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
			    	Location location = player.getBedSpawnLocation();
			    	
			    	//Check if the player has a bed location before we try and use it
			    	if(location != null) {
			    	
			    	player.teleport(location);
			    	main.tpQueue.remove(player.getUniqueId());
		
			    	player.sendMessage(ChatColor.GOLD + "You have been teleported to your bed.");
			    } else {
			    	//if they do not have a bed, send this message
			    	player.sendMessage(ChatColor.RED + "Your bed is missing or obstructed. ");
			    }
			    	
				}
			}.runTaskLater(main.plugin, delay *20));
			
		} catch (Exception error) {
			//Check for errors
			player.sendMessage(ChatColor.RED + "An Error occured while teleporting. Please inform staff if this issue persists.");
			error.printStackTrace();
		}
		
		
		
	}
}
