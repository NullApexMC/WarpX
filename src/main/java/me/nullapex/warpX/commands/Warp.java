package me.nullapex.warpX.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Warp implements CommandExecutor {
    private final HashMap<String, Location> warps;

    public Warp(HashMap<String, Location> warps) {
        this.warps = warps;
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
        Location warp = warps.get(warpName);
        if (warp == null) {
            player.sendMessage(ChatColor.RED + "That location does not exist");
            return true;
        }

        player.teleport(warp);
        player.sendMessage(ChatColor.GREEN + "Warp " + ChatColor.AQUA + warpName);
        return true;
    }
}
