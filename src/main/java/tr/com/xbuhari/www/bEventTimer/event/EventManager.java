package tr.com.xbuhari.www.bEventTimer.event;

import tr.com.xbuhari.www.bEventTimer.Main;

import java.util.HashMap;

public class EventManager {

    private HashMap<String, Event> events;

    public EventManager() {
        this.events = new HashMap<>();
        this.reloadEvents();
    }

    public void reloadEvents() {
        this.events.clear();
        for (String _key : Main.getPlugin().getConfig().getConfigurationSection("events").getKeys(false)) {
            Event event = new Event(_key);
            this.events.put(event.getTime(), event);
        }
    }

    public HashMap<String, Event> getEvents() {
        return events;
    }
}
