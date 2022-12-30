package xyz.masa3mc.survivalevent;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.masa3mc.survivalevent.delivery.DeliveryGui;
import xyz.masa3mc.survivalevent.pConf.PlayerConf;
import xyz.masa3mc.survivalevent.reward.RewardGui;
import xyz.masa3mc.survivalevent.table.TableListener;

import java.util.*;

import static xyz.masa3mc.survivalevent.fishing.FishingResults.*;
import static xyz.masa3mc.survivalevent.reward.RewardGui.next;
import static xyz.masa3mc.survivalevent.reward.RewardGui.top;
import static xyz.masa3mc.survivalevent.spot.SpotGui.spotInv;

public class SurvivalEventListener implements @Nullable CommandExecutor, @NotNull Listener, @NotNull TabCompleter {

    static SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("event")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("コンソールからは実行できません。");
                    return true;
                }
                Player p = (Player) sender;
                topGui(p);
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("event")) {
                        plugin.createFiles();
                        plugin.putCombination();
                        sender.sendMessage("リロードしたよーん");
                    }
                }
                if (args[0].equalsIgnoreCase("reset")) {
                    if (sender.hasPermission("event")) {
                        plugin.reset();
                    }
                }
                if (args[0].equalsIgnoreCase("npcreset")) {
                    if (sender.hasPermission("event")) {
                        plugin.npcReset();
                        sender.sendMessage("npcリセットされたよーん");
                    }
                }
                if (args[0].equalsIgnoreCase("hdremove")) {
                    if (sender.hasPermission("event")) {
                        DHAPI.removeHologram("event_ranking");
                        sender.sendMessage("ｈｄけしたよーん");
                    }
                }
                if (args[0].equalsIgnoreCase("getitem")) {
                    if (sender.hasPermission("event")) {
                        Player p = (Player) sender;
                        p.getInventory().addItem(mochigome(null), daifuku(null), tsukitate(null), kinako(null), shiratama(null));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("event")) {
            if (args.length == 1)
                if (sender.hasPermission("event")) {
                    tab.add("reload");
                    tab.add("reset");
                    tab.add("npcreset");
                    tab.add("hdremove");
                    tab.add("getitem");
                }
        }
        return tab;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().isSimilar(shiratama(null)) || e.getPlayer().getInventory().getItemInMainHand().isSimilar(mochigome(null)))
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))
                e.setCancelled(true);
    }

    @EventHandler
    public void onThrow(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType().equals(Material.FISHING_ROD))
            if (e.getPlayer().isSneaking()) {
                topGui(e.getPlayer());
                e.setCancelled(true);
            }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        PlayerConf playerConf = new PlayerConf(e.getPlayer().getUniqueId());
        if (!playerConf.getPEachConf().isSet("name"))
            playerConf.getPEachConf().set("name", e.getPlayer().getDisplayName());
    }

    public static void addPlayerPoint(Player p, int delta) {
        int original = plugin.getPConf().getInt("point." + p.getUniqueId());
        plugin.getPConf().set("point." + p.getUniqueId(), original + delta);
        PlayerConf playerConf = new PlayerConf(p.getUniqueId());
        if (!playerConf.getPEachConf().contains(("rewardCount"))) playerConf.getPEachConf().set("rewardCount", 0);
        RewardGui rewardGui = new RewardGui();
        try {
            while (rewardGui.points.get(playerConf.getPEachConf().getInt("rewardCount")) <= original + delta) {
                ItemStack rewardedItem = rewardGui.itemList.get(playerConf.getPEachConf().getInt("rewardCount"));
                String itemName = rewardedItem.getItemMeta().hasDisplayName() ? rewardedItem.getItemMeta().getDisplayName() : rewardedItem.getType().toString();
                String amount = rewardedItem.getType().equals(Material.DIAMOND) || rewardedItem.getType().equals(Material.NETHERITE_INGOT) ? "§6" + rewardedItem.getAmount() + "§6個" : "";
                int rewardedPoint = rewardGui.points.get(playerConf.getPEachConf().getInt("rewardCount"));
                p.sendMessage("§7[§6" + rewardedPoint + "§6P§7]§aポイント景品として" + itemName + amount + "§aを入手しました！");
                if (p.getInventory().firstEmpty() == -1) {
                    p.getWorld().dropItem(p.getLocation(), rewardedItem);
                } else p.getInventory().addItem(rewardedItem);
                playerConf.getPEachConf().set("rewardCount", playerConf.getPEachConf().getInt("rewardCount") + 1);
            }
            playerConf.savePEachConf();
        } catch (IndexOutOfBoundsException e) {
            p.sendMessage("§c既に納品を完了しています！");
        }
        if (original < 100000 && 100000 <= original + delta) p.sendMessage("§7[§6イベント§7]§e§lすべてのイベント景品を獲得しました！");
        plugin.savePConf();
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§a" + (original + delta) + " §7(+" + delta + "§7)"));
    }

    private void topGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§aトップ画面 §7| §6§l年末ジャンボ宝釣り");
        for (int i = 0; i < 27; i++) inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        if (!plugin.getPConf().contains("point." + p.getUniqueId()))
            plugin.getPConf().set("point." + p.getUniqueId(), 0);
        int point = plugin.getPConf().getInt("point." + p.getUniqueId());
        inv.setItem(10, delivery());
        inv.setItem(12, evPoint(point));
        inv.setItem(14, result(p));
        inv.setItem(16, spot());
        inv.setItem(26, close());
        p.openInventory(inv);
    }

    private void resultGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§aあなたの釣果 §7| §6§l年末ジャンボ宝釣り");
        for (int i = 0; i < 53; i++) inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        for (int i = 0; i < 9; i++) inv.setItem(i, new ItemStack(Material.PINK_STAINED_GLASS_PANE));
        for (int i = 19; i < 26; i++) inv.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        for (int i = 36; i < 45; i++) inv.setItem(i, new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        inv.setItem(2, instant(1, p));
        inv.setItem(4, instant(4, p));
        inv.setItem(6, instant(7, p));
        inv.setItem(18, mochigome(p));
        inv.setItem(20, shiratama(p));
        inv.setItem(22, kinako(p));
        inv.setItem(24, daifuku(p));
        inv.setItem(26, tsukitate(p));
        inv.setItem(38, gacha(1, p));
        inv.setItem(40, gacha(5, p));
        inv.setItem(42, gacha(10, p));
        inv.setItem(45, back());
        inv.setItem(53, close());
        p.openInventory(inv);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            Player p = (Player) e.getWhoClicked();
            if (e.getView().getTitle().equalsIgnoreCase("§aトップ画面 §7| §6§l年末ジャンボ宝釣り")) {
                int point = plugin.getPConf().getInt("point." + p.getUniqueId());
                if (e.getCurrentItem().equals(evPoint(point))) p.openInventory(new RewardGui().rewardInv(1, p));
                if (e.getCurrentItem().equals(result(p))) resultGui(p);
                if (e.getCurrentItem().equals(spot())) spotInv(p);
                if (e.getCurrentItem().equals(close())) p.closeInventory();
                if (e.getCurrentItem().equals(delivery())) new DeliveryGui().deliveryGui(p);
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equalsIgnoreCase("§aあなたの釣果 §7| §6§l年末ジャンボ宝釣り")) {
                if (e.getCurrentItem().equals(back())) topGui(p);
                if (e.getCurrentItem().equals(close())) p.closeInventory();
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equalsIgnoreCase("§a今日の納品場所 §7| §6§l年末ジャンボ宝釣り")) {
                if (e.getCurrentItem().equals(back())) topGui(p);
                if (e.getCurrentItem().equals(close())) p.closeInventory();
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equalsIgnoreCase("§a釣り場一覧 §7| §6§l年末ジャンボ宝釣り")) {
                if (e.getCurrentItem().equals(back())) topGui(p);
                if (e.getCurrentItem().equals(close())) p.closeInventory();
                e.setCancelled(true);
            }
            if (e.getView().getTitle().contains("§aイベント景品")) {
                String page = e.getView().getTitle().replaceAll("[^\\d]", "");
                StringBuilder sb = new StringBuilder();
                sb.append(page);
                sb.delete(page.length() - 2, page.length());
                int pa = Integer.parseInt(sb.toString());
                if (e.getCurrentItem().equals(next())) p.openInventory(new RewardGui().rewardInv(pa + 1, p));
                if (e.getCurrentItem().equals(RewardGui.back())) p.openInventory(new RewardGui().rewardInv(pa - 1, p));
                if (e.getCurrentItem().equals(top())) topGui(p);
                e.setCancelled(true);
            }
        }
    }

    private ItemStack evPoint(int point) {
        ItemStack item = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§lイベントポイント");
        meta.setLore(Arrays.asList("§a所持ポイント§7: §6" + point + "pt", "§a次報酬までのポイント数§7: §6" + diffToNextReward(point) + "pt"));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack result(Player p) {
        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lあなたの釣果");
        meta.setLore(Arrays.asList("§7イベント限定アイテムの釣果が確認できます", "§aイベント釣果§7: §6" + fishedAmount(p)));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack spot() {
        ItemStack item = new ItemStack(Material.COD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e§l釣り場一覧");
        meta.setLore(Collections.singletonList("§7公開釣り場・発見した釣り場の一覧が確認できます"));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack delivery() {
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§d本日の納品場所");
        meta.setLore(Collections.singletonList("§7本日のイベントアイテム納品場所が確認できます"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack close() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c閉じる");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack back() {
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b戻る");
        item.setItemMeta(meta);
        return item;
    }

    private Integer diffToNextReward(int point) {
        TreeSet<Integer> rewPoints = new TreeSet<>();
        rewPoints.addAll(Arrays.asList(200, 400, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 4400, 4600, 4800, 5000, 6000, 7000, 8000, 9000, 10000, 12000, 14000, 16000, 18000, 20230, 22500));
        for (int i = 25000; i < 100000; i += 2500) rewPoints.add(i);
        return point > 100000 ? 0 : rewPoints.ceiling(point) - point;
    }

    private int fishedAmount(Player p) {
        PlayerConf playerConf = new PlayerConf(p.getUniqueId());
        int amount = 0;
        Collection<String> itemKeys = new ArrayList<>(new TableListener().itemToKeyMap.values());
        for (String key : itemKeys) {
            if (playerConf.getPEachConf().contains(key))
                amount += playerConf.getPEachConf().getInt(key);
        }
        return amount;
    }

    private static TreeMap<Integer, String> ranking() {
        TreeMap<Integer, String> playerPointMap = new TreeMap<>();
        plugin.getPConf().getConfigurationSection("point").getKeys(false).forEach(value ->
                playerPointMap.put(plugin.getPConf().getInt("point." + value), Bukkit.getOfflinePlayer(UUID.fromString(value)).getName()));
        return playerPointMap;
    }

    private static void changeHologram() {
        TreeMap<Integer, String> map = ranking();
        List<Integer> points = new ArrayList<>(map.keySet());
        List<String> players = new ArrayList<>(map.values());
        List<String> rankList = new ArrayList<>();
        rankList.add("§c§m-§6§m-§e§m-§a§m-§b§m-§3§l ポイントランキング §b§m-§a§m-§e§m-§6§m-§c§m-");
        for (int i = 0; points.size() >= 10 ? i < 10 : i < points.size(); i++)
            rankList.add("§6§l" + (i + 1) + "§7.§a" + players.get(map.size() - (1 + i)) + "§7(§e" + points.get(map.size() - (1 + i)) + "§7)");
        if (DHAPI.getHologram("event_ranking") != null) {
            Hologram hologram = DHAPI.getHologram("event_ranking");
            DHAPI.setHologramLines(hologram, rankList);
        } else {
            int x = plugin.getConf().getInt("hologram.X");
            int y = plugin.getConf().getInt("hologram.Y");
            int z = plugin.getConf().getInt("hologram.Z");
            World world = Bukkit.getWorld(plugin.getConf().getString("hologramWorld"));
            Location loc = new Location(world, x, y, z);
            DHAPI.createHologram("event_ranking", loc, rankList);
        }
    }

    public static void hologramUpdater() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                changeHologram();
            }
        };
        task.runTaskTimer(plugin, 0L, 200L);
    }

    //順位, プレイヤー, ポイントを表示させたい
    //自分の順位も取得できたら一層神
}
