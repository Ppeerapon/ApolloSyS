package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DeadFinger implements CommandExecutor {

    static Main plugin;
    public DeadFinger(Main main) {
        plugin= main;
    }

   // public static ArrayList<Player> dplayer = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("deadfinger")){
            Player p = (Player) commandSender;
            //dplayer.add(p);
        }
        return  true;
    }
}
