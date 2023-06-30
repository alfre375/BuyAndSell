package me.alfredo.buyandsell.events;

import me.alfredo.buyandsell.BuyAndSell;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    BuyAndSell buySell;
    public OnPlayerJoin(BuyAndSell buySell){
        this.buySell = buySell;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!buySell.hasBalance(e.getPlayer(),"default")) {
            Player p = e.getPlayer();
            buySell.config.set(buySell.getCashPath(p, "default"), buySell.config.getInt("defaultCash"));
        }
    }
}
