package me.alfredo.buyandsell;

import me.alfredo.buyandsell.commands.*;
import me.alfredo.buyandsell.events.GUIMenuClick;
import me.alfredo.buyandsell.events.OnPlayerJoin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BuyAndSell extends JavaPlugin {

    public FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        // Plugin startup logic
        config.options().copyDefaults(true);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginCommand("viewbalance").setExecutor(new ViewBalance(this));
        getServer().getPluginCommand("pay").setExecutor(new Pay(this));
        getServer().getPluginCommand("getmoney").setExecutor(new GetMoneyFromNowhere(this));
        getServer().getPluginCommand("makewallet").setExecutor(new MakeWallet(this));
        getServer().getPluginCommand("transfertowallet").setExecutor(new TransferToWallet(this));
        getServer().getPluginCommand("bnsreload").setExecutor(new Reload(this));
        getServer().getPluginCommand("sellhand").setExecutor(new SellHand(this));
        getServer().getPluginCommand("shop").setExecutor(new Shop(this));
        getServer().getPluginManager().registerEvents(new GUIMenuClick(this),this);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Saving config.yml...");
        saveConfig();
        System.out.println(ChatColor.GREEN + "Goodbye, see you next time!");
    }
    public boolean hasBalance(Player p,String wallet) {
        return config.contains("CashQTYs." + p.getUniqueId() + "." + wallet);
    }/*
    public FileConfiguration getConfig() {
        return config;
    }*/
    public String getCashPath(Player p, String wallet) {
        return "CashQTYs." + p.getUniqueId() + "." + wallet;
    }
    public int PlayerCurrentCash;
}
