package club.hardcoreminecraft.javase.compasshome;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;



public class main extends JavaPlugin {
	 static main plugin;
	 
		//Create a hashmap of all players in queue to teleport. 
	 static Map<UUID, BukkitTask> tpQueue = new HashMap<UUID, BukkitTask>();
	 
	  public void onEnable() {


		  //register new events: 		
		plugin = getPlugin(main.class);
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new playerCompassEvent(), this);
		pluginManager.registerEvents(new playerJoinSetCompass(), this);
		pluginManager.registerEvents(new cancelTeleportEvents(), this);
		
		//register commands 
		
		//setup the config
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
	    	  }

}
