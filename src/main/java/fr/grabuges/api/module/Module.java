package fr.grabuges.api.module;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.framework.configuration.ConfigurationFramework;
import fr.grabuges.api.utils.LogUtils;
import fr.grabuges.gta.App;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;

public interface Module {

    Listener[] listeners();
    AbstractCommand[] commands();
    String prefix();


}
