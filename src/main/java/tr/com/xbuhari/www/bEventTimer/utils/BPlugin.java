package tr.com.xbuhari.www.bEventTimer.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tr.com.xbuhari.www.bEventTimer.Main;
import tr.com.xbuhari.www.bEventTimer.cmds.BETCmd;
import tr.com.xbuhari.www.bEventTimer.event.BossbarManager;
import tr.com.xbuhari.www.bEventTimer.event.EventManager;
import tr.com.xbuhari.www.bEventTimer.event.TimeManager;
import tr.com.xbuhari.www.bEventTimer.papi.EventEXP;

import java.util.HashMap;

public class BPlugin extends JavaPlugin {
    private EventManager eventManager;
    private TimeManager timeManager;

    private BossbarManager bossbarManager;

    private EventEXP eventEXP;
    public void load() {
        this.eventManager = new EventManager();
        this.timeManager = new TimeManager();
        bossbarManager = new BossbarManager();
        getServer().getPluginManager().registerEvents(bossbarManager, this);
        this.eventEXP = new EventEXP();
        this.eventEXP.register();
        metrics();
        getCommand("bet").setExecutor(new BETCmd());
    }
    private void metrics() {
        int pluginId = 23532;
        Metrics metrics = new Metrics(this, pluginId);
    }
    public void unload() {
        bossbarManager.removeBar();
        PlaceholderAPI.unregisterExpansion(this.eventEXP);
    }


    public EventManager getEventManager() {
        return eventManager;
    }

    public BossbarManager getBossbarManager() {
        return bossbarManager;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }
}
