package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import me.alfredo.buyandsell.enums.ItemGrantSuccessEnum;
import me.alfredo.buyandsell.items.GetMoneyItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MoneyItemCommand implements CommandExecutor {
    BuyAndSell bns;
    public MoneyItemCommand(BuyAndSell bns) {
        this.bns = bns;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = sender instanceof Player ? (Player) sender : null;
        float amount = args.length >= 1 ? Float.parseFloat(args[0]) : (float) 1.0;
        if (p != null) {
            GetMoneyItem gmi = new GetMoneyItem(bns);
            ItemGrantSuccessEnum igse = gmi.grantMoneyItem(p, amount);
            p.sendMessage(ChatColor.GREEN + "Se le ha concedido el objeto");
        } else {
            p.sendMessage(ChatColor.RED + "Oops. Debes ser un jugador");
        }
        return true;
    }
}
