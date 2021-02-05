package fr.grabuges.gta.module;

import fr.grabuges.gta.App;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class PluginFinder<T> {

    private final Class<? extends T> clazz;

    public PluginFinder(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    public Optional<T> get() {
        return (Optional<T>) App.getInstance().getModuleLoader().getPlugin((Class<? extends JavaPlugin>) clazz);
    }

}
