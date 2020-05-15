package club.hardcoreminecraft.javase.compasshome;

import java.awt.List;
import java.util.ArrayList;

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

import net.md_5.bungee.api.ChatColor;

public class playerCompassEvent implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCompass(PlayerInteractEvent event) {
		
		if(!(main.plugin.getConfig().getList("enabledWorlds").contains(event.getPlayer().getWorld().getName()))) return; 
		
		Player player = event.getPlayer();
		
		if(player.getItemInHand().getType() == Material.COMPASS) {
			if((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				
				handleItemChange(player);	
				event.setCancelled(true);
			} 

		}
		
		
		
	}
	
	
	public void changeItemInHand(Player player) {
		ItemStack hand = getNamedCompass();
		player.setItemInHand(hand);
		
	}
	
	public void handleItemChange(Player player) {
		
		if(player.getItemInHand().equals(getNamedCompass())) {
			teleportPlayertoBed(player);
		} else {
			changeItemInHand(player);
		}
		
	}
	
	public ItemStack getNamedCompass() {
		ItemStack compass = new ItemStack(Material.COMPASS ,1);
		ItemMeta meta = compass.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Click to teleport to your bed.");
		lore.add(ChatColor.GOLD + "This will consume your compass");
		meta.setDisplayName(ChatColor.GOLD +""+ ChatColor.BOLD + "Click again to teleport");
		meta.setLore(lore);
		compass.setItemMeta(meta);
		
		return compass;
	}
	
	public void teleportPlayertoBed(final Player player) {
		player.getItemInHand().setAmount(0);
		
		int delay = main.plugin.getConfig().getInt("teleportDelay");
		
		if(delay > 0) 	
		player.sendMessage(ChatColor.GOLD + "You will be teleported to your bed in " + delay + " seconds!");
		
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
		    @Override
		    public void run() {
		    	
		    	Location location = player.getBedSpawnLocation();
		    	
		    	player.teleport(location);
		        
		    	player.sendMessage(ChatColor.GOLD + "You have been teleported to your bed.");
		    	
		    }
		},delay);
		
	}
}
