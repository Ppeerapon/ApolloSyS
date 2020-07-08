package Ppeerapon.UNUSE;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Block implements Listener {
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Location playerlocation = event.getPlayer().getLocation();
        Player player = event.getPlayer();
        if (playerlocation.getWorld().getName().equals("spawn4")) {
            if (!(player.isOp())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You can't place any block on this world!");
            }
        }
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Location playerlocation = event.getPlayer().getLocation();
        Player player = event.getPlayer();
        if (playerlocation.getWorld().getName().equals("spawn4")) {
            if (!(player.isOp())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You can't break any block on this world!");
            }
        }
    }
}
