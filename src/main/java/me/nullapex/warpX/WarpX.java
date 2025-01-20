package me.nullapex.warpX;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class WarpX extends JavaPlugin {
    private final HashMap<String, Location> warps = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("WarpPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("WarpPlugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("setwarp")) {
            if (args.length != 1) {
                player.sendMessage("Usage: /setwarp <name>");
                return true;
            }
            warps.put(args[0].toLowerCase(), player.getLocation());
            player.sendMessage("Warp " + args[0] + " set!");
        } else if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length != 1) {
                player.sendMessage("Usage: /warp <name>");
                return true;
            }
            Location warp = warps.get(args[0].toLowerCase());
            if (warp == null) {
                player.sendMessage("Warp " + args[0] + " does not exist!");
                return true;
            }
            player.teleport(warp);
            player.sendMessage("Warped to " + args[0] + "!");
        } else if (command.getName().equalsIgnoreCase("delwarp")) {
            if (args.length != 1) {
                player.sendMessage("Usage: /delwarp <name>");
                return true;
            }
            if (warps.remove(args[0].toLowerCase()) != null) {
                player.sendMessage("Warp " + args[0] + " deleted!");
            } else {
                player.sendMessage("Warp " + args[0] + " does not exist!");
            }
        }

        return true;
    }
}