package tr.com.xbuhari.www.bEventTimer.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import tr.com.xbuhari.www.bEventTimer.Main;
import tr.com.xbuhari.www.bEventTimer.event.Event;
import tr.com.xbuhari.www.bEventTimer.event.TimeManager;

import java.util.concurrent.TimeUnit;

public class EventEXP extends PlaceholderExpansion {
    public String getIdentifier() {
        return "bet";
    }

    public String getAuthor() {
        return "xBuhari";
    }

    public String getVersion() {
        return "1.0";
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("time")) {
            TimeManager.TimeFormat timeFormat = Main.getPlugin().getTimeManager().getTimeFormat();
            Event event = Main.getPlugin().getEventManager().getEvents().get(timeFormat.getTime());
            if (timeFormat.getMode().equalsIgnoreCase("now")) {
                return clr(event.getTextNow());
            } else {
                return clr(event.getTextTime()).replace("%time%", calculateTimeDifference(timeFormat.getLongTime()));
            }
        }
        return "";
    }

    public static String clr(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }

    public static String cnf(String cnf) {
        return clr(Main.getPlugin().getConfig().getString(cnf));
    }

    public static String calculateTimeDifference(long inputTime) {
        long currentTime = System.currentTimeMillis();
        long difference = inputTime - currentTime;
        long hours = TimeUnit.MILLISECONDS.toHours(difference);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(difference - TimeUnit.HOURS.toMillis(hours));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(difference - TimeUnit.HOURS.toMillis(hours) - TimeUnit.MINUTES.toMillis(minutes));
        FileConfiguration config = Main.getPlugin().getConfig();
        String timeFormat;
        if (hours > 0) {
            timeFormat = config.getString("settings.timeformat.v3");
        } else if (minutes > 0) {
            timeFormat = config.getString("settings.timeformat.v2");
        } else {
            timeFormat = config.getString("settings.timeformat.v1");
        }
        return timeFormat
                .replace("%h%", String.valueOf(hours))
                .replace("%m%", String.valueOf(minutes))
                .replace("%s%", String.valueOf(seconds));
    }
}
