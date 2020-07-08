package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class GodCM implements CommandExecutor {

    static Main plugin;
    public GodCM(Main main) {
        plugin = main;
    }

    public static ArrayList<Player> god = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("god")){
            if (player.hasPermission("SyS.God") || player.hasPermission("SyS.*")){
                if (args.length == 0){
                    if (!god.contains(player)){
                        god.add(player);
                        player.sendMessage(ChatColor.GREEN + "GodMode is Enable");
                    } else {
                        god.remove(player);
                        player.sendMessage(ChatColor.RED + "GodMode is Disable");
                    }
                } else if (args.length == 1){
                    Player target = getServer().getPlayer(args[0]);
                    if (!god.contains(target)){
                        god.add(target);
                        player.sendMessage(ChatColor.GREEN + "GodMode is Enable For " + target.getName());
                        target.sendMessage(ChatColor.GREEN + "GodMode is Enable By " + player.getName());
                    } else {
                        god.remove(target);
                        player.sendMessage(ChatColor.RED + "GodMode is Disable For " + target.getName());
                        target.sendMessage(ChatColor.RED + "GodMode is Disable By " + player.getName());
                    }
                }
            } else player.sendMessage(ChatColor.RED + "You don't have permission to do that!");

        }
        return true;
    }
}
