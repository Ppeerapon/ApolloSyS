package Ppeerapon.UNUSE;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Moving implements Listener {

    @EventHandler
    public void moving(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Block b = p.getLocation().getBlock();

        b.setType(Material.GOLD_BLOCK);
    }
}
