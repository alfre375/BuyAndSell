package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMoneyFromNowhere implements CommandExecutor {

    BuyAndSell buySell;
    public GetMoneyFromNowhere(BuyAndSell buySell){
        this.buySell = buySell;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp() || p.hasPermission("BuyAndSell.GetMoneyFromThinAir")){
                if(buySell.hasBalance(p,"default")) {
                    double old = buySell.config.getDouble(buySell.getCashPath(p, "default"));
                    double other = Double.parseDouble(args[0]);
                    System.out.println("Printing: CashQTYs:" + old + " , args[0](double):" + other + " args[0](orig):"+args[0]);
                    double New_Amount = old + other;
                    buySell.config.set(buySell.getCashPath(p, "default"), New_Amount);
                    p.sendMessage(ChatColor.GREEN + "You have been granted $" + args[0] + " by the gods of Minecraft: Java Edition!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You DO NOT have permission to run this command! The gods of Minecraft: Java Edition will be mad at you!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can run this command!");
        }
        return true;
    }
}
