package ua.limefu.classes;

import org.bukkit.plugin.java.JavaPlugin;
import ua.limefu.classes.Type.*;

public final class Classes extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SpeedMan(), this);
        getServer().getPluginManager().registerEvents(new Vampire(), this);
        getServer().getPluginManager().registerEvents(new EnderWizard(), this);
        getServer().getPluginManager().registerEvents(new Snake(), this);
        getServer().getPluginManager().registerEvents(new Jumper(), this);
        getCommand("case").setExecutor(new CaseCMD());
    }

    @Override
    public void onDisable() {

    }
}
