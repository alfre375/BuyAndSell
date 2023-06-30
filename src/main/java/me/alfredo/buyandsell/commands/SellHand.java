package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SellHand implements CommandExecutor {
    BuyAndSell buySell;
    public SellHand(BuyAndSell buySell) {this.buySell = buySell;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            ItemStack inHand = p.getItemInHand();
            //ItemMeta itemMeta = inHand.getItemMeta();
            Material type = inHand.getType();
            List<String> stringList = buySell.getConfig().getStringList("ItemSellValues");
            boolean isDone = false;
            for (String x : stringList) {

                if (x.equalsIgnoreCase(type.name())) {
                    double y = buySell.config.getDouble("ItemSellValues." + x);
                    buySell.config.set(buySell.getCashPath(p,"default"),buySell.getConfig().getDouble(buySell.getCashPath(p,"default") + y));
                    inHand.setAmount(inHand.getAmount() - 1);
                    isDone = true;
                }
            }

            if (isDone == false) {
                p.sendMessage("You can't sell " + type.name());
            }
        }

        return true;
    }
}
