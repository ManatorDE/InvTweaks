package de.manator.invtweaks.events;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.manator.invtweaks.Config;

public class InvCloseEvent implements Listener {

	private File dataFolder;

	public InvCloseEvent(File dataFolder) {
		this.dataFolder = dataFolder;
	}

	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		Inventory inv = e.getInventory();

		if (inv.getType() == InventoryType.CHEST && Config.isSortingEnabled(dataFolder, Config.CHEST)) {
			ItemStack[] contents = inv.getContents();
			inv.clear();
			bubbleSort(contents);
			inv.setContents(contents);
		} else if (inv.getType() == InventoryType.BARREL && Config.isSortingEnabled(dataFolder, Config.BARREL)) {
			ItemStack[] contents = inv.getContents();
			inv.clear();
			bubbleSort(contents);
			inv.setContents(contents);
		} else if (inv.getType() == InventoryType.SHULKER_BOX && Config.isSortingEnabled(dataFolder, Config.SHULKER)) {
			ItemStack[] contents = inv.getContents();
			inv.clear();
			bubbleSort(contents);
			inv.setContents(contents);
		} else if (inv.getType() == InventoryType.ENDER_CHEST && Config.isSortingEnabled(dataFolder, Config.ENDER)) {
			ItemStack[] contents = inv.getContents();
			inv.clear();
			bubbleSort(contents);
			inv.setContents(contents);
		}
	}

	private void bubbleSort(ItemStack[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] != null && arr[j + 1] != null) {
					if (arr[j].getType().compareTo(arr[j + 1].getType()) < 0) {
						// swap arr[j+1] and arr[j]
						ItemStack temp = arr[j];
						arr[j] = arr[j + 1];
						arr[j + 1] = temp;
					} else if (arr[j].getType().compareTo(arr[j + 1].getType()) == 0) {
						if (arr[j].getMaxStackSize() > arr[j].getAmount()) {
							if (arr[j].hasItemMeta() && arr[j + 1].hasItemMeta()) {
								if (arr[j].getItemMeta().equals(arr[j + 1].getItemMeta())) {
									int amount = arr[j].getAmount() + arr[j + 1].getAmount();
									int max = arr[j].getMaxStackSize();
									if (amount > max) {
										arr[j].setAmount(max);
										arr[j + 1].setAmount(amount - max);
									} else if (amount <= max) {
										arr[j].setAmount(amount);
										arr[j + 1] = null;
									}
								}
							} else if (!arr[j].hasItemMeta() && !arr[j + 1].hasItemMeta()) {
								int amount = arr[j].getAmount() + arr[j + 1].getAmount();
								int max = arr[j].getMaxStackSize();
								if (amount > max) {
									arr[j].setAmount(max);
									arr[j + 1].setAmount(amount - max);
								} else if (amount <= max) {
									arr[j].setAmount(amount);
									arr[j + 1] = null;
								}
							}
						}
					}
				} else if (arr[j] == null) {
					ItemStack temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

}
