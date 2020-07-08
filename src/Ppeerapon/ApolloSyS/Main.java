package Ppeerapon.ApolloSyS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static Location jailLocation;
    public static ConfigManager cfgm;

    @Override
    public void onEnable(){
        loadConfigManager();

        getLogger().info(ChatColor.GREEN+"ApolloSyS has been enable!");

        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new MainListener(), this);

        loadConfig();

        registerCmds();
    }

    @Override
    public void  onDisable(){
        getLogger().info(ChatColor.RED+"ApolloSyS has been disable");
    }

    public void loadConfigManager(){
        cfgm = new ConfigManager();
        cfgm.setup();
        cfgm.savePlayers();
        cfgm.reloadPlayers();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

   @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("Yorn")) { // YORN
            sender.sendMessage(ChatColor.GOLD + "The Sun Power is Limitless, LIMITLESS!");
            return true;
        }

        return true;
    }

    public void registerCmds(){
        this.getCommand("setspawn").setExecutor(new MainCommands(this));
        this.getCommand("spawn").setExecutor(new MainCommands(this));
        this.getCommand("heal").setExecutor(new MainCommands(this));
        this.getCommand("feed").setExecutor(new MainCommands(this));
        this.getCommand("god").setExecutor(new MainCommands(this));
        this.getCommand("fly").setExecutor(new MainCommands(this));
        this.getCommand("invsee").setExecutor(new MainCommands(this));
        this.getCommand("freeze").setExecutor(new MainCommands(this));
        this.getCommand("enderchest").setExecutor(new MainCommands(this));
        this.getCommand("vanish").setExecutor(new MainCommands(this));
        this.getCommand("mute").setExecutor(new MainCommands(this));
        this.getCommand("unmute").setExecutor(new MainCommands(this));
        this.getCommand("jail").setExecutor(new MainCommands(this));
        this.getCommand("setjail").setExecutor(new MainCommands(this));
        this.getCommand("sethome").setExecutor(new MainCommands(this));
        this.getCommand("home").setExecutor(new MainCommands(this));
        this.getCommand("gamemode").setExecutor(new MainCommands(this));
        this.getCommand("nick").setExecutor(new MainCommands(this));
        this.getCommand("sun").setExecutor(new MainCommands(this));
        this.getCommand("rain").setExecutor(new MainCommands(this));
        this.getCommand("day").setExecutor(new MainCommands(this));
        this.getCommand("noon").setExecutor(new MainCommands(this));
        this.getCommand("night").setExecutor(new MainCommands(this));
        this.getCommand("midnight").setExecutor(new MainCommands(this));
        this.getCommand("thunder").setExecutor(new MainCommands(this));
        this.getCommand("sleep").setExecutor(new MainCommands(this));
        this.getCommand("suicide").setExecutor(new MainCommands(this));
        this.getCommand("tptoggle").setExecutor(new MainCommands(this));
        this.getCommand("teleport").setExecutor(new MainCommands(this));
        this.getCommand("getid").setExecutor(new MainCommands(this));
        this.getCommand("fakeop").setExecutor(new Prank(this));
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "PASS");
        this.getCommand("rapair").setExecutor(new MainCommands(this));
    }
    
}
