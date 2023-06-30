package me.alfredo.buyandsell.commands;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop implements CommandExecutor {

    BuyAndSell buySell;
    public Shop(BuyAndSell buySell) {this.buySell = buySell;}
    String NoPermMsg = ChatColor.translateAlternateColorCodes('&',"&cYou do not have permission to execute this command");



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("BuyAndSell.buy") && p.hasPermission("BuyAndSell.shop")) {
                Inventory shopGui = Bukkit.createInventory(p,36, "Tienda");

                ItemStack close = new ItemStack(Material.BARRIER);
                ItemMeta closeMeta = close.getItemMeta();
                closeMeta.setDisplayName(ChatColor.RED + "Cerrar");
                close.setItemMeta(closeMeta);
                shopGui.setItem(31,close);

                ItemStack Inv = new ItemStack(Material.CHEST);
                ItemMeta InvMeta = Inv.getItemMeta();
                InvMeta.setDisplayName("Tienda");
                Inv.setItemMeta(InvMeta);
                shopGui.setItem(35,Inv);

                ItemStack gems = new ItemStack(Material.DIAMOND);
                ItemMeta gemMeta = gems.getItemMeta();
                gemMeta.setDisplayName(ChatColor.AQUA + "Gemas");
                gems.setItemMeta(gemMeta);
                shopGui.setItem(13,gems);

                p.openInventory(shopGui);
            } else {
                p.sendMessage(NoPermMsg);
            }
        } else {
            //
        }
        return true;
    }
}
