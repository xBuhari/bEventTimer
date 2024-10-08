package tr.com.xbuhari.www.bEventTimer.event;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tr.com.xbuhari.www.bEventTimer.Main;
import tr.com.xbuhari.www.bEventTimer.papi.EventEXP;

public class BossbarManager implements Listener {
    private BossBar bossBar;

    public BossbarManager() {
        setBossBar();
    }

    public void removeBar() {
        if (bossBar == null) {
            return;
        }
        bossBar.removeAll();
        bossBar = null;
    }

    public void setBossBar() {
        if (!Main.getPlugin().getConfig().getBoolean("settings.bossbar.status", true)) {
            return;
        }
        bossBar = Bukkit.createBossBar("Loading...", BarColor.valueOf(EventEXP.cnf("settings.bossbar.colorTime")), BarStyle.valueOf(EventEXP.cnf("settings.bossbar.style")));
        bossBar.setVisible(true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (bossBar.getPlayers().contains(player)) {
                continue;
            }
            bossBar.addPlayer(player);
        }
    }

    public static double calculateProximityRatio(long v1, long v2, long v3) {
        if (v2 < v1 || v2 > v3) {
            throw new IllegalArgumentException("v2 değeri v1 ile v3 arasında olmalıdır.");
        }
        long totalDistance = v3 - v1;
        long distanceFromV1 = v2 - v1;
        double ratio = (double) distanceFromV1 / totalDistance;
        return ratio;
    }
    public void updateBossBar(long kucuk, long inputTime) {
        if (!Main.getPlugin().getConfig().getBoolean("settings.bossbar.status", true)) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        long remainingTime = inputTime - currentTime;
        bossBar.setTitle(PlaceholderAPI.setPlaceholders(null, "%bet_time%"));

        if (remainingTime > 0) {
            bossBar.setProgress(calculateProximityRatio(kucuk, currentTime, inputTime));
            bossBar.setColor(BarColor.valueOf(EventEXP.cnf("settings.bossbar.colorTime")));
        } else {
            bossBar.setProgress(1);
            bossBar.setColor(BarColor.valueOf(EventEXP.cnf("settings.bossbar.colorNow")));
        }
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e) {
        if (!Main.getPlugin().getConfig().getBoolean("settings.bossbar.status", true)) {
            return;
        }
        if (bossBar.getPlayers().contains(e.getPlayer())) {
            return;
        }
        bossBar.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!Main.getPlugin().getConfig().getBoolean("settings.bossbar.status", true)) {
            return;
        }
        if (!bossBar.getPlayers().contains(e.getPlayer())) {
            return;
        }
        bossBar.removePlayer(e.getPlayer());
    }
}
