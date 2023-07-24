package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewBalance implements CommandExecutor {

    BuyAndSell buySell;
    public ViewBalance(BuyAndSell buySell){
        this.buySell = buySell;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String wallet = "default";
        if (args.length >= 1) {
            wallet = args[0];
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (buySell.hasBalance((Player) sender,"default")) {
                p.sendMessage(ChatColor.GREEN + "You have $" + buySell.config.getDouble(buySell.getCashPath(p,wallet)) + " in your balance!");
            } else {
                p.sendMessage("You somehow don't have a balance, try leaving and re-joining!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You cannot run this command as the console, as console does not get a balance.");
        }
        return true;
    }
}

