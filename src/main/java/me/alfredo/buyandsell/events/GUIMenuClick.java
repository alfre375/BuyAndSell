package me.alfredo.buyandsell.events;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIMenuClick implements Listener {


    BuyAndSell buyAndSell;
    public GUIMenuClick(BuyAndSell buyAndSell) {
        this.buyAndSell = buyAndSell;
    }

    public void GemNo(Player pwc) {
        Inventory confirm = Bukkit.createInventory(pwc,9);
        //
        ItemStack Yes = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta yesMeta = Yes.getItemMeta();
        yesMeta.setDisplayName(ChatColor.AQUA + "Sí");
        Yes.setItemMeta(yesMeta);
        confirm.setItem(3,Yes);
        //
        ItemStack Diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = Diamond.getItemMeta();
        List<String> diamondLore = new ArrayList<String>();
        diamondLore.add(ChatColor.translateAlternateColorCodes('&',"&6Compra por &4$&c100"));
        diamondMeta.setLore(diamondLore);
        Diamond.setItemMeta(diamondMeta);
        confirm.setItem(4,Diamond);
        //
        ItemStack no = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta noMeta = no.getItemMeta();
        noMeta.setDisplayName(ChatColor.AQUA + "No");
        no.setItemMeta(noMeta);
        confirm.setItem(5,no);
        //
        ItemStack invName = new ItemStack(Material.CHEST);
        ItemMeta invNameMeta = invName.getItemMeta();
        invNameMeta.setDisplayName("Tienda: Gemas: Diamante: Confirmar");
        invName.setItemMeta(invNameMeta);
        confirm.setItem(8,invName);
        //
        pwc.openInventory(confirm);
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {



        Inventory clicked = e.getClickedInventory();
        //e.getWhoClicked().sendMessage("Size is " + clicked.getSize() + "!!");

        if (clicked.getSize() >= 45) {
            //e.getWhoClicked().sendMessage("Size >= 45!!");
            ItemStack InventoryName = clicked.getItem(44);
            if (InventoryName.getType() == Material.CHEST && InventoryName.getItemMeta().getDisplayName().equals("Tienda: Gemas")) {

                Player pwc = (Player) e.getWhoClicked();
                Material type = e.getCurrentItem().getType();
                //pwc.sendMessage("Choosing material!!");
                switch (type) {
                    case BARRIER:
                        pwc.closeInventory();
                        pwc.chat("/shop");
                        break;
                    case DIAMOND:
                        pwc.closeInventory();
                        //
                        GemNo(pwc);
                        break;
                    default:
                        //pwc.sendMessage("Did not chose anything!!" + type.toString() + " equals " + (type == Material.DIAMOND));

                }

                e.setCancelled(true);
            }
        }


        if (clicked.getSize() >= 36) {
            ItemStack InventoryName = e.getClickedInventory().getItem(35);
            if (InventoryName.getType() == Material.CHEST && InventoryName.getItemMeta().getDisplayName().equals("Tienda")) {

                Player pwc = (Player) e.getWhoClicked();
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Cerrar")) {
                    pwc.closeInventory();
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Gemas")) {
                    pwc.closeInventory();
                    Inventory gems = Bukkit.createInventory(pwc,45);

                    ItemStack close = new ItemStack(Material.BARRIER);
                    ItemMeta closeMeta = close.getItemMeta();
                    closeMeta.setDisplayName(ChatColor.RED + "Retrocede");
                    close.setItemMeta(closeMeta);
                    gems.setItem(40,close);

                    ItemStack invName = new ItemStack(Material.CHEST);
                    ItemMeta invNameMeta = invName.getItemMeta();
                    invNameMeta.setDisplayName("Tienda: Gemas");
                    invName.setItemMeta(invNameMeta);
                    gems.setItem(44,invName);

                    ItemStack diamond = new ItemStack(Material.DIAMOND);
                    ItemMeta diamondMeta = diamond.getItemMeta();
                    List<String> diamondLore = new ArrayList<String>();
                    diamondLore.add(ChatColor.translateAlternateColorCodes('&',"&6Compra por &4$&c100"));
                    diamondMeta.setLore(diamondLore);
                    diamond.setItemMeta(diamondMeta);
                    gems.setItem(4,diamond);

                    pwc.openInventory(gems);
                }

                e.setCancelled(true);
            }
        }

        if (e.getClickedInventory().getSize() >= 9) {
            ItemStack InventoryName = e.getClickedInventory().getItem(8);
            if (InventoryName.getType() == Material.CHEST && InventoryName.getItemMeta().getDisplayName().equals("Tienda: Gemas: Diamante: Confirmar")) {

                Player pwc = (Player) e.getWhoClicked();
                Material type = e.getCurrentItem().getType();

                switch (type) {
                    case EMERALD_BLOCK:
                        pwc.closeInventory();
                        if (buyAndSell.config.getInt(buyAndSell.getCashPath(pwc,"default")) > 100) {
                            ItemStack diamond = new ItemStack(Material.DIAMOND,1);
                            buyAndSell.config.set(buyAndSell.getCashPath(pwc,"default"), buyAndSell.config.getInt(buyAndSell.getCashPath(pwc,"default")) - 100);
                            pwc.getInventory().addItem(diamond);
                            pwc.sendMessage(ChatColor.GREEN + "You have successfully purchased 1 diamond. You have $" + buyAndSell.config.getInt(buyAndSell.getCashPath(pwc,"default")) + " left in yur default wallet");
                        } else {
                            int moreNeeded = 100 - buyAndSell.config.getInt(buyAndSell.getCashPath(pwc,"default"));
                            pwc.sendMessage(ChatColor.RED + "You do not have enough money to buy diamond. Cost: $100. You need $" + moreNeeded + " more.");
                        }
                        break;
                    case REDSTONE_BLOCK:
                        pwc.closeInventory();
                        pwc.sendMessage("Cancelled");
                        //GemNo(pwc);
                        Inventory gems = Bukkit.createInventory(pwc,45);

                        ItemStack close = new ItemStack(Material.BARRIER);
                        ItemMeta closeMeta = close.getItemMeta();
                        closeMeta.setDisplayName(ChatColor.RED + "Retrocede");
                        close.setItemMeta(closeMeta);
                        gems.setItem(40,close);

                        ItemStack invName = new ItemStack(Material.CHEST);
                        ItemMeta invNameMeta = invName.getItemMeta();
                        invNameMeta.setDisplayName("Tienda: Gemas");
                        invName.setItemMeta(invNameMeta);
                        gems.setItem(44,invName);

                        ItemStack diamond = new ItemStack(Material.DIAMOND);
                        ItemMeta diamondMeta = diamond.getItemMeta();
                        List<String> diamondLore = new ArrayList<String>();
                        diamondLore.add(ChatColor.translateAlternateColorCodes('&',"&6Compra por &4$&c100"));
                        diamondMeta.setLore(diamondLore);
                        diamond.setItemMeta(diamondMeta);
                        gems.setItem(4,diamond);

                        pwc.openInventory(gems);
                        break;
                }

                e.setCancelled(true);
            }
        }
    }

}
