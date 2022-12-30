package xyz.masa3mc.survivalevent.spot;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.masa3mc.survivalevent.SurvivalEvent;
import xyz.masa3mc.survivalevent.pConf.PlayerConf;

import java.util.List;

import static xyz.masa3mc.survivalevent.spot.SpotGui.regionToName;

public class SpotListener implements Listener {

    SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);

    @EventHandler
    public void onEnterRegion(RegionEnteredEvent e) {
        if (plugin.getConf().getStringList("hidden_regions").contains(e.getRegionName())) {
            if (e.getPlayer() != null) {
                PlayerConf playerConf = new PlayerConf(e.getPlayer().getUniqueId());
                if (!playerConf.getPEachConf().getStringList("unveiled").contains(e.getRegionName())) {
                    List<String> original = playerConf.getPEachConf().getStringList("unveiled");
                    original.add(e.getRegionName());
                    playerConf.getPEachConf().set("unveiled", original);
                    playerConf.savePEachConf();
                    e.getPlayer().sendMessage("§7[§3イベント§7]§a隠し釣り場" + regionToName(e.getRegionName()) + "§aを開放しました！");
                }
            }
        }
    }
}
