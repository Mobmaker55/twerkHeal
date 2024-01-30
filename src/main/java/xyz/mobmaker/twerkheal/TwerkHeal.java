package xyz.mobmaker.twerkheal;

import org.bukkit.plugin.java.JavaPlugin;

public final class TwerkHeal extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new TwerkListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
