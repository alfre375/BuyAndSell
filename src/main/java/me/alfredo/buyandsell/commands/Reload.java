package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {

    BuyAndSell buySell;
    public Reload(BuyAndSell buySell) {this.buySell = buySell;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean hasPerms = false;
        if (sender instanceof Player) {
            if (sender.hasPermission("BuyAndSell.Reload")) {
                hasPerms = true;
            }
        } else {
            hasPerms = true;
        }
        if (hasPerms) {
            buySell.reloadConfig();
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "YOU DO NOT HAVE PERMISSION TO EXECUTE THIS COMMAND!");
            return true;
        }
    }
}
