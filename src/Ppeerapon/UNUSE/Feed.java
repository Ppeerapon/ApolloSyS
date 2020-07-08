package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class Feed implements CommandExecutor {

    static Main plugin;
    public Feed(Main main) {
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("feed")) { // FEED
            if (args.length == 0) {
                player.setFoodLevel(20);
                player.sendMessage(ChatColor.GREEN + "You have been feed");
            } else if (args.length == 1) {
                Player target = getServer().getPlayer(args[0]);
                assert target != null;
                target.setFoodLevel(20);
                target.sendMessage(ChatColor.GREEN + "You have been feed by " + player.getName());
            } else player.sendMessage(ChatColor.RED + "Something went wrong");
        }
    return true;
    }
}
