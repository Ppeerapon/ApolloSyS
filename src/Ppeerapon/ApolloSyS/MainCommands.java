package Ppeerapon.ApolloSyS;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static org.bukkit.Bukkit.getLootTable;
import static org.bukkit.Bukkit.getServer;

public class MainCommands implements CommandExecutor {

    static Main plugin; // CommandExecutor
    public MainCommands(Main main) {
        plugin = main;
    }

    String noperm = ChatColor.RED + "Sorry, but you don't have permission"; // NO PERMISSION MSG
    String prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "S" + ChatColor.AQUA + ChatColor.BOLD + "y" + ChatColor.DARK_AQUA + ChatColor.BOLD + "S" + ChatColor.BOLD + ChatColor.WHITE + " > ";

    public static ArrayList<UUID> godlist = new ArrayList<>(); // GOD LIST
    public static ArrayList<UUID> flylist = new ArrayList<>(); // FLY LIST
    public static ArrayList<UUID> freezelist = new ArrayList<>(); // FREEZE LIST
    public static ArrayList<Player> vanishlist = new ArrayList<>(); // VANISH LIST
    public static ArrayList<UUID> mutelist = new ArrayList<>(); // MUTE LIST
    public static ArrayList<UUID> prisoner = new ArrayList<>(); // JAIL LIST
    static  ArrayList<UUID> tptoggle = new ArrayList<>(); // TP-OFF LIST

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location spawn = plugin.getConfig().getLocation("Spawn");
        if (command.getName().equalsIgnoreCase("setspawn")){
            plugin.getConfig().set("Spawn", player.getLocation());
            plugin.saveConfig();
            player.sendMessage(prefix + ChatColor.AQUA + "Spawn is set at you location");
        }
        if (command.getName().equalsIgnoreCase("spawn")){
            if (spawn == null){
                player.sendMessage(prefix + ChatColor.RED + "Spawn isn't set");
            } else if (args.length == 0) {
                player.teleport(spawn);
                player.sendMessage(prefix + ChatColor.AQUA + "You has been teleport to spawn");
            } else if (args.length == 1) {
                Player target = getServer().getPlayer(args[0]);
                assert target != null;
                target.teleport(spawn);
                target.sendMessage(prefix + ChatColor.AQUA + "You has been teleport to spawn by " + player.getDisplayName());
                player.sendMessage(prefix + target.getDisplayName() + ChatColor.AQUA + " has been teleport to spawn");
            }
        }

