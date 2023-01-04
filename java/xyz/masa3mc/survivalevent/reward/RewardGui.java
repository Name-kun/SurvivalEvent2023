package xyz.masa3mc.survivalevent.reward;

import static xyz.masa3mc.survivalevent.fishing.FishingResults.*;
import static xyz.masa3mc.survivalevent.reward.RewardLists.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.masa3mc.survivalevent.pConf.PlayerConf;

public class RewardGui {

	public List<Integer> points;
	public List<ItemStack> itemList;

	public RewardGui() {
		points = new ArrayList<>(Arrays.asList(200, 400, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600,
				2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 4400, 4600, 4800, 5000, 6000, 7000, 8000, 9000, 10000,
				12000, 14000, 16000, 18000, 20230, 22500));
		for (int i = 25000; i <= 100000; i += 2500)
			points.add(i);
		for (int i = 110000; i <= 300000; i += 10000)
			points.add(i);
		points.add(2000000000);
		ItemStack neIngot = new ItemStack(Material.NETHERITE_INGOT);
		ItemStack neBlock = new ItemStack(Material.NETHERITE_BLOCK);
		itemList = new ArrayList<>(Arrays.asList(new ItemStack(Material.DIAMOND, 8), habutae(), instant(1, null),
				angel(), stickMochi(), mochiIngot(), instant(1, null), boneMochi(), new ItemStack(Material.DIAMOND, 16),
				stringMochi(), new ItemStack(Material.DIAMOND, 32), mochiPotion(),
				new ItemStack(Material.HEART_OF_THE_SEA), mochiFish(), stickMochi(), saltMochi(), instant(4, null),
				liquidMochi(), instant(4, null), stringMochi(), new ItemStack(Material.NETHER_STAR), shiningMochi(),
				instant(7, null), mochiHoe(), stickMochi(), neIngot, neIngot, neIngot, neIngot, neIngot, neIngot,
				neIngot, neIngot, neIngot, happyMochi(), neIngot));
		for (int i = 0; i < 30; i++)
			itemList.add(neIngot);
		for (int i = 0; i < 4; i++)
			itemList.add(neBlock);
		itemList.add(mugenLavaBuckets());
		for (int i = 0; i < 3; i++)
			itemList.add(neBlock);
		itemList.add(spyGlass());
		for (int i = 0; i < 3; i++)
			itemList.add(neBlock);
		itemList.add(tankenSet());
		for (int i = 0; i < 3; i++)
			itemList.add(neBlock);
		itemList.add(mugenSponge(1));
		itemList.add(mugentsukitate());
		itemList.add(fanElytra());
		itemList.add(superPickaxe6());
		itemList.add(boots2023());
		itemList.add(new ItemStack(Material.AIR));
	}

	public Inventory rewardInv(int page, Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§aイベント景品(p." + page + "§a)§7| §6§l年末ジャンボ宝釣り");
		for (int i = 9; i < 18; i++)
			inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
		int firstIndex = page * 9 - 9;
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		PlayerConf playerConf = new PlayerConf(p.getUniqueId());
		int pReward = playerConf.getPEachConf().getInt("rewardCount");
		for (int i = 0; i < 9; i++) {
			if (points.size() - 2 >= firstIndex + i) {
				inv.setItem(i, pReward > firstIndex + i ? itemList.get(firstIndex + i) : veiled());
				meta.setDisplayName("§a" + points.get(firstIndex + i));
				item.setItemMeta(meta);
				inv.setItem(i + 18, item);
			}
		}
		if (page == 1)
			inv.setItem(9, top());
		else
			inv.setItem(9, back());
		if (inv.getItem(8) != null)
			inv.setItem(17, next());
		return inv;
	}

	// ポイント加算時の景品贈与と景品未獲得時の非表示設定を制作してください

	public static ItemStack next() {
		ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§a次へ");
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack back() {
		ItemStack item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§b前へ");
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack top() {
		ItemStack item = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§eトップへ戻る");
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack veiled() {
		ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§c未開放");
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
}
