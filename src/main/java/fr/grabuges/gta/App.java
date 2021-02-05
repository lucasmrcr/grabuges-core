package fr.grabuges.gta;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.grabuges.gta.module.ModuleLoader;
import fr.grabuges.gta.module.command.CommandModule;
import fr.grabuges.gta.module.command.factory.CommandFactory;
import fr.grabuges.gta.module.module.CoreModule;
import fr.grabuges.vendor.LuckPerms;

public class App extends JavaPlugin {

    private static App instance;
    private ModuleLoader moduleLoader;
    private LuckPerms luckPerms;

    @Override
    public void onEnable() {
        instance = this;
        luckPerms = new LuckPerms(
                Bukkit.getServicesManager().getRegistration(net.luckperms.api.LuckPerms.class).getProvider()
        );

        moduleLoader = new ModuleLoader();

        CommandModule commandModule = new CommandModule();
        CoreModule coreModule = new CoreModule();

        moduleLoader.loadModule(commandModule, this);
        moduleLoader.loadModule(coreModule, this);
    }

    @Override
    public void onDisable() {
        CommandFactory.getInstance().get().clear();
    }

    public static App getInstance() {
        return instance;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }
}
