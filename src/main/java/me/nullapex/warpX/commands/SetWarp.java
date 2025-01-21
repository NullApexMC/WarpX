package me.nullapex.warpX.commands;

import me.nullapex.warpX.WarpX;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {
    private final WarpX plugin;

    public SetWarp(WarpX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /setwarp <warp name>");
            return true;
        }

        String warpName = args[0].toLowerCase();
        Location location = player.getLocation();
        saveWarp(warpName, location);

        player.sendMessage(ChatColor.GREEN + "Warp " + ChatColor.AQUA + warpName + ChatColor.GREEN + " has been set.");
        return true;
    }

    private void saveWarp(String warpName, Location location) {
        FileConfiguration config = plugin.getWarpsConfig();

        config.set(warpName + ".world", location.getWorld().getName());
        config.set(warpName + ".x", location.getX());
        config.set(warpName + ".y", location.getY());
        config.set(warpName + ".z", location.getZ());

        plugin.saveWarpsConfig();
    }
}
