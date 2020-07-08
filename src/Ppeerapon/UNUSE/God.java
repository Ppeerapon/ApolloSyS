package Ppeerapon.UNUSE;

import Ppeerapon.ApolloSyS.MainCommands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class God implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent e){
        Player p = (Player) e.getEntity();
        if (MainCommands.godlist.contains(p)){
            e.setCancelled(true);
            p.sendMessage("Test1");
        } else {
            e.setCancelled(false);
            p.sendMessage("Test2");
        }
    }
}
