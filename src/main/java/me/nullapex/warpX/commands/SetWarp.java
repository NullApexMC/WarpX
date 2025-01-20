package me.nullapex.warpX.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SetWarp implements CommandExecutor {
    private final HashMap<String, Location> warps;

    public SetWarp(HashMap<String, Location> warps) {
        this.warps = warps;
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
        warps.put(warpName, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Warp " + ChatColor.AQUA + warpName + ChatColor.GREEN + " has been set.");
        return true;
    }
}
