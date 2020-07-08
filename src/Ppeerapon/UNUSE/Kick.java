package Ppeerapon.UNUSE;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Kick extends JavaPlugin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kick")){ // KICK
            Player target = getServer().getPlayer(args[0]);
            assert target != null;
            target.kickPlayer(args[1]);
        }
        return true;
    }
}
