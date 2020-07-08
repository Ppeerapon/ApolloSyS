package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Interact implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        if (Chat.dplayer.contains(p)){
            e.getRightClicked().remove();
            p.sendMessage(ChatColor.GREEN + "Dead!");
            Chat.dplayer.remove(p);
        }
    }
}
