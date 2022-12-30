package xyz.masa3mc.survivalevent.fishing;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.masa3mc.survivalevent.pConf.PlayerConf;

import java.util.Arrays;
import java.util.Collections;

public class FishingResults {

    public static ItemStack instant(int i, Player p) {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        if (i == 1) {
            meta.setDisplayName("§6なお～ル君(弱)");
            if (p == null) meta.setLore(Arrays.asList("§7即席修繕キット「なお～ル君」の1ツール用。", "§6>>§a右クリックで開く"));
            else {
                PlayerConf playerConf = new PlayerConf(p.getUniqueId());
                meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("instantMending1") ? playerConf.getPEachConf().getInt("instantMending1") : 0)));
            }
        }
        if (i == 4) {
            meta.setDisplayName("§6なお～ル君(中)");
            if (p == null) meta.setLore(Arrays.asList("§7即席修繕キット「なお～ル君」の4ツール用。", "§6>>§a右クリックで開く"));
            else {
                PlayerConf playerConf = new PlayerConf(p.getUniqueId());
                meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("instantMending4") ? playerConf.getPEachConf().getInt("instantMending4") : 0)));
            }
        }
        if (i == 7) {
            meta.setDisplayName("§6なお～ル君(強)");
            if (p == null) meta.setLore(Arrays.asList("§7即席修繕キット「なお～ル君」の7ツール用。", "§6>>§a右クリックで開く"));
            else {
                PlayerConf playerConf = new PlayerConf(p.getUniqueId());
                meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("instantMending7") ? playerConf.getPEachConf().getInt("instantMending7") : 0)));
            }
        }
        meta.addEnchant(Enchantment.LUCK, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        NBTItem nbtItem = new NBTItem(item);
        if (i == 1) nbtItem.setInteger("InstantMending", 1);
        if (i == 4) nbtItem.setInteger("InstantMending", 4);
        if (i == 7) nbtItem.setInteger("InstantMending", 7);
        nbtItem.applyNBT(item);
        return item;
    }

    public static ItemStack mochigome(Player p) {
        ItemStack item = new ItemStack(Material.PUMPKIN_SEEDS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lもち米");
        if (p == null) meta.setLore(Arrays.asList("§aどう見ても某無人島生活TVの", "§aちねり米だが気にしない。"));
        else {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("mochigome") ? playerConf.getPEachConf().getInt("mochigome") : 0)));
        }
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        NBTItem nbtItem = new NBTItem(item);
        return item;
    }

    public static ItemStack shiratama(Player p) {
        ItemStack item = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§l白玉");
        if (p == null) meta.setLore(Arrays.asList("§a冷たいアイスにも温かいぜんざいにも", "§a相性バッチリ。"));
        else {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("shiratama") ? playerConf.getPEachConf().getInt("shiratama") : 0)));
        }
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack kinako(Player p) {
        ItemStack item = new ItemStack(Material.RAW_IRON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lきな粉餅");
        if (p == null) meta.setLore(Arrays.asList("§a実は砂糖を付けなければ", "§aそんなにカロリーは高くないらしい。"));
        else {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("kinako") ? playerConf.getPEachConf().getInt("kinako") : 0)));
        }
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack daifuku(Player p) {
        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§l大福");
        if (p == null) meta.setLore(Arrays.asList("§aあんこ餅との差は餅の部分が", "§a甘いかどうからしい。"));
        else {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("daifuku") ? playerConf.getPEachConf().getInt("daifuku") : 0)));
        }
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack tsukitate(Player p) {
        ItemStack item = new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lつきたてお餅");
        if (p == null) meta.setLore(Arrays.asList("§aアツアツでよく伸びそうな", "§a馴染みのある出来たてのお餅。"));
        else {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("tsukitate") ? playerConf.getPEachConf().getInt("tsukitate") : 0)));
        }
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack gacha(int i, Player p) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6" + i + "§6万ガチャ券");
        if (p == null) meta.setLore(Collections.singletonList(i + "万ガチャを引くための券"));
        else {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            meta.setLore(Collections.singletonList("§a釣った回数§7: " + (playerConf.getPEachConf().contains("gacha" + i) ? playerConf.getPEachConf().getInt("gacha" + i) : 0)));
        }
        item.setItemMeta(meta);
        return item;
        //i = 1, 5, 10
    }
}
