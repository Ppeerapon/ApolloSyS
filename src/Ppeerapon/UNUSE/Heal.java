package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class Heal implements CommandExecutor {

    static Main plugin;
    public Heal(Main main){
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("heal")) { // HEAL
            if (args.length == 0) { //HEAL YOUR SELF
                player.setHealth(20);
                player.sendMessage(ChatColor.GREEN + "You have been heal");
                return true;
            } else if (args.length == 1) { //HEAL OTHER
                Player target = getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Player not found");
                    return true;
                }
                target.setHealth(20);
                target.sendMessage(ChatColor.GREEN + "You have been heal From " + player.getName());
                player.sendMessage(ChatColor.GREEN + "" + target.getName() + " has been heal");
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("healall")){
            Player p = (Player) getServer().getOnlinePlayers();
            p.setHealth(20);
            p.sendMessage(ChatColor.GREEN + "You have been heal From ");
        }
        return true;
    }
}
