package de.manator.invtweaks.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class InvTweaksTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		
		ArrayList<String> list = new ArrayList<>();
		
		if(command.getName().equalsIgnoreCase("invtweaks")) {
			if(args.length == 1) {
				list.add("togglechestsorting");
				list.add("togglebarrelsorting");
				list.add("toggleshulkersorting");
				list.add("toggleendersorting");
			}
		}
		
		return list;
	}
	
}