        if (command.getName().equalsIgnoreCase("heal")) { // HEAL
            if (player.hasPermission("SyS.Heal") || player.hasPermission("SyS.*")) {
                if (args.length == 0) { //HEAL YOUR SELF
                    player.setHealth(20);
                    player.sendMessage(prefix + ChatColor.GREEN + "You have been healed");
                    return true;
                } else if (args.length == 1) { //HEAL OTHER
                    Player target = getServer().getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(prefix + ChatColor.RED + "Player not found");
                    } else {
                        target.setHealth(20);
                        target.sendMessage(prefix + ChatColor.GREEN + "You have been healed from " + ChatColor.YELLOW + player.getName());
                        player.sendMessage(prefix + ChatColor.YELLOW + "" + target.getName() + ChatColor.GREEN + " has been healed");
                    }
                    return true;
                }
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("feed")) { // FEED
            if (player.hasPermission("SyS.Feed") || player.hasPermission("SyS.*")) {
                if (args.length == 0) {
                    player.setFoodLevel(20);
                    player.sendMessage(prefix + ChatColor.GREEN + "You have been feed");
                } else if (args.length == 1) { // FEED OTHER
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    target.setFoodLevel(20);
                    target.sendMessage(prefix + ChatColor.GREEN + "You have been feed by " + ChatColor.YELLOW + player.getName());
                } else player.sendMessage(ChatColor.RED + "Usage: /feed [player]");
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("god")) { // GOD
            if (player.hasPermission("SyS.God") || player.hasPermission("SyS.*")) {
                if (args.length == 0) {
                    if (!godlist.contains(player.getUniqueId())) {
                        godlist.add(player.getUniqueId());
                        player.sendMessage(prefix + ChatColor.GREEN + "GodMode is enabled");
                    } else {
                        godlist.remove(player.getUniqueId());
                        player.sendMessage(prefix + ChatColor.RED + "GodMode is disabled");
                    }
                } else if (args.length == 1) {
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    if (!godlist.contains(target.getUniqueId())) {
                        godlist.add(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.GREEN + "GodMode is enabled for " + ChatColor.YELLOW + target.getName());
                        target.sendMessage(prefix + ChatColor.GREEN + "GodMode is enabled by " + ChatColor.YELLOW + player.getName());
                    } else {
                        godlist.remove(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.RED + "GodMode is disabled for " + ChatColor.YELLOW + target.getName());
                        target.sendMessage(prefix + ChatColor.RED + "GodMode is disabled by " + ChatColor.YELLOW + player.getName());
                    }
                }
            } else player.sendMessage(noperm);

        }

        if (command.getName().equalsIgnoreCase("fly")) { // FLY
            if (player.hasPermission("SyS.Fly") || player.hasPermission("SyS.*")) {
                if (args.length == 0) {
                    if (!flylist.contains(player.getUniqueId())) {
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        flylist.add(player.getUniqueId());
                        player.sendMessage(prefix + ChatColor.GREEN + "FlyMode is enabled");
                    } else {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        flylist.remove(player.getUniqueId());
                        player.sendMessage(prefix + ChatColor.RED + "FlyMode is disabled");
                    }
                } else if (args.length == 1) { // FLY OTHER
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    if (!flylist.contains(target.getUniqueId())) {
                        target.setAllowFlight(true);
                        target.setFlying(true);
                        flylist.add(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.GREEN + "FlyMode is enable for " + ChatColor.YELLOW + target.getName());
                        target.sendMessage(prefix + ChatColor.GREEN + "FlyMode is enable by " + ChatColor.YELLOW + player.getName());
                    } else {
                        target.setAllowFlight(false);
                        target.setFlying(false);
                        flylist.remove(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.RED + "FlyMode is disable for " + ChatColor.YELLOW + target.getName());
                        target.sendMessage(prefix + ChatColor.RED + "FlyMode is disable by " + ChatColor.YELLOW + player.getName());
                    }
                }
            } else {
                player.sendMessage(noperm);
            }
        }
        if (command.getName().equalsIgnoreCase("invsee")) { // INVSEE
            if (player.hasPermission("SyS.Invsee") || player.hasPermission("SyS.*")) {
                if (args.length == 1) {
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    player.openInventory(target.getInventory());
                } else {
                    player.sendMessage(ChatColor.RED + "Something went wrong");
                }
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("freeze")) { // FREEZE
            if (player.hasPermission("SyS.Freeze") || player.hasPermission("SyS.*")) {
                if (args.length == 1) {
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    if (!freezelist.contains(target.getUniqueId())) {
                        freezelist.add(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " has been freeze");
                        target.sendMessage(prefix + ChatColor.RED + "You have been freeze by " + ChatColor.YELLOW + player.getName());
                    } else {
                        freezelist.remove(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.YELLOW + target.getName() + ChatColor.RED + " has been unfreeze");
                        target.sendMessage(prefix + ChatColor.GREEN + "You have been unfreeze by " + ChatColor.YELLOW + player.getName());
                    }
                }
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("Enderchest")) { // ENDER CHEST
            if (player.hasPermission("SyS.Enderchest") || player.hasPermission("SyS.*")) {
                if (args.length == 0) {
                    player.openInventory(player.getEnderChest());
                } else if (args.length == 1) {
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    player.openInventory(target.getEnderChest());
                }
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("repair")) { // REPAIR
            if (player.hasPermission("SyS.Repair") || player.hasPermission("SyS.*")) {
                player.getItemInHand().setDurability((short) 1000);
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("Vanish")) { // VANISH
            if (player.hasPermission("SyS.Vanish") || player.hasPermission("SyS.*")) {
                if (args.length == 0) {
                    if (!vanishlist.contains(player)) {
                        for (Player onlineplayer : getServer().getOnlinePlayers()) {
                            onlineplayer.hidePlayer(player);
                        }
                        vanishlist.add(player);
                        player.sendMessage(prefix + ChatColor.GREEN + "You are now vanished");
                    } else {
                        for (Player onlineplayer : getServer().getOnlinePlayers()) {
                            onlineplayer.showPlayer(player);
                        }
                        vanishlist.remove(player);
                        player.sendMessage(prefix + ChatColor.RED + "You are no longer vanished");
                    }
                }
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("mute")) { // MUTE
            if (player.hasPermission("SyS.Mute") || player.hasPermission("SyS.*")) {
                if (args.length == 1) {
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    if (!mutelist.contains(target.getUniqueId())) {
                        mutelist.add(target.getUniqueId());
                        player.sendMessage(prefix + ChatColor.GREEN + target.getName() + " has been muted");
                        target.sendMessage(prefix + ChatColor.RED + "You have been muted by " + ChatColor.YELLOW + player);
                    } else {
                        player.sendMessage(prefix + ChatColor.RED + "This player already muted");
                    }
                }
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("unmute")) { // UN MUTE
            if (args.length == 1) {
                Player target = getServer().getPlayer(args[0]);
                assert target != null;
                if (mutelist.contains(target.getUniqueId())) {
                    mutelist.remove(target.getUniqueId());
                    player.sendMessage(prefix + ChatColor.GREEN + "Unmute " + target.getName());
                    target.sendMessage(prefix + ChatColor.GREEN + "You have been unmute by " + player.getName());
                } else {
                    player.sendMessage(prefix + ChatColor.RED + "Player isn't muted");
                }
            }
        }

        if (command.getName().equalsIgnoreCase("setjail")) { // SET JAIL
            if (player.hasPermission("SyS.Setjail") || player.hasPermission("SyS.*")) {
                player.sendMessage(prefix + ChatColor.GREEN + "Jail location has been set");
                plugin.getConfig().set("Jail", player.getLocation());
                plugin.saveConfig();
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("jail")) { // JAIL
            if (player.hasPermission("SyS.Jail") || player.hasPermission("SyS.*")) {
                if (args.length == 1) {
                    Player target = getServer().getPlayer(args[0]);
                    assert target != null;
                    if (!prisoner.contains(target.getUniqueId())) {
                        prisoner.add(target.getUniqueId());
                        target.teleport(Objects.requireNonNull(plugin.getConfig().getLocation("Jail")));
                    }
                }
            }
        }

        if (command.getName().equalsIgnoreCase("sethome")) { // SET HOME
            String homename;
            if (args.length == 1) {
                homename = args[0];
            } else {
                homename = "Home";
            }
            String home = "users." + player.getName() + ".homes." + homename;
            Main.cfgm.getPlayers().set(home, player.getLocation());
            Main.cfgm.savePlayers();
            Main.cfgm.reloadPlayers();
            player.sendMessage(prefix + ChatColor.GREEN + homename + " has been set at your location");
        }

        if (command.getName().equalsIgnoreCase("home")) { // HOME
            if (args.length == 0) {
                String home = "users." + player.getName() + ".homes.Home";
                Location location = Main.cfgm.usersscfg.getLocation(home);
                assert location != null;
                player.teleport(location);
                player.sendMessage(prefix + ChatColor.GREEN + "You have been teleport to home");
            } else if (args.length == 1) {
                String home = "users." + player.getName() + ".homes." + args[0];
                Location location = Main.cfgm.usersscfg.getLocation(home);
                if (location != null) {
                    player.teleport(location);
                    player.sendMessage(prefix + ChatColor.GREEN + "You have been teleport to " + args[0]);
                } else {
                    player.sendMessage(prefix + ChatColor.RED + "Home isn't exists");
                }
            }
        }

        if (command.getName().equalsIgnoreCase("gamemode")) { // GAME MODE
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Usage: /gamemode <gamemode>");
            } else if (args.length == 1) {
                if (player.hasPermission("SyS.Gamemode") || player.hasPermission("SyS.*")) {
                    if (args[0].equalsIgnoreCase("Survival") || args[0].equalsIgnoreCase("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Survival");
                    } else if (args[0].equalsIgnoreCase("Creative") || args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Creative");
                    } else if (args[0].equalsIgnoreCase("Adventure") || args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Adventure");
                    } else if (args[0].equalsIgnoreCase("Spectator") || args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Spectator");
                    }
                } else {
                    player.sendMessage(noperm);
                }
            } else if (args.length == 2) { // GAME MODE OTHER
                Player target = Bukkit.getPlayer(args[1]);
                if (player.hasPermission("SyS.Gamemode.Other") || player.hasPermission("SyS.*")) {
                    if (args[0].equalsIgnoreCase("Survival") || args[0].equalsIgnoreCase("0")) {
                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Survival " + ChatColor.AQUA + "By " + ChatColor.WHITE + player.getDisplayName());
                        target.sendMessage(prefix + ChatColor.AQUA + "Change " + ChatColor.WHITE + target.getDisplayName() + ChatColor.AQUA + " gamemode to " + ChatColor.DARK_AQUA + "Survival");
                    } else if (args[0].equalsIgnoreCase("Creative") || args[0].equalsIgnoreCase("1")) {
                        target.setGameMode(GameMode.CREATIVE);
                        target.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Creative " + ChatColor.AQUA + "By " + ChatColor.WHITE + player.getDisplayName());
                        target.sendMessage(prefix + ChatColor.AQUA + "Change " + ChatColor.WHITE + target.getDisplayName() + ChatColor.AQUA + " gamemode to " + ChatColor.DARK_AQUA + "Creative");
                    } else if (args[0].equalsIgnoreCase("Adventure") || args[0].equalsIgnoreCase("2")) {
                        target.setGameMode(GameMode.ADVENTURE);
                        target.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Adventure " + ChatColor.AQUA + "By " + ChatColor.WHITE + player.getDisplayName());
                        target.sendMessage(prefix + ChatColor.AQUA + "Change " + ChatColor.WHITE + target.getDisplayName() + ChatColor.AQUA + " gamemode to " + ChatColor.DARK_AQUA + "Adventure");
                    } else if (args[0].equalsIgnoreCase("Spectator") || args[0].equalsIgnoreCase("3")) {
                        target.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage(prefix + ChatColor.AQUA + "Your gamemode have change to " + ChatColor.DARK_AQUA + "Spectator " + ChatColor.AQUA + "By " + ChatColor.WHITE + player.getDisplayName());
                        target.sendMessage(prefix + ChatColor.AQUA + "Change " + ChatColor.WHITE + target.getDisplayName() + ChatColor.AQUA + " gamemode to " + ChatColor.DARK_AQUA + "Spectator");
                    }
                } else {
                    player.sendMessage(noperm);
                }
            }
        }


        if (command.getName().equalsIgnoreCase("sun")){
            if (player.hasPermission("SyS.Weather") || player.hasPermission("SyS.*")){
                player.getWorld().setStorm(false);
                player.getWorld().setThundering(false);
                player.sendMessage(prefix + ChatColor.AQUA + "Weather change to " + ChatColor.DARK_AQUA + "Sunny");
            } else{
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("rain")){
            if (player.hasPermission("SyS.Weather") || player.hasPermission("SyS.*")){
                player.getWorld().setStorm(true);
                player.sendMessage(prefix + ChatColor.AQUA + "Weather change to " + ChatColor.DARK_AQUA + "Storm");
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("thunder")){
            if (player.hasPermission("SyS.Weather") || player.hasPermission("SyS.*")){
                player.getWorld().setThundering(true);
                player.sendMessage(prefix + ChatColor.AQUA + "Weather change to " + ChatColor.DARK_AQUA + "Thundering");
            } else {
                player.sendMessage(noperm);
            }
        }

        if (command.getName().equalsIgnoreCase("nick")){
            if (args.length == 1){
                if (player.hasPermission("SyS.Nick") || player.hasPermission("SyS.*")) {
                    player.setDisplayName(args[0]);
                    player.sendMessage(prefix + ChatColor.AQUA + "You name have been change to " + ChatColor.DARK_AQUA + args[0]);
                } else {
                    player.sendMessage(noperm);
                }
            }
        }

        if (command.getName().equalsIgnoreCase("day")){
            if (args.length == 0){
                if (player.hasPermission("SyS.Time") || player.hasPermission("SyS.*")) {
                    player.getWorld().setTime(1000);
                    player.sendMessage(prefix + ChatColor.AQUA + "Time set " + ChatColor.DARK_AQUA + "Day");
                } else {
                    player.sendMessage(noperm);
                }
            }
        }

        if (command.getName().equalsIgnoreCase("noon")){
            if (args.length == 0){
                if (player.hasPermission("SyS.Time") || player.hasPermission("SyS.*")) {
                    player.getWorld().setTime(6000);
                    player.sendMessage(prefix + ChatColor.AQUA + "Time set " + ChatColor.DARK_AQUA + "Noon");
                } else {
                    player.sendMessage(noperm);
                }
            }
        }

        if (command.getName().equalsIgnoreCase("night")){
            if (args.length == 0){
                if (player.hasPermission("SyS.Time") || player.hasPermission("SyS.*")) {
                    player.getWorld().setTime(13000);
                    player.sendMessage(prefix + ChatColor.AQUA + "Time set " + ChatColor.DARK_AQUA + "Night");
                } else {
                    player.sendMessage(noperm);
                }
            }
        }

        if (command.getName().equalsIgnoreCase("midnight")){
            if (args.length == 0){
                if (player.hasPermission("SyS.Time") || player.hasPermission("SyS.*")) {
                    player.getWorld().setTime(18000);
                    player.sendMessage(prefix + ChatColor.AQUA + "Time set " + ChatColor.DARK_AQUA + "Midnight");
                } else {
                    player.sendMessage(noperm);
                }
            }
        }

        if (command.getName().equalsIgnoreCase("sleep")){
            player.sendMessage("Good Night!");
        }

        if (command.getName().equalsIgnoreCase("suicide")){
            player.setHealth(0);
            for (Player onlineplayer : getServer().getOnlinePlayers()){
                onlineplayer.sendMessage(prefix + ChatColor.GRAY + "R.I.P " + player.getDisplayName() + " 2020-2020");
            }
        }

        if (command.getName().equalsIgnoreCase("tptoggle")){
            if (!(tptoggle.contains(player.getUniqueId()))){
                player.sendMessage(prefix + ChatColor.RED + "TP-OFF No one can't teleport to you");
                tptoggle.add(player.getUniqueId());
            } else {
                player.sendMessage(prefix + ChatColor.GREEN + "TP-ON All can teleport to you");
                tptoggle.remove(player.getUniqueId());
            }
        }

        if (command.getName().equalsIgnoreCase("teleport")){
            if (player.hasPermission("SyS.Teleport") || player.hasPermission("SyS.*")){
                Player target = getServer().getPlayer(args[0]);
                assert target != null;
                Location targetlocation = target.getLocation();
                if (args.length == 1){
                    if (!(tptoggle.contains(target.getUniqueId()))){
                        player.teleport(targetlocation);
                        player.sendMessage(prefix + ChatColor.GREEN + "You have been teleport to " + target.getDisplayName());
                    } else {
                        player.sendMessage(prefix + ChatColor.RED + "Player has disable teleport");
                    }
                }
            }
        }

        if (command.getName().equalsIgnoreCase("getid")){
            int data = player.getItemInHand().getData().getItemType().getId();
            player.sendMessage(String.valueOf(data));
        }

        if (command.getName().equalsIgnoreCase("apollosys")){ // ???
            player.sendMessage("ApolloSyS Version 0.1 By Ppeerapon");
        }



        return true;
    }
}
