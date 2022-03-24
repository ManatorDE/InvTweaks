package de.manator.invtweaks.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemBreakEvent implements Listener {
	
	@EventHandler
	public void itemBreakEvent(PlayerItemBreakEvent e) {
		Player p = e.getPlayer();
		Material mat = e.getBrokenItem().getType();
		
		PlayerInventory inv = p.getInventory();
		
		int slot = 0;
		
		for(int i = 9; i < inv.getSize() - 5; i ++) {
			if(inv.getItem(i) != null && inv.getItem(i).getType() == mat) {
				slot = i;
				break;
			}
		}
		
		if(slot != 0) {
			ItemStack newItem = inv.getItem(slot);
			inv.clear(slot);
			inv.setItemInMainHand(newItem);
		}
	}
	
	
	
}
