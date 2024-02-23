package me.alfredo.buyandsell.items;

import me.alfredo.buyandsell.BuyAndSell;
import me.alfredo.buyandsell.enums.ItemGrantSuccessEnum;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GetMoneyItem {
    public BuyAndSell bns;
    public GetMoneyItem(BuyAndSell bns) {
        this.bns = bns;
    }
    public ItemStack generateMoneyItem(float totalAmount) {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.
                translateAlternateColorCodes('&',
                        bns.config.getString("moneyItemName")));
        meta.setCustomModelData(441622);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&6Valor: " + totalAmount));
        lore.add(String.valueOf(totalAmount));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public ItemGrantSuccessEnum grantMoneyItem(Player p, float amount) {
        if (!p.isOnline()) {
            return ItemGrantSuccessEnum.INVALID_PLAYER;
        }
        if (p.isBanned()) {
            return ItemGrantSuccessEnum.INVALID_PLAYER;
        }
        if (amount < 0) {
            return ItemGrantSuccessEnum.NEGATIVE_VALUE;
        }
        ItemStack item = generateMoneyItem(amount);
        Inventory inv = p.getInventory();
        int fe = inv.firstEmpty();
        if (fe < 0) {
            p.getWorld().dropItem(p.getLocation(), item);
            p.sendMessage(ChatColor.RED +
                    "STOP! Your money item is on the floor!");
            return ItemGrantSuccessEnum.SUCCESS_DROPPED;
        } else {
            inv.addItem(item);
            return ItemGrantSuccessEnum.SUCCESS;
        }
    }
}
