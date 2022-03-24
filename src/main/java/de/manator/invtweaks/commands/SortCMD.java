package de.manator.invtweaks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SortCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("sort")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				sortInventory(p);
			}
		}
		return true;
	}

	private void sortInventory(Player p) {
		ItemStack[] inv = p.getInventory().getStorageContents();
		ItemStack[] newInv = new ItemStack[inv.length - 9];
		for(int i = 0; i < newInv.length; i++)  {
			newInv[i] = inv[i+9];
		}
		bubbleSort(newInv);
		bubbleSort(newInv);
		for(int i = 0; i < newInv.length; i++) {
			inv[i+9] = newInv[i];
		}
		p.getInventory().setStorageContents(inv);
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
