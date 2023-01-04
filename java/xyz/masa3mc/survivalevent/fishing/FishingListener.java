package xyz.masa3mc.survivalevent.fishing;

import static xyz.masa3mc.survivalevent.SurvivalEventListener.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import xyz.masa3mc.survivalevent.SurvivalEvent;
import xyz.masa3mc.survivalevent.table.TableListener;

public class FishingListener implements @NotNull Listener {

	SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);
	public static HashMap<String, String> regionToTable = new HashMap<>();

	@EventHandler
	public void onFishing(PlayerFishEvent e) {
		// getRegions(e.getPlayer()).forEach(region ->
		// e.getPlayer().sendMessage(region));
		if (e.getCaught() != null) {
			if (getRegions(e.getPlayer()) != null) {
				List<String> pRegions = getRegions(e.getPlayer());
				String pRegion = null;
				for (String region : pRegions) {
					if (plugin.getConf().getStringList("regions").contains(region))
						pRegion = region;
					break;
				}
				Player p = e.getPlayer();
				// プレイヤーがconfigに登録された保護内にいる場合
				if (pRegion != null && e.getPlayer().getWorld().getName()
						.equalsIgnoreCase(plugin.getConf().getString("npcWorld"))) {
					if (e.getExpToDrop() != 0) {
						TableListener tableListener = new TableListener();
						int r = new Random().nextInt(101) + 1;
						// 隠し釣り場の場合
						if (tableListener.isHidden(pRegion)) {
							if (r <= 10) {
								e.getCaught().remove();
								tableByCase(p, pRegion, e.getCaught());
							} else if (r == 11) {
								e.getCaught().remove();
								if (tableListener.isDoubleTimes(pRegion))
									// 確実に宝が釣れます
									tableListener.ensuredTreasure(p, e.getCaught(), pRegion);
								else
									tableByCase(p, pRegion, e.getCaught());
							}
						} else {
							if (r <= 10) {
								e.getCaught().remove();
								tableByCase(p, pRegion, e.getCaught());
							}
						}
					}
				}
			}
		}
	}

	private void tableByCase(Player p, String pRegion, Entity caught) {
		TableListener tableListener = new TableListener();
		List<String> tableItems;
		switch (regionToTable.get(pRegion)) {
		case "A":
			tableItems = plugin.getConf().getStringList("tableAItem");
			tableListener.tableA(p, caught, stringToItem(tableItems), pRegion);
			break;
		case "B":
			tableItems = plugin.getConf().getStringList("tableBItem");
			tableListener.tableB(p, caught, stringToItem(tableItems), pRegion);
			break;
		case "C":
			tableItems = plugin.getConf().getStringList("tableCItem");
			tableListener.tableC(p, caught, stringToItem(tableItems), pRegion);
			break;
		case "D":
			tableItems = plugin.getConf().getStringList("tableDItem");
			tableListener.tableD(p, caught, stringToItem(tableItems), pRegion);
			break;
		case "E":
			tableItems = plugin.getConf().getStringList("tableEItem");
			tableListener.tableE(p, caught, stringToItem(tableItems), pRegion);
			break;
		case "F":
			tableItems = plugin.getConf().getStringList("tableFItem");
			tableListener.tableF(p, caught, stringToItem(tableItems), pRegion);
			break;
		}
		addPlayerPoint(p, 5);
	}

	// configのtableXItemのItemをItemStackの配列に変換して返す
	public List<ItemStack> stringToItem(List<String> itemNames) {
		List<ItemStack> itemStackList = new ArrayList<>();
		for (String name : itemNames) {
			switch (name) {
			case "mochigome":
				itemStackList.add(FishingResults.mochigome(null));
				break;
			case "shiratama":
				itemStackList.add(FishingResults.shiratama(null));
				break;
			case "kinako":
				itemStackList.add(FishingResults.kinako(null));
				break;
			case "daifuku":
				itemStackList.add(FishingResults.daifuku(null));
				break;
			case "tsukitate":
				itemStackList.add(FishingResults.tsukitate(null));
				break;
			}
		}
		return itemStackList;
	}

	public static List<String> getRegions(Player p) {
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(Objects.requireNonNull(p.getWorld())));
		Location loc = p.getLocation();
		ApplicableRegionSet ars;
		List<String> regionNames = new ArrayList<>();
		if (regions != null) {
			ars = regions.getApplicableRegions(BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
			for (ProtectedRegion region : ars.getRegions()) {
				regionNames.add(region.getId());
			}
			return regionNames;
		}
		return null;
	}

}
