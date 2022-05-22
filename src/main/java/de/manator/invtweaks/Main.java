package de.manator.invtweaks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.manator.invtweaks.commands.InvTweaks;
import de.manator.invtweaks.commands.InvTweaksTab;
import de.manator.invtweaks.commands.SortCMD;
import de.manator.invtweaks.events.BlockPlaceEvent;
import de.manator.invtweaks.events.InvCloseEvent;
import de.manator.invtweaks.events.ItemBreakEvent;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("Registering events...");
		Bukkit.getPluginManager().registerEvents(new InvCloseEvent(getDataFolder()), this);
		Bukkit.getPluginManager().registerEvents(new ItemBreakEvent(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlaceEvent(), this);
		getLogger().info("Done!");
		getLogger().info("Registering commands...");
		Bukkit.getPluginCommand("sort").setExecutor(new SortCMD());
		Bukkit.getPluginCommand("invtweaks").setExecutor(new InvTweaks(getDataFolder()));
		Bukkit.getPluginCommand("invtweaks").setTabCompleter(new InvTweaksTab());
		getLogger().info("Done!");
		getLogger().info("Creating missing files...");
		Config.createConfig(getDataFolder());
		getLogger().info("Done!");
	}
	
}
