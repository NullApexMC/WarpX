package me.nullapex.warpX.commands;

import me.nullapex.warpX.WarpX;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Warp implements CommandExecutor {
    private final WarpX plugin;

    public Warp(WarpX plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /warp <name>");
            return true;
        }

        String warpName = args[0].toLowerCase();

        FileConfiguration config = plugin.getConfig();

        if(!config.contains(warpName)) {
            player.sendMessage(ChatColor.RED + "Warp does not exist");
            return true;
        }

        String worldName = config.getString(warpName + ".world");
        double x = config.getDouble(warpName + ".x");
        double y = config.getDouble(warpName + ".y");
        double z = config.getDouble(warpName + ".z");

        if (Bukkit.getWorld(worldName) == null) {
            player.sendMessage(ChatColor.RED + "World " + worldName + " does not exist");
            return true;
        }

        Location loc = new Location(Bukkit.getWorld(worldName), x, y, z);

        player.teleport(loc);
        player.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.AQUA + warpName);
        return true;
    }
}
