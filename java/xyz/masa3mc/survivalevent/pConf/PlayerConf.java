package xyz.masa3mc.survivalevent.pConf;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.masa3mc.survivalevent.SurvivalEvent;

import java.io.File;
import java.util.UUID;

public class PlayerConf {

    SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);
    UUID uuid;
    File pEachFile;
    FileConfiguration pEachConf;

    public PlayerConf(UUID uuid) {
        this.uuid = uuid;
        pEachFile = new File(plugin.getDataFolder(), "players" + File.separator + uuid + ".yml");
        pEachConf = YamlConfiguration.loadConfiguration(pEachFile);
    }

    public FileConfiguration getPEachConf() {
        return pEachConf;
    }

    public void savePEachConf() {
        try {
            pEachConf.save(pEachFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
