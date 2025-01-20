package me.nullapex.warpX;

import me.nullapex.warpX.commands.SetWarp;
import me.nullapex.warpX.commands.Warp;
import me.nullapex.warpX.commands.DelWarp;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class WarpX extends JavaPlugin {
    private final HashMap<String, Location> warps = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("WarpX enabled!");

        getCommand("setwarp").setExecutor(new SetWarp(warps));
        getCommand("warp").setExecutor(new Warp(warps));
        getCommand("delwarp").setExecutor(new DelWarp(warps));
    }

    @Override
    public void onDisable() {
        getLogger().info("WarpX disabled!");
    }
}