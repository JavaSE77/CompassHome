package club.hardcoreminecraft.javase.compasshome;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class main extends JavaPlugin {
	 static main plugin;
	  
	  public void onEnable() {

		  //register new events: 		
		plugin = getPlugin(main.class);
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new playerCompassEvent(), this);
		
		//register commands
		
		//setup the config
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
	    	  }

}
