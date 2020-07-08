package Ppeerapon.ApolloSyS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Prank implements CommandExecutor {

    static Main plugin;
    public Prank(Main main){
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        Player t = Bukkit.getServer().getPlayer(args[0]);
        if (p.hasPermission("SyS.Prank")) {
            if (command.getName().equalsIgnoreCase("fakeop")) {
                if (args.length == 1) {
                    p.sendMessage(ChatColor.GRAY + "[ FAKE ] Server: Made " + t.getName() + "a server operator");
                    t.sendMessage(ChatColor.GRAY + "Server: Made " + t.getName() + "a server operator");
                } else {
                    p.sendMessage("Usage: /fakeop <player>");
                }
            }
        }
        return true;
    }
}
