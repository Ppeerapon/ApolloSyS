package Ppeerapon.ApolloSyS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager  {

    private Main plugin = Main.getPlugin(Main.class); // PLUGIN
    public FileConfiguration usersscfg;
    public File usersfile; // USER FILE

    public void setup(){
        if (!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        usersfile = new File(plugin.getDataFolder(), "users.yml");

        if (!usersfile.exists()){
            try {
                usersfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "users.yml file has been created");
            } catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create users.yml");
            }
        }
        usersscfg = YamlConfiguration.loadConfiguration(usersfile);
    }

    public FileConfiguration getPlayers(){
        return usersscfg;
    }

    public void savePlayers(){
        try {
            usersscfg.save(usersfile);
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "users.yml file has been saved");
        } catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save users.yml file");
        }
    }

    public void reloadPlayers(){
        usersscfg = YamlConfiguration.loadConfiguration(usersfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "users.yml file has been reloaded");
    }
}
