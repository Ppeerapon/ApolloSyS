package Ppeerapon.UNUSE;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Ban extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("ban")){
            Player target = getServer().getPlayer(args[0]);
            assert target != null;
            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), args[1], null,""+sender);
            player.sendMessage(ChatColor.GREEN + "" + target.getName() + " has been baned");
        }

        if (command.getName().equalsIgnoreCase("unban")){
            Player target = getServer().getPlayer(args[0]);
            Bukkit.getBannedPlayers().remove(target);
        }

        return true;
    }
}
