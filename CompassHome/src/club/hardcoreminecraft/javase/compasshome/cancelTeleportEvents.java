package club.hardcoreminecraft.javase.compasshome;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatColor;

public class cancelTeleportEvents implements Listener {

	@EventHandler
	public void playerDamageEvent(EntityDamageEvent event) {
		if( event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			
			//Check if the players uuid is in the list of players teleporting
			if(isPlayerTeleporting(player)) {
				if(cancelTeleportEnabled()) {
					Bukkit.getScheduler().cancelTask(main.tpQueue.get(player.getUniqueId()).getTaskId());
					main.tpQueue.remove(player.getUniqueId());
					informPlayertpCancled(player);
				}
			}
			
		}
	}
	

	
	
	
	public boolean isPlayerTeleporting(Player player) {
		return main.tpQueue.containsKey(player.getUniqueId());
	}
	
	public boolean cancelTeleportEnabled() {
		return main.plugin.getConfig().getBoolean("cancelTeleportOnMove");
	}
	
	public void informPlayertpCancled(Player player) {
		//Inform the player and the logger that the player is no longer teleporting
		player.sendMessage(ChatColor.RED + "Teleporting canceled. ");
		main.plugin.getLogger().info("[CompasssHomes] " +player.getName() + "'s teleport has been canceled. ");
	}
	
	
	
}
