package me.alfredo.buyandsell;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {
    private BuyAndSell buyAndSell;
    private Economy eco;
    public void hook() {
        eco = buyAndSell.economyImplementer;
        Bukkit.getServicesManager().register(Economy.class, this.eco,buyAndSell, ServicePriority.High);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "VaultAPI  hooked into " + ChatColor.AQUA + buyAndSell.getName());
    }
    public void unhook() {
        Bukkit.getServicesManager().unregister(Economy.class, this.eco);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "VaultAPI  unhooked from " + ChatColor.AQUA + buyAndSell.getName());
    }
}
