package de.manator.invtweaks.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BlockPlaceEvent implements Listener {
	
	@EventHandler
	public void blockPlaceEvent(org.bukkit.event.block.BlockPlaceEvent e) {
		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		
		if(inv.getItemInMainHand().getAmount() == 1) {
			Material mat = e.getBlockPlaced().getType();
			
			int slot = 0;
			
			for(int i = 9; i < inv.getSize() - 5; i++) {
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
	
}
