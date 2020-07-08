package Ppeerapon.ApolloSyS;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class Chat implements Listener {

    public static ArrayList<Player> dplayer = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer(); // GET PLAYER
        String text = e.getMessage(); // GET MESSAGE

        if (MainCommands.mutelist.contains(p)){ // MUTE PLAYER
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You can't talk now!");
        }

        String msg = ChatColor.translateAlternateColorCodes('&',text); // COLOR MESSAGE
        e.setMessage(msg);

        if (e.getMessage().equalsIgnoreCase("Yorn")){
            p.sendMessage(ChatColor.GOLD+"Feel The Sun");
        }

        if (e.getMessage().equalsIgnoreCase("Dead Finger")){
            dplayer.add(p);
            p.sendMessage(ChatColor.GREEN + "Dead Finger Ready!");
        }

        if (e.getMessage().equalsIgnoreCase("Test")){
            p.sendMessage(String.valueOf(MainCommands.vanishlist));
        }
    }
}
