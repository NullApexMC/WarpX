package me.nullapex.warpX.commands;

import me.nullapex.warpX.WarpX;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {
    private final WarpX plugin;

    public DelWarp(WarpX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: delwarp <warp name>");
            return true;
        }

        String warpName = args[0].toLowerCase();

        FileConfiguration warpsConfig = plugin.getWarpsConfig();

        if(!warpsConfig.contains(warpName)) {
            player.sendMessage(ChatColor.RED + "That warp does not exist");
            return true;
        }

        warpsConfig.set(warpName, null);
        plugin.saveWarpsConfig();

        player.sendMessage(ChatColor.RED + "Warp " + ChatColor.AQUA + warpName + ChatColor.RED + " has been deleted");
        return true;
    }
}
