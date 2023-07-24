package me.alfredo.buyandsell;

import me.alfredo.buyandsell.enums.WithdrawEvaluations;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class EconomyImplementer implements Economy {
    BuyAndSell buyAndSell;
    public EconomyImplementer(BuyAndSell buyAndSell) {
        this.buyAndSell = buyAndSell;
    }
    @Override
    public boolean isEnabled() {
        return buyAndSell.vaultConnected;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 4;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return null;
    }

    @Override
    public boolean hasAccount(String s) {
        Player p = Bukkit.getPlayer(s);
        return buyAndSell.hasBalance(p, "default");
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return buyAndSell.hasBalance((Player) offlinePlayer, "default");
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return buyAndSell.hasBalance((Player) offlinePlayer, "default");
    }

    @Override
    public double getBalance(String s) {
        Player p = Bukkit.getPlayer(s);
        return buyAndSell.playerCashD(p, "default");
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return buyAndSell.playerCashD(offlinePlayer, "default");
    }

    @Override
    public double getBalance(String s, String s1) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return buyAndSell.playerCashD(offlinePlayer, "default");
    }

    @Override
    public boolean has(String s, double v) {
        return buyAndSell.playerCashD(Bukkit.getPlayer(s), "default") > v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return buyAndSell.playerCashD(offlinePlayer, "default") > v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return buyAndSell.playerCashD(offlinePlayer, "default") > v;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        Player p = Bukkit.getPlayer(s);
        WithdrawEvaluations withdrawEvaluations = buyAndSell.withdrawCash(p, "default", v);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        WithdrawEvaluations withdrawEvaluations = buyAndSell.withdrawCash(offlinePlayer, "default", v);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        WithdrawEvaluations withdrawEvaluations = buyAndSell.withdrawCash(offlinePlayer, "default", v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        Player p = Bukkit.getPlayer(s);
        buyAndSell.depositCash(p, "default", v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        buyAndSell.depositCash(offlinePlayer, "default", v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        buyAndSell.depositCash(offlinePlayer, "default", v);
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}
