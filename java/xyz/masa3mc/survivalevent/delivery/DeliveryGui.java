package xyz.masa3mc.survivalevent.delivery;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.masa3mc.survivalevent.SurvivalEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static xyz.masa3mc.survivalevent.SurvivalEventListener.back;
import static xyz.masa3mc.survivalevent.SurvivalEventListener.close;
import static xyz.masa3mc.survivalevent.fishing.FishingResults.*;

public class DeliveryGui {

    static SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);
    private static final HashMap<Material, ItemStack> bootToItemMap = new HashMap<>();

    public DeliveryGui() {
        bootToItemMap.put(Material.NETHERITE_BOOTS, mochigome(null));
        bootToItemMap.put(Material.DIAMOND_BOOTS, shiratama(null));
        bootToItemMap.put(Material.GOLDEN_BOOTS, kinako(null));
        bootToItemMap.put(Material.CHAINMAIL_BOOTS, daifuku(null));
        bootToItemMap.put(Material.LEATHER_BOOTS, tsukitate(null));
    }

    public void deliveryGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§a今日の納品場所 §7| §6§l年末ジャンボ宝釣り");
        List<ItemStack> deliveryIcon = deliveryPlaceIcon();
        for (int i = 0; i < 26; i++) inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        int index = 0;
        for (int i = 9; i < 18; i += 2) {
            inv.setItem(i, deliveryIcon.get(index));
            index++;
        }
        inv.setItem(18, back());
        inv.setItem(26, close());
        p.openInventory(inv);
    }

    private List<ItemStack> deliveryPlaceIcon() {
        List<Integer> idList = plugin.getConf().getIntegerList("npcId");
        List<ItemStack> placeIcons = new ArrayList<>();
        for (int id : idList) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            int x = npc.getStoredLocation().getBlockX();
            int y = npc.getStoredLocation().getBlockY();
            int z = npc.getStoredLocation().getBlockZ();
            Material npcBoot = npc.getOrAddTrait(Equipment.class).get(Equipment.EquipmentSlot.BOOTS).getType();
            ItemStack icon = bootToItemMap.get(npcBoot);
            ItemMeta meta = icon.getItemMeta();
            meta.setLore(Collections.singletonList("§7x§8:§6" + x + " §7y§8:§6" + y + " §7z§8:§6" + z));
            icon.setItemMeta(meta);
            placeIcons.add(icon);
        }
        return placeIcons;
    }
}
