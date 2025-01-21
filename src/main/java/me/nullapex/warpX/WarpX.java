package me.nullapex.warpX;

import me.nullapex.warpX.commands.SetWarp;
import me.nullapex.warpX.commands.Warp;
import me.nullapex.warpX.commands.DelWarp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class WarpX extends JavaPlugin {
    private File warpsFile;
    private FileConfiguration warpsConfig;

    @Override
    public void onEnable() {
        getLogger().info("WarpX enabled!");

        createWarpsFile();

        getCommand("setwarp").setExecutor(new SetWarp(this));
        getCommand("warp").setExecutor(new Warp(this));
        getCommand("delwarp").setExecutor(new DelWarp(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("WarpX disabled!");
    }

    private void createWarpsFile() {
        warpsFile = new File(getDataFolder(), "warps.yml");
        if(!warpsFile.exists()) {
            saveResource("warps.yml", false);
        }
        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
    }

    public FileConfiguration getWarpsConfig() {
        return warpsConfig;
    }

    public void saveWarpsConfig() {
        try {
            warpsConfig.save(warpsFile);
        } catch (IOException e) {
            getLogger().severe("Could not save warps.yml: " + e.getMessage());
        }
    }
}