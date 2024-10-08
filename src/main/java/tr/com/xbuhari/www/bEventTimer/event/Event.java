package tr.com.xbuhari.www.bEventTimer.event;

import org.bukkit.configuration.ConfigurationSection;
import tr.com.xbuhari.www.bEventTimer.Main;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String id;

    private String time;

    private String textTime;
    private String textNow;

    private List<String> cmds;

    public Event(String id) {
        this.id = id;
        ConfigurationSection cs = Main.getPlugin().getConfig().getConfigurationSection("events." + id);
        this.time = cs.getString("time");
        this.textTime = cs.getString("textTime");
        this.textNow = cs.getString("textNow");
        this.cmds = new ArrayList<>(cs.getStringList("cmds"));
    }

    public List<String> getCmds() {
        return cmds;
    }

    public String getId() {
        return id;
    }

    public String getTextNow() {
        return textNow;
    }

    public String getTextTime() {
        return textTime;
    }

    public String getTime() {
        return time;
    }
}
