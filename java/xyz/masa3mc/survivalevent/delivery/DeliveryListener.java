package xyz.masa3mc.survivalevent.delivery;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.masa3mc.survivalevent.SurvivalEvent;

import java.util.HashMap;

import static xyz.masa3mc.survivalevent.SurvivalEventListener.addPlayerPoint;
import static xyz.masa3mc.survivalevent.fishing.FishingResults.*;

public class DeliveryListener implements @NotNull Listener {

    SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);
    private final HashMap<Material, ItemStack> bootToItemMap = new HashMap<>();

    public DeliveryListener() {
        bootToItemMap.put(Material.NETHERITE_BOOTS, mochigome(null));
        bootToItemMap.put(Material.DIAMOND_BOOTS, shiratama(null));
        bootToItemMap.put(Material.GOLDEN_BOOTS, kinako(null));
        bootToItemMap.put(Material.CHAINMAIL_BOOTS, daifuku(null));
        bootToItemMap.put(Material.LEATHER_BOOTS, tsukitate(null));
    }


    @EventHandler
    public void onNPCClick(NPCRightClickEvent e) {
        if (plugin.getConf().getIntegerList("npcId").contains(e.getNPC().getId())) {
            Material npcBoot = e.getNPC().getOrAddTrait(Equipment.class).get(Equipment.EquipmentSlot.BOOTS).getType();
            Player p = e.getClicker();
            ItemStack item = bootToItemMap.get(npcBoot);
            if (p.getInventory().getItemInMainHand().isSimilar(item)) {
                delivery(p, item, getAmountAndRemove(p, item));
            }
        }
    }

    private int getAmountAndRemove(Player p, ItemStack item) {
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = p.getInventory().getItem(i);
            if (slot != null && slot.isSimilar(item)) {
                amount += slot.getAmount();
                p.getInventory().setItem(i, new ItemStack(Material.AIR));
            }
        }
        return amount;
    }

    public void delivery(Player p, ItemStack item, int itemAmount) {
        int pointAmount = 0;
        if (item.isSimilar(mochigome(null)))
            pointAmount = itemAmount * 70;
        if (item.isSimilar(shiratama(null)))
            pointAmount = itemAmount * 90;
        if (item.isSimilar(kinako(null)))
            pointAmount = itemAmount * 100;
        if (item.isSimilar(daifuku(null)))
            pointAmount = itemAmount * 120;
        if (item.isSimilar(tsukitate(null)))
            pointAmount = itemAmount * 130;
        addPlayerPoint(p, pointAmount);
        String itemName = item.getItemMeta().getDisplayName();
        p.sendMessage("§7[§6イベント§7]" + itemName + "§7を§a" + itemAmount + "§7個納品したよ！");
    }
}
