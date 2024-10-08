package tr.com.xbuhari.www.bEventTimer.event;

import org.bukkit.Bukkit;
import tr.com.xbuhari.www.bEventTimer.Main;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class TimeManager {
    private TimeFormat timeFormat;

    private long lastChange;
    private HashSet<String> cmds;

    public TimeManager() {
        lastChange = System.currentTimeMillis();
        this.cmds = new HashSet<>();
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(), () -> {timeFormat = getTime();
            if (this.timeFormat.mode.equalsIgnoreCase("now")) {
                lastChange = System.currentTimeMillis();
                String time = timeFormat.time;
                if (!cmds.contains(time)) {
                    cmds.add(time);
                    Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
                        for (String _cmd : Main.getPlugin().getEventManager().getEvents().get(time).getCmds()) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), _cmd);
                        }
                    });
                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> cmds.remove(time), 20L*60*2);
                }
            }
            if (Main.getPlugin().getConfig().getBoolean("settings.bossbar.status", true)) {
                Main.getPlugin().getBossbarManager().updateBossBar(lastChange, timeFormat.longTime);
            }
        }, 20, 20);
    }


    private TimeFormat getTime() {
        List<String> hours = new ArrayList<>(Main.getPlugin().getEventManager().getEvents().keySet());
        return time(hours);
    }

    public TimeFormat getTimeFormat() {
        return timeFormat;
    }

    public static LocalDateTime findClosestLocalDateTime(LocalDateTime referenceTime, Set<LocalDateTime> times) {
        LocalDateTime closestTime = null;
        Duration closestDuration = null;

        for (LocalDateTime time : times) {
            Duration duration = Duration.between(referenceTime, time).abs();

            if (closestTime == null || duration.compareTo(closestDuration) < 0) {
                closestTime = time;
                closestDuration = duration;
            }
        }

        return closestTime;
    }

    public static class TimeFormat {
        private String mode;
        private String time;
        private long longTime;

        public TimeFormat(String mode, String time, long longTime) {
            this.mode = mode;
            this.time = time;
            this.longTime = longTime;

        }

        public String getMode() {
            return mode;
        }

        public String getTime() {
            return time;
        }

        public long getLongTime() {
            return longTime;
        }

        @Override
        public String toString() {
            return "TimeFormat{" +
                    "mode='" + mode + '\'' +
                    ", time='" + time + '\'' +
                    ", longTime=" + longTime +
                    '}';
        }
    }

    public static TimeFormat time(List<String> dateList) {
        LocalDateTime now = LocalDateTime.now();
        HashMap<LocalDateTime, String> before = new HashMap<>();
        HashMap<LocalDateTime, String> nowtime = new HashMap<>();
        for (String time : dateList) {
            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            LocalDateTime modifiedDateTime = now.withHour(hour).withMinute(minute);
            if (now.isBefore(modifiedDateTime)) {
                before.put(modifiedDateTime, time);
                continue;
            }
            if (now.isEqual(modifiedDateTime)) {
                nowtime.put(modifiedDateTime, time);
            }
        }
        if (!nowtime.isEmpty()) {
            return new TimeFormat("now", nowtime.values().stream().findAny().get(), Timestamp.valueOf(nowtime.keySet().stream().findAny().get()).getTime());
        }
        if (before.isEmpty()) {
            now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).plus(Duration.ofMillis(86400000L));
            for (String time : dateList) {
                int hour = Integer.parseInt(time.split(":")[0]);
                int minute = Integer.parseInt(time.split(":")[1]);
                LocalDateTime modifiedDateTime = now.withHour(hour).withMinute(minute);
                if (now.isBefore(modifiedDateTime)) {
                    before.put(modifiedDateTime, time);
                    continue;
                }
                if (now.isEqual(modifiedDateTime)) {
                    nowtime.put(modifiedDateTime, time);
                }
            }
            if (!nowtime.isEmpty()) {
                return new TimeFormat("now", nowtime.values().stream().findAny().get(), Timestamp.valueOf(nowtime.keySet().stream().findAny().get()).getTime());
            }
            LocalDateTime diff = findClosestLocalDateTime(LocalDateTime.now(), before.keySet());
            return new TimeFormat("before", before.get(diff), Timestamp.valueOf(diff.withSecond(0)).getTime());
        }
        else {
            LocalDateTime diff = findClosestLocalDateTime(LocalDateTime.now(), before.keySet());
            return new TimeFormat("before", before.get(diff), Timestamp.valueOf(diff.withSecond(0)).getTime());
        }
    }
}
