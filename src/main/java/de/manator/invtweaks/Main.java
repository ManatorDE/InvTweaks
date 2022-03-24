package de.manator.invtweaks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.manator.invtweaks.commands.SortCMD;
import de.manator.invtweaks.events.BlockPlaceEvent;
import de.manator.invtweaks.events.InvCloseEvent;
import de.manator.invtweaks.events.ItemBreakEvent;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		Bukkit.getPluginManager().registerEvents(new InvCloseEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ItemBreakEvent(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlaceEvent(), this);
		Bukkit.getPluginCommand("sort").setExecutor(new SortCMD());
		
	}
	
}
