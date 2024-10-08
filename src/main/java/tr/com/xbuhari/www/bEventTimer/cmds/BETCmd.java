package tr.com.xbuhari.www.bEventTimer.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tr.com.xbuhari.www.bEventTimer.Main;
import tr.com.xbuhari.www.bEventTimer.utils.Utils;

public class BETCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!commandSender.hasPermission("bet.admin")) {
            commandSender.sendMessage(Utils.cnf("lang.perm_error"));
            return false;
        }
        if (args.length == 0) {
            commandSender.sendMessage(Utils.clr("&cBilinmeyen komut. Yardım için &f/bet help"));
            return false;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage(Utils.clr("&6BEventTimer Yardım Komutları"));
                commandSender.sendMessage(Utils.clr("&6"));
                commandSender.sendMessage(Utils.clr("&e/bet help &8» &fEklentinin komutları hakkında bilgi verir."));
                commandSender.sendMessage(Utils.clr("&e/bet reload &8» &fEklentiyi yeniden yükler."));
                commandSender.sendMessage(Utils.clr("&6"));
                return false;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                Main.getPlugin().reload();
                commandSender.sendMessage(Utils.clr("&cEklenti yeniden yüklendi."));
                return false;
            }
            commandSender.sendMessage(Utils.clr("&cBilinmeyen komut. Yardım için &f/bet help"));
            return false;
        }
        commandSender.sendMessage(Utils.clr("&cBilinmeyen komut. Yardım için &f/bet help"));
        return false;
    }
}
