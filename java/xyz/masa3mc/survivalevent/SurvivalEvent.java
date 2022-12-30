package xyz.masa3mc.survivalevent;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.masa3mc.survivalevent.delivery.DeliveryListener;
import xyz.masa3mc.survivalevent.fishing.FishingListener;
import xyz.masa3mc.survivalevent.pConf.PlayerConf;
import xyz.masa3mc.survivalevent.reward.RewardListener;
import xyz.masa3mc.survivalevent.spot.SpotListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static xyz.masa3mc.survivalevent.SurvivalEventListener.hologramUpdater;

public final class SurvivalEvent extends JavaPlugin {

    public File configFile;
    public FileConfiguration config;
    public File pFile;
    public FileConfiguration pConf;
    final List<ItemStack> head;
    final List<ItemStack> boots;
    final HashMap<Material, String> bootsToName;

    public SurvivalEvent() {
        head = new ArrayList<>(Arrays.asList(new ItemStack(Material.DROPPER), new ItemStack(Material.END_ROD),
                new ItemStack(Material.DISPENSER), new ItemStack(Material.TWISTING_VINES), new ItemStack(Material.OBSERVER)));
        boots = new ArrayList<>(Arrays.asList(new ItemStack(Material.NETHERITE_BOOTS), new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.GOLDEN_BOOTS), new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.LEATHER_BOOTS)));
        bootsToName = new HashMap<>();
        bootsToName.put(Material.NETHERITE_BOOTS, "§7§lもち米くん");
        bootsToName.put(Material.DIAMOND_BOOTS, "§3§lシラタマちゃん");
        bootsToName.put(Material.GOLDEN_BOOTS, "§a§lきな粉お兄さん");
        bootsToName.put(Material.CHAINMAIL_BOOTS, "§e§l大福パパ");
        bootsToName.put(Material.LEATHER_BOOTS, "§6§lつきたて母さん");
    }

    @Override
    public void onEnable() {
        SurvivalEventListener survivalEventListener = new SurvivalEventListener();
        getCommand("event").setExecutor(survivalEventListener);
        getCommand("event").setTabCompleter(survivalEventListener);
        getServer().getPluginManager().registerEvents(survivalEventListener, this);
        getServer().getPluginManager().registerEvents(new FishingListener(), this);
        getServer().getPluginManager().registerEvents(new DeliveryListener(), this);
        getServer().getPluginManager().registerEvents(new RewardListener(), this);
        getServer().getPluginManager().registerEvents(new SpotListener(), this);
        createFiles();
        putPConf();
        scheduler();
        hologramUpdater();
        putCombination();
    }

    private void putPConf() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            PlayerConf playerConf = new PlayerConf(p.getUniqueId());
            if (!playerConf.getPEachConf().isSet("name"))
                playerConf.getPEachConf().set("name", p.getPlayer().getDisplayName());
        });
    }

    @Override
    public void onDisable() {
    }

    public void createFiles() {
        configFile = new File(this.getDataFolder(), "config.yml");
        pFile = new File(this.getDataFolder(), "players.yml");

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            this.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        if (!pFile.exists()) {
            configFile.getParentFile().mkdirs();
            this.saveResource("players.yml", false);
        }
        pConf = new YamlConfiguration();

        try {
            config.load(configFile);
            pConf.load(pFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConf() {
        return config;
    }

    public FileConfiguration getPConf() {
        return pConf;
    }

    public void saveConf() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePConf() {
        try {
            pConf.save(pFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scheduler() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                Date now = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                if (simpleDateFormat.format(now).equals(getConf().getString("time"))) {
                    reset();
                }
                new RewardListener().loop();
            }
        };
        task.runTaskTimer(this, 0L, 20L);
    }

    public void reset() {
        List<String> regions = getConf().getStringList("regions");
        List<String> tableNames = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        HashMap<String, List<String>> tableRegionMap = new HashMap<>();
        for (String tableName : tableNames) {
            List<String> list = new ArrayList<>();
            tableRegionMap.put(tableName, list);
        }
        //tableXにregionを追加する
        for (String region : regions) {
            Collections.shuffle(tableNames);
            List<String> originalRegions = tableRegionMap.get(tableNames.get(0));
            originalRegions.add(region);
            tableRegionMap.put(tableNames.get(0), originalRegions);
        }
        for (String tableName : tableNames) {
            getConf().set("table" + tableName, tableRegionMap.get(tableName));
        }
        //各regionにアイテムを抽選
        List<String> deliveryName = new ArrayList<>(Arrays.asList("mochigome", "shiratama", "kinako", "daifuku", "tsukitate"));
        for (String table : tableNames) {
            List<String> list = new ArrayList<>();
            Collections.shuffle(deliveryName);
            if (table.equals("A"))
                for (int i = 0; i < 2; i++)
                    list.add(deliveryName.get(i));
            if (table.equals("B") || table.equals("C"))
                for (int i = 0; i < 3; i++)
                    list.add(deliveryName.get(i));
            if (table.equals("D") || table.equals("E"))
                for (int i = 0; i < 4; i++)
                    list.add(deliveryName.get(i));
            if (table.equals("F"))
                for (int i = 0; i < 5; i++)
                    list.add(deliveryName.get(i));
            getConf().set("table" + table + "Item", list);
        }
        //隠し釣り場の効果を抽選
        List<String> hiddenRegions = getConf().getStringList("hidden_regions");
        List<String> effects = new ArrayList<>(Arrays.asList("doubleDrop", "doubleTimes", "upGraded"));
        HashMap<String, List<String>> effectRegionMap = new HashMap<>();
        for (String effect : effects) {
            List<String> list = new ArrayList<>();
            effectRegionMap.put(effect, list);
        }
        for (String region : hiddenRegions) {
            Collections.shuffle(effects);
            List<String> original = effectRegionMap.get(effects.get(0));
            original.add(region);
            effectRegionMap.put(effects.get(0), original);
        }
        for (String effect : effects)
            getConf().set(effect, effectRegionMap.get(effect));
        saveConf();
        putCombination();
        npcReset();
        Bukkit.broadcastMessage("§7[§3イベント§7]§6§l釣り場が更新されました！");
    }

    public void putCombination() {
        List<String> tableName = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        //hashmapに代入
        tableName.forEach(name -> {
            List<String> regionList = getConf().getStringList("table" + name);
            if (!regionList.isEmpty())
                regionList.forEach(eachRegion -> FishingListener.regionToTable.put(eachRegion, name));
        });
    }

    public void npcReset() {
        //指定したIDのnpcを削除
        getConf().getIntegerList("npcId").forEach(id -> {
            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null) npc.destroy();
        });
        //装備付きnpc配置
        List<String> locKeys = new ArrayList<>(getConf().getConfigurationSection("npc").getKeys(false));
        List<Integer> idList = new ArrayList<>();
        Collections.shuffle(locKeys);
        for (int i = 0; i < 5; i++) {
            int x = getConf().getInt("npc." + locKeys.get(i) + ".X");
            int y = getConf().getInt("npc." + locKeys.get(i) + ".Y");
            int z = getConf().getInt("npc." + locKeys.get(i) + ".Z");
            Location loc = new Location(getServer().getWorld(getConf().getString("npcWorld")), x, y, z);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "a");
            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, head.get(i));
            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boots.get(i));
            npc.setName(bootsToName.get(boots.get(i).getType()));
            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            skinTrait.setSkinName("NameKun");
            npc.spawn(loc);
            //npcのIDをListに登録
            idList.add(npc.getId());
        }
        getConf().set("npcId", idList);
        //セーブが後々重複しそうなので何とかしといてください
        saveConf();
    }

    //resetと同時にコンフィグに登録されてあるnpcを削除して、抽選されたコンフィグ上の座標を元にnpcを配置し、IDをコンフィグに再登録する
    //納品アイテムの種類は頭(または足)の装備によって判別、納品が完了するとポイントが入手できるようにする 納品回数の記帳あり


}
