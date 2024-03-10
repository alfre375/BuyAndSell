package me.alfredo.buyandsell.events;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerItemRightClick implements Listener {
    BuyAndSell bns;
    public PlayerItemRightClick(BuyAndSell bns) {
        this.bns = bns;
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        if (a == Action.RIGHT_CLICK_AIR) {
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            if (meta.getCustomModelData() == 441622) {
                String strVal = lore.get(1);
                double val = Double.parseDouble(strVal);
                bns.depositCash(p,"default",val);
                item.setAmount(0);
                p.sendMessage(ChatColor.GREEN + "Se ha agregado el dinero a tu cuenta de manera satisfactoria");
            }
        }
    }
}
