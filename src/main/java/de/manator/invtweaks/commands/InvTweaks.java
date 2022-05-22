package de.manator.invtweaks.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.manator.invtweaks.Config;

public class InvTweaks implements CommandExecutor {
	
	private File dataFolder;
	
	public InvTweaks(File dataFolder) {
		this.dataFolder = dataFolder;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("invtweaks")) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("togglechestsorting")) {
					Config.toggleSorting(dataFolder, Config.CHEST);
					if(Config.isSortingEnabled(dataFolder, Config.CHEST)) {
						sender.sendMessage("[InvTweaks] §aChest sorting was enabled!");
					} else {
						sender.sendMessage("[InvTweaks] §cChest sorting was disabled!");
					}
				} else if(args[0].equalsIgnoreCase("togglebarrelsorting")) {
					Config.toggleSorting(dataFolder, Config.BARREL);
					if(Config.isSortingEnabled(dataFolder, Config.BARREL)) {
						sender.sendMessage("[InvTweaks] §aBarrel sorting was enabled!");
					} else {
						sender.sendMessage("[InvTweaks] §cBarrel sorting was disabled!");
					}
				} else if(args[0].equalsIgnoreCase("toggleendersorting")) {
					Config.toggleSorting(dataFolder, Config.ENDER);
					if(Config.isSortingEnabled(dataFolder, Config.ENDER)) {
						sender.sendMessage("[InvTweaks] §aEnder chest sorting was enabled!");
					} else {
						sender.sendMessage("[InvTweaks] §cEnder chest sorting was disabled!");
					}
				} else if(args[0].equalsIgnoreCase("toggleshulkersorting")) {
					Config.toggleSorting(dataFolder, Config.SHULKER);
					if(Config.isSortingEnabled(dataFolder, Config.SHULKER)) {
						sender.sendMessage("[InvTweaks] §aShulker box sorting was enabled!");
					} else {
						sender.sendMessage("[InvTweaks] §cShulker box sorting was disabled!");
					}
				}
			}
		}
		
		return true;
	}

}
