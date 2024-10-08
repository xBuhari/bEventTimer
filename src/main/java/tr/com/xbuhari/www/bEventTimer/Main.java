package tr.com.xbuhari.www.bEventTimer;


import tr.com.xbuhari.www.bEventTimer.utils.BPlugin;

public class Main extends BPlugin {
    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        load();
    }

    public void reload() {
        saveDefaultConfig();
        reloadConfig();
        getEventManager().reloadEvents();
        getBossbarManager().removeBar();
        getBossbarManager().setBossBar();
    }

    @Override
    public void onDisable() {
        unload();
    }



    public static Main getPlugin() {
        return plugin;
    }
}
