package pt.starfall.spawners;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import pt.starfall.spawners.listener.SpawnerBreak;
import pt.starfall.spawners.listener.SpawnerPlace;

public final class Spawners extends JavaPlugin {
    private static Spawners plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new SpawnerBreak(), this);
        getServer().getPluginManager().registerEvents(new SpawnerPlace(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static @NotNull Plugin getPlugin() {
        return plugin;
    }
}
