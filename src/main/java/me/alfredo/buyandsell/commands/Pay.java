package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay implements CommandExecutor {

    BuyAndSell buySell;
    public Pay(BuyAndSell buySell){
        this.buySell = buySell;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        String fromWallet = "default";
        String toWallet = "default";
        if (args.length >= 3) {
            fromWallet = args[2];
        }
        if (args.length >= 4) {
            toWallet = args[3];
        }
        if (target == null) {
            sender.sendMessage("Invalid Target!");
            return true;
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            double quantity;
            try{
                quantity = Double.parseDouble(args[1]);
            }catch(NumberFormatException e){
                sender.sendMessage(ChatColor.RED + "This command argument only takes values that can be converted into doubles. No text!");
                return true;
            }

            if (quantity <= 0) {
                sender.sendMessage(ChatColor.RED + "The amount you entered is a negative number, which are not allowed, to charge the person money, use /charge or /fcharge");
                return true;
            }
            if (buySell.config.getDouble(buySell.getCashPath(p,fromWallet)) >= quantity) {
                buySell.config.set(buySell.getCashPath(p,fromWallet), buySell.config.getDouble(buySell.getCashPath(p,fromWallet)) - quantity);
                buySell.config.set(buySell.getCashPath(target,toWallet), buySell.config.getDouble(buySell.getCashPath(target,toWallet)) + quantity);
                p.sendMessage(ChatColor.GREEN + "You have paid " + args[0] + " $" + quantity + " with success!");
                target.sendMessage(ChatColor.GREEN + "You have been paid $" + quantity + " by the generous player who goes by the name of " + p.getName() + "!");
                System.out.println(p.getName() + " has given $" + quantity + " to " + args[0] + "!");
            }
        }
        return true;
    }
}
