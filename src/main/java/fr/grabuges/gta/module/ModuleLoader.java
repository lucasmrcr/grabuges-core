package fr.grabuges.gta.module;

import fr.grabuges.api.module.Module;
import fr.grabuges.gta.App;
import fr.grabuges.gta.module.command.factory.CommandFactory;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class ModuleLoader {

    private final HashMap<String, Module> modules;
    private final HashMap<String, JavaPlugin> plugins;

    public ModuleLoader() {
        modules = new HashMap<>();
        plugins = new HashMap<>();
    }

    public void loadModule(Module module, JavaPlugin javaPlugin) {
        PluginManager pluginManager = javaPlugin.getServer().getPluginManager();

        for (Listener listener : module.listeners())
            pluginManager.registerEvents(listener, javaPlugin);

        CommandFactory.getInstance().replace(module.getClass().getSimpleName(), module.commands());

        modules.put(module.getClass().getSimpleName(), module);
        plugins.put(javaPlugin.getClass().getSimpleName(), javaPlugin);
    }

    public void unloadModule(Module module, JavaPlugin javaPlugin) {
        CommandFactory.getInstance().remove(module.getClass().getSimpleName());
        modules.remove(module.getClass().getSimpleName());
        plugins.remove(javaPlugin.getClass().getSimpleName());
    }

    public Optional<Module> getModule(Class<? extends Module> moduleClass) {
        return Optional.ofNullable(modules.getOrDefault(moduleClass.getSimpleName(), null));
    }

    public Optional<Module> getModule(String name) {
        Module module = null;

        for (Module tempModule : modules.values()) {
            if (tempModule.getClass().getSimpleName().equalsIgnoreCase(name)) {
                module = tempModule;
                break;
            }
        }

        return Optional.ofNullable(module);
    }

    public Optional<JavaPlugin> getPlugin(Class<? extends JavaPlugin> moduleClass) {
        return Optional.ofNullable(plugins.getOrDefault(moduleClass.getSimpleName(), null));
    }

    public Collection<Module> getModules() {
        return modules.values();
    }

}
