package me.nullapex.warpX.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class DelWarp implements CommandExecutor {
    private final HashMap<String, Location> warps;

    public DelWarp(HashMap<String, Location> warps) {
        this.warps = warps;
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
        if (warps.remove(warpName) != null) {
            player.sendMessage(ChatColor.RED + "Warp " + ChatColor.AQUA + warpName + ChatColor.RED + " has been deleted.");
        } else {
            player.sendMessage(ChatColor.RED + "Warp " + ChatColor.AQUA + warpName + ChatColor.RED + " not found.");
        }
        return true;
    }
}
