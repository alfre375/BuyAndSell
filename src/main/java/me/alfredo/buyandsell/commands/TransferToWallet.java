package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TransferToWallet implements CommandExecutor {

    BuyAndSell buySell;
    public TransferToWallet(BuyAndSell buySell){
        this.buySell = buySell;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Integer quantity;

        String fromWallet = "";
        String toWallet = "";

        if (args.length < 3) {
            p.sendMessage("Missing arguments");
            return true;
        }

        fromWallet = args[0];
        toWallet = args[1];

        if (!buySell.hasBalance(p,fromWallet)) {
            p.sendMessage(ChatColor.RED + "From Wallet does not exist");
            return true;
        }

        if (!buySell.hasBalance(p,toWallet)) {
            p.sendMessage(ChatColor.RED + "To Wallet does not exist");
            return true;
        }

        try{
            quantity = Integer.parseInt(args[2]);
        }catch(NumberFormatException e){
            sender.sendMessage(ChatColor.RED + "This command argument only takes values that can be converted into integers. No decimals!");
            return true;
        }
        if (buySell.config.getInt(buySell.getCashPath(p,fromWallet)) >= quantity) {
            buySell.config.set(buySell.getCashPath(p,fromWallet), buySell.config.getInt(buySell.getCashPath(p,fromWallet)) - quantity);
            buySell.config.set(buySell.getCashPath(p,toWallet), buySell.config.getInt(buySell.getCashPath(p,toWallet)) + quantity);
            p.sendMessage(ChatColor.GREEN + "You have put " + " $" + quantity + " into wallet " + toWallet + " from wallet " + fromWallet + " with success!");
            System.out.println(p.getName() + p.getDisplayName() + " has put " + " $" + quantity + " into wallet " + toWallet + " from wallet " + fromWallet + " with success!");
        }
        return true;
    }
}
