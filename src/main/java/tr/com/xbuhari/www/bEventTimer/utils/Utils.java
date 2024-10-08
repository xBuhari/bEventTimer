package tr.com.xbuhari.www.bEventTimer.utils;

import org.bukkit.ChatColor;
import tr.com.xbuhari.www.bEventTimer.Main;

public class Utils {

    public static String clr(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }

    public static String cnf(String cnf) {
        return clr(Main.getPlugin().getConfig().getString(cnf));
    }


}
