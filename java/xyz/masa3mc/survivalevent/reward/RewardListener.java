package xyz.masa3mc.survivalevent.reward;

import static xyz.masa3mc.survivalevent.reward.RewardLists.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import de.tr7zw.nbtapi.NBTItem;

public class RewardListener implements @NotNull Listener {

    public void loop() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getInventory().getItemInMainHand().isSimilar(angel()))
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 0));
            if (p.getInventory().getHelmet() != null && new NBTItem(p.getInventory().getHelmet()).getString("event").equals("mochiFish"))
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 127));
            if (p.getInventory().getItemInMainHand().isSimilar(saltMochi())) {
                Player nameKun = Bukkit.getPlayerExact("NameKun");
                if (nameKun != null)
                    if (locationDistance(nameKun.getLocation(), p.getLocation()) < 4)
                        nameKun.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 3));
            }
            if (p.getInventory().getItemInOffHand().isSimilar(shiningMochi())) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 60, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 0));
            }
        }
    }

    @EventHandler
    public void preCraft(PrepareItemCraftEvent e) {
        if (e.getInventory().getContents()[0] != null && e.getInventory().getContents()[0].getType().equals(Material.FISHING_ROD)) {
            ItemStack[] contents = e.getInventory().getContents();
            boolean returnable = true;
            for (int i = 3; i < 7; i += 2)
                if (!contents[i].equals(stickMochi())) returnable = false;
            if (!contents[6].equals(stringMochi())) returnable = false;
            if (!contents[9].equals(stringMochi())) returnable = false;
            if (returnable) e.getInventory().setItem(0, happyRod());
        }
    }

    @EventHandler
    public void entityInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.WOLF)) {
            if (e.getPlayer().getInventory().getItemInMainHand().isSimilar(boneMochi())) {
                Wolf ent = (Wolf) e.getRightClicked();
                if (!ent.isTamed()) {
                    ent.setOwner(e.getPlayer());
                    e.getPlayer().sendMessage("§a狼を手懐けました！");
                } else e.getPlayer().sendMessage("§c既に他のプレイヤーまたは自身によって手懐けられています。");
            }
        }
    }

    @EventHandler
    public void consumeItem(PlayerItemConsumeEvent e) {
        if (e.getItem().equals(mochiPotion()))
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 4));
        if (e.getItem().equals(liquidMochi())) {
            e.getPlayer().getActivePotionEffects().forEach(potionEffect -> e.getPlayer().removePotionEffect(potionEffect.getType()));
            e.setReplacement(liquidMochi());
            
        }
    }

    private static int locationDistance(Location loc1, Location loc2) {
        int loc1x = loc1.getBlockX();
        int loc1y = loc1.getBlockY();
        int loc1z = loc1.getBlockZ();
        int loc2x = loc2.getBlockX();
        int loc2y = loc2.getBlockY();
        int loc2z = loc2.getBlockZ();
        return (int) Math.floor(Math.sqrt(Math.pow(loc1x - loc2x, 2) + Math.pow(loc1y - loc2y, 2) + Math.pow(loc1z - loc2z, 2)));
    }
}
