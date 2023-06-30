package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MakeWallet implements CommandExecutor {
    BuyAndSell buyAndSell;
    public MakeWallet(BuyAndSell buyAndSell) {this.buyAndSell = buyAndSell;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Player t = p;
            String wallet = "";
            if (args.length < 1) {
                p.sendMessage(ChatColor.RED + "You need to specify a wallet name");
                return true;
            }
            wallet = args[0];
            if (args.length >= 2) {
                if (p.hasPermission("BuyAndSell.MakeWallet.Others")) {
                    t = Bukkit.getPlayer(args[1]);
                    if (t == null) {
                        p.sendMessage(ChatColor.RED + "Player does not exist");
                        return true;
                    }
                    if (!t.isOnline()) {
                        p.sendMessage(ChatColor.RED + "Player is offline");
                        return true;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                    if (p.isOp()) {
                        p.sendMessage("You need permission BuyAndSell.MakeWallet.Others");
                    }
                    return true;
                }
            }
            if (p.isOp() || p.hasPermission("BuyAndSell.MakeWallet")) {
                buyAndSell.getConfig().set(buyAndSell.getCashPath(t,wallet),buyAndSell.getConfig().getInt("spareWalletDefault"));
            }
        }
        return true;
    }
}
