package xyz.masa3mc.survivalevent.table;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import xyz.masa3mc.survivalevent.pConf.PlayerConf;
import xyz.masa3mc.survivalevent.SurvivalEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static xyz.masa3mc.survivalevent.fishing.FishingResults.*;

public class TableListener {

    SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);
    public final HashMap<ItemStack, String> itemToKeyMap = new HashMap<>();

    public TableListener() {
        itemToKeyMap.put(instant(1, null), "instantMending1");
        itemToKeyMap.put(instant(4, null), "instantMending4");
        itemToKeyMap.put(instant(7, null), "instantMending7");
        itemToKeyMap.put(mochigome(null), "mochigome");
        itemToKeyMap.put(shiratama(null), "shiratama");
        itemToKeyMap.put(kinako(null), "kinako");
        itemToKeyMap.put(daifuku(null), "daifuku");
        itemToKeyMap.put(tsukitate(null), "tsukitate");
        itemToKeyMap.put(gacha(1, null), "gacha1");
        itemToKeyMap.put(gacha(4, null), "gacha5");
        itemToKeyMap.put(gacha(7, null), "gacha10");
    }

    public void tableA(Player p, Entity caught, List<ItemStack> delivered, String region) {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 10) item = randomTreasure();
        else if (random <= 55) item = delivered.get(0);
        else item = delivered.get(1);
        if (isHidden(region)) hiddenRegion(p, item, caught.getLocation(), region);
        else {
            Entity result = caught.getWorld().dropItem(caught.getLocation(), item);
            result.setVelocity(getVector(p.getLocation(), result));
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            playerConf.savePEachConf();
        }
    }

    public void tableB(Player p, Entity caught, List<ItemStack> delivered, String region) {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 10) item = randomTreasure();
        else if (random <= 40) item = delivered.get(0);
        else if (random <= 70) item = delivered.get(1);
        else item = delivered.get(2);
        if (isHidden(region)) hiddenRegion(p, item, caught.getLocation(), region);
        else {
            Entity result = caught.getWorld().dropItem(caught.getLocation(), item);
            result.setVelocity(getVector(p.getLocation(), result));
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            playerConf.savePEachConf();
        }
    }

    public void tableC(Player p, Entity caught, List<ItemStack> delivered, String region) {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 10) item = randomTreasure();
        else if (random <= 55) item = delivered.get(0);
        else if (random <= 85) item = delivered.get(1);
        else item = delivered.get(2);
        if (isHidden(region)) hiddenRegion(p, item, caught.getLocation(), region);
        else {
            Entity result = caught.getWorld().dropItem(caught.getLocation(), item);
            result.setVelocity(getVector(p.getLocation(), result));
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            playerConf.savePEachConf();
        }
    }

    public void tableD(Player p, Entity caught, List<ItemStack> delivered, String region) {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 10) item = randomTreasure();
        else if (random <= 40) item = delivered.get(0);
        else if (random <= 65) item = delivered.get(1);
        else if (random <= 85) item = delivered.get(2);
        else item = delivered.get(3);
        if (isHidden(region)) hiddenRegion(p, item, caught.getLocation(), region);
        else {
            Entity result = caught.getWorld().dropItem(caught.getLocation(), item);
            result.setVelocity(getVector(p.getLocation(), result));
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            playerConf.savePEachConf();
        }
    }

    public void tableE(Player p, Entity caught, List<ItemStack> delivered, String region) {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 10) item = randomTreasure();
        else if (random <= 55) item = delivered.get(0);
        else if (random <= 75) item = delivered.get(1);
        else if (random <= 90) item = delivered.get(2);
        else item = delivered.get(3);
        if (isHidden(region)) hiddenRegion(p, item, caught.getLocation(), region);
        else {
            Entity result = caught.getWorld().dropItem(caught.getLocation(), item);
            result.setVelocity(getVector(p.getLocation(), result));
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            playerConf.savePEachConf();
        }
    }

    public void tableF(Player p, Entity caught, List<ItemStack> delivered, String region) {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 10) item = randomTreasure();
        else if (random <= 28) item = delivered.get(0);
        else if (random <= 46) item = delivered.get(1);
        else if (random <= 64) item = delivered.get(2);
        else if (random <= 82) item = delivered.get(3);
        else item = delivered.get(4);
        if (isHidden(region)) hiddenRegion(p, item, caught.getLocation(), region);
        else {
            Entity result = caught.getWorld().dropItem(caught.getLocation(), item);
            result.setVelocity(getVector(p.getLocation(), result));
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            playerConf.savePEachConf();
        }
    }

    public void ensuredTreasure(Player p, Entity caught, String region) {
        hiddenRegion(p, randomTreasure(), caught.getLocation(), region);
    }

    public void hiddenRegion(Player p, ItemStack returned, Location entityLoc, String region) {
        PlayerConf playerConf = new PlayerConf(p.getUniqueId());
        //なお～るくんとガチャのグレードアップ
        ItemStack item;
        if (plugin.getConf().getStringList("upGraded").contains(region)) {
            if (returned.equals(instant(1, null)))
                item = instant(4, null);
            else if (returned.equals(gacha(1, null)))
                item = gacha(5, null);
            else item = returned;
            Entity result = p.getWorld().dropItem(entityLoc, item);
            result.setVelocity(getVector(p.getLocation(), result));
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
        }
        //25%で二重釣り
        if (plugin.getConf().getStringList("doubleDrop").contains(region)) {
            item = returned;
            int random = new Random().nextInt(101) + 1;
            if (random <= 25) {
                for (int i = 0; i < 2; i++) {
                    Entity result = p.getWorld().dropItem(entityLoc, item);
                    result.setVelocity(getVector(p.getLocation(), result));
                    int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
                    playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
                }
            } else {
                Entity result = p.getWorld().dropItem(entityLoc, item);
                result.setVelocity(getVector(p.getLocation(), result));
                int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
                playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
            }
        }
        if (isDoubleTimes(region)) {
            item = returned;
            Entity result = p.getWorld().dropItem(entityLoc, item);
            result.setVelocity(getVector(p.getLocation(), result));
            int original = playerConf.getPEachConf().getInt(itemToKeyMap.get(item));
            playerConf.getPEachConf().set(itemToKeyMap.get(item), original + 1);
        }
        playerConf.savePEachConf();
    }

    public Boolean isHidden(String region) {
        return plugin.getConf().getStringList("hidden_regions").contains(region);
    }

    public Boolean isDoubleTimes(String region) {
        return plugin.getConf().getStringList("doubleTimes").contains(region);
    }

    public ItemStack randomTreasure() {
        int random = new Random().nextInt(101) + 1;
        ItemStack item;
        if (random <= 4) item = instant(7, null);
        else if (random <= 12) item = instant(4, null);
        else if (random <= 36) item = instant(1, null);
        else if (random <= 42) item = gacha(10, null);
        else if (random <= 64) item = gacha(5, null);
        else item = gacha(1, null);
        return item;
    }

    private org.bukkit.util.Vector getVector(Location owner, Entity entity) {
        double d0 = owner.getX() - entity.getLocation().getX();
        double d1 = owner.getY() - entity.getLocation().getY();
        double d2 = owner.getZ() - entity.getLocation().getZ();
        return new Vector(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
    }
}
