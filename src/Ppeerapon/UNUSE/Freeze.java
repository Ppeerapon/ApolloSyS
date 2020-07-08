package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.MainCommands;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Freeze implements Listener {

    @EventHandler
    public void onMovement(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (MainCommands.freezelist.contains(p)){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You have been freeze, So you can't move right now");
        }
    }

}
