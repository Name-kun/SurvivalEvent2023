package xyz.masa3mc.survivalevent.spot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.masa3mc.survivalevent.SurvivalEvent;
import xyz.masa3mc.survivalevent.pConf.PlayerConf;

import java.util.*;

import static xyz.masa3mc.survivalevent.SurvivalEventListener.back;
import static xyz.masa3mc.survivalevent.SurvivalEventListener.close;

public class SpotGui {

    static SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);

    public static void spotInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§a釣り場一覧 §7| §6§l年末ジャンボ宝釣り");
        for (int i = 0; i < 27; i++) inv.setItem(i, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        for (int i = 27; i < 54; i++) inv.setItem(i, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        for (int i = 0; i < 27; i += 2) inv.setItem(i, spotDescription(i));
        List<Integer> hiddenIndex = new ArrayList<>(Arrays.asList(30, 32, 38, 40, 42, 48, 50));
        List<String> hiddenRegions = plugin.getConf().getStringList("hidden_regions");
        HashMap<String, Integer> regionToIndexMap = new HashMap<>();
        for (int i = 0; i < 7; i++) regionToIndexMap.put(hiddenRegions.get(i), hiddenIndex.get(i));
        PlayerConf playerConf = new PlayerConf(p.getUniqueId());
        regionToIndexMap.keySet().forEach(key -> {
            if (playerConf.getPEachConf().getStringList("unveiled").contains(key))
                inv.setItem(regionToIndexMap.get(key), unveiledRegionIcons(key));
            else inv.setItem(regionToIndexMap.get(key), veiledRegionIcons(key));
        });
        inv.setItem(45, back());
        inv.setItem(53, close());
        p.openInventory(inv);
    }

    private static ItemStack spotDescription(int i) {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        switch (i) {
            case 0:
                meta.setDisplayName("§a魔黒ぺちぺち空港");
                meta.setLore(Collections.singletonList("§7x§8:§62400 §7z§8:§62400"));
                break;
            case 2:
                meta.setDisplayName("§a萱岡湖");
                meta.setLore(Collections.singletonList("§7x§8:§66100 §7z§8:§61850"));
                break;
            case 4:
                meta.setDisplayName("§a大橋");
                meta.setLore(Collections.singletonList("§7x§8:§6930 §7z§8:§6600"));
                break;
            case 6:
                meta.setDisplayName("§aリレア港");
                meta.setLore(Collections.singletonList("§7x§8:§6-11400 §7z§8:§68370"));
                break;
            case 8:
                meta.setDisplayName("§a胡桃湖");
                meta.setLore(Collections.singletonList("§7x§8:§61600 §7z§8:§62950"));
                break;
            case 10:
                meta.setDisplayName("§a佐々湾");
                meta.setLore(Collections.singletonList("§7x§8:§6-1450 §7z§8:§62900"));
                break;
            case 12:
                meta.setDisplayName("§a蒼湊");
                meta.setLore(Collections.singletonList("§7x§8:§6-3050 §7z§8:§63050"));
                break;
            case 14:
                meta.setDisplayName("§aポケ村");
                meta.setLore(Collections.singletonList("§7x§8:§6-1600 §7z§8:§6-600"));
                break;
            case 16:
                meta.setDisplayName("§aこむぎ岬");
                meta.setLore(Collections.singletonList("§7x§8:§6-200 §7z§8:§6700"));
                break;
            case 18:
                meta.setDisplayName("§a日向灯台");
                meta.setLore(Collections.singletonList("§7x§8:§6-1100 §7z§8:§6100"));
                break;
            case 20:
                meta.setDisplayName("§a新津港");
                meta.setLore(Collections.singletonList("§7x§8:§6300 §7z§8:§6-1150"));
                break;
            case 22:
                meta.setDisplayName("§a泊村");
                meta.setLore(Collections.singletonList("§7x§8:§65300 §7z§8:§6-7050"));
                break;
            case 24:
                meta.setDisplayName("§a布佐崎海岸");
                meta.setLore(Collections.singletonList("§7x§8:§62400 §7z§8:§6-1100"));
                break;
            case 26:
                meta.setDisplayName("§a東雲岬");
                meta.setLore(Collections.singletonList("§7x§8:§6-2150 §7z§8:§61000"));
                break;
        }
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack unveiledRegionIcons(String region) {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        HashMap<String, String> regionToLore = new HashMap<>();
        List<String> lore = new ArrayList<>(Arrays.asList("§7x§8:§62450 §7z§8:§62250", "§7x§8:§61780 §7z§8:§61160", "§7x§8:§65150 §7z§8:§6-7000", "§7x§8:§62300 §7z§8:§6-1000",
                "§7x§8:§6-4450 §7z§8:§6400", "§7x§8:§61100 §7z§8:§6-1050", "§7x§8:§6920 §7z§8:§6-170"));
        List<String> regions = plugin.getConf().getStringList("hidden_regions");
        for (int i = 0; i < 7; i++) regionToLore.put(regions.get(i), lore.get(i));
        meta.setDisplayName(regionToName(region));
        meta.setLore(Collections.singletonList(regionToLore.get(region)));
        meta.addEnchant(Enchantment.MENDING, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack veiledRegionIcons(String region) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        HashMap<String, String> regionToLore = new HashMap<>();
        List<String> lore = new ArrayList<>(Arrays.asList("§7池なのに海水魚が住んでいる...？", "§7昔なめくじが住んでいたとされる川...？", "§7最北端の釣り場...？", "§7とてもクリーンな発電施設...？",
                "§7隠された港...？", "§7まさ鯖内で一番高い水場...？", "§7まさ鯖内で一番キレイな川...？"));
        List<String> regions = plugin.getConf().getStringList("hidden_regions");
        for (int i = 0; i < 7; i++) regionToLore.put(regions.get(i), lore.get(i));
        meta.setDisplayName("§c未開放");
        meta.setLore(Collections.singletonList(regionToLore.get(region)));
        meta.addEnchant(Enchantment.MENDING, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static String regionToName(String region) {
        HashMap<String, String> regionToName = new HashMap<>();
        List<String> regionName = new ArrayList<>(Arrays.asList("§e§lまぐろ拠点の裏の池", "§e§lなめくんの家の裏の川", "§e§l白波海岸", "§e§l布佐崎原発", "§e§lはまきほ港",
                "§e§lグランデ・メゾン塵谷屋上", "§e§l小谷川"));
        List<String> regions = plugin.getConf().getStringList("hidden_regions");
        for (int i = 0; i < 7; i++) regionToName.put(regions.get(i), regionName.get(i));
        return regionToName.get(region);
    }
}
