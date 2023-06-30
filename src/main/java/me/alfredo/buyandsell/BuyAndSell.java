package me.alfredo.buyandsell;

import me.alfredo.buyandsell.commands.*;
import me.alfredo.buyandsell.enums.WithdrawEvaluations;
import me.alfredo.buyandsell.events.GUIMenuClick;
import me.alfredo.buyandsell.events.OnPlayerJoin;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BuyAndSell extends JavaPlugin {
    public boolean vaultConnected = false;
    public EconomyImplementer economyImplementer;
    private VaultHook vaultHook;

    public FileConfiguration config = getConfig();
    public boolean isVaultInstalled = isVaultInstalled();
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
        vaultConnected = isVaultInstalled && config.getBoolean("connectVault");
        if (vaultConnected) {
            economyImplementer = new EconomyImplementer(this);
            vaultHook.hook();
        }
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Saving config.yml...");
        saveConfig();
        vaultConnected = false;
        System.out.println("Unhooking vault");
        vaultHook.unhook();
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
    public double playerCashD(OfflinePlayer offlinePlayer, String wallet) {
        return config.getDouble(getCashPath((Player) offlinePlayer, wallet));
    }
    public double depositCash(OfflinePlayer offlinePlayer, String wallet, double amount) {
        config.set(getCashPath((Player) offlinePlayer, wallet), config.getDouble(getCashPath((Player) offlinePlayer, wallet)) + amount);
        return config.getDouble(getCashPath((Player) offlinePlayer, wallet));
    }
    public boolean canAfford(OfflinePlayer offlinePlayer, String wallet, double amount) {
        return playerCashD(offlinePlayer, wallet) > amount;
    }
    public WithdrawEvaluations withdrawCash(OfflinePlayer offlinePlayer, String wallet, double amount) {
        if (canAfford(offlinePlayer, wallet, amount)) {
            double oldBal = playerCashD(offlinePlayer, wallet);
            config.set(getCashPath((Player) offlinePlayer, wallet), playerCashD(offlinePlayer, wallet) - amount);
            if (playerCashD(offlinePlayer, wallet) == oldBal - amount) {
                return WithdrawEvaluations.SUCCESS;
            } else {
                config.set(getCashPath((Player) offlinePlayer, wallet), playerCashD(offlinePlayer, wallet));
                return WithdrawEvaluations.FAIL;
            }
        } else {
            return WithdrawEvaluations.NOT_ENOUGH_MONEY;
        }
    }
    public double PlayerCurrentCash;
    public boolean isVaultInstalled() {
        return getServer().getPluginManager().getPlugin("MyPlugin")!=null;
    }
}
