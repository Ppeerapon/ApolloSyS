package Ppeerapon.UNUSE;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TP extends JavaPlugin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("tp")) { // TP
            Player target = getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            if (args.length == 0) { //TP TO OTHER
                Location location = target.getLocation();
                player.teleport(location);
                player.sendMessage(ChatColor.GREEN + "You have been teleport to " + target.getName());
                return true;
            }
            Player target2 = getServer().getPlayer(args[1]);
            if (target2 == null) {
                player.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            if (args.length == 1) { // TP OTHERS TO OTHER
                Location location = target2.getLocation();
                target.teleport(location);
                player.sendMessage(ChatColor.GREEN + "" + target + " has been teleport to " + target2);
                return true;
            }
        }
        return true;
    }
}
