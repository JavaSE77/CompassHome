package club.hardcoreminecraft.javase.compasshome;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class methods {

	public static boolean isWorldEnabled(String worldname) {
		if((main.plugin.getConfig().getList("enabledWorlds").contains(worldname))) {
			return true;
		}
		return false;
	}
	
	public static ItemStack getNamedCompass() {
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
	
}
