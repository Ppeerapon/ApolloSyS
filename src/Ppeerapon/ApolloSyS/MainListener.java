package Ppeerapon.ApolloSyS;

import com.sun.org.apache.xpath.internal.objects.XNumber;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Objects;
import java.util.UUID;

public class MainListener implements Listener {

    int _id = 1; // USER ID

    @EventHandler
    public void onJoin(PlayerJoinEvent e) { // JOIN
        Player p = e.getPlayer();

        for (Player vplayer : MainCommands.vanishlist) { // VANISH
            p.hidePlayer(vplayer);
        }

        String getid = "users." + p.getName() + ".ID"; // PATH
        String id = Main.cfgm.usersscfg.getString(getid); // GET ID
        if (id == null) { // PLAYER HAS ID?
            Main.cfgm.getPlayers().set(getid, _id);
            Main.cfgm.savePlayers();
            Main.cfgm.reloadPlayers();
            _id++;
        }

        String title = ChatColor.AQUA + "Apollo Network";
        String subtitle = ChatColor.translateAlternateColorCodes('&',"&aName: &f" + p.getName() + " &aID: &f" + id);
        p.sendTitle(title,subtitle);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) { // QUIT
        Player p = e.getPlayer();
        for (Player vplayer : MainCommands.vanishlist){
            MainCommands.vanishlist.remove(vplayer);
        }

    }

    @EventHandler
    public void onMovement(PlayerMoveEvent e) { // MOVEMENT
        Player p = e.getPlayer();
        if (MainCommands.freezelist.contains(p)) { // FREEZE
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {  // BLOCK PLACE
        Location playerlocation = event.getPlayer().getLocation();
        Player player = event.getPlayer();
        if (playerlocation.getWorld().getName().equals("spawn4")) { // SPAWN PROTECTION
            if (!(player.isOp())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You can't place any block on this world!");
            }
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) { // BLOCK BREAK
        Location playerlocation = event.getPlayer().getLocation();
        Player player = event.getPlayer();
        if (playerlocation.getWorld().getName().equals("spawn4")) { // SPAWN PROTECTION
            if (!(player.isOp())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You can't break any block on this world!");
            }
        }
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e) { // GOD
        Player p = (Player) e.getEntity();
        UUID uuid = p.getUniqueId();
        if (MainCommands.godlist.contains(uuid)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (Chat.dplayer.contains(p)) {
            e.getRightClicked().remove();
            p.sendMessage(ChatColor.GREEN + "Dead!");
            Chat.dplayer.remove(p);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        Item item = e.getItemDrop();
    }

    @EventHandler
    public void  onSignChange(SignChangeEvent e){
        String[] lines = e.getLines();
        for(int i = 0; i < 4 ; i++){
            String Line = lines[i];
            Line = ChatColor.translateAlternateColorCodes('&',Line);
            e.setLine(i,Line);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();
        e.setKeepInventory(true);
        e.setKeepLevel(true);
        assert p != null;
        p.setBedSpawnLocation(null);
        p.teleport(Objects.requireNonNull(MainCommands.plugin.getConfig().getLocation("Spawn")));
    }

}

