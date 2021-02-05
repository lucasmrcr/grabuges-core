package fr.grabuges.api.framework.configuration;

import fr.grabuges.api.module.Module;
import fr.grabuges.api.utils.LogUtils;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigurationFramework {

    private final String name;
    private final File file;
    private final FileConfiguration fileConfiguration;

    @SneakyThrows
    public ConfigurationFramework(JavaPlugin javaPlugin, String name) {
        File dataFolder = javaPlugin.getDataFolder();

        this.name = name + ".yml";
        this.file = new File(dataFolder, this.name);

        if (!dataFolder.exists() && !dataFolder.mkdir())
            throw new IOException("Cant create configuration folder " + dataFolder.getPath());


        if (!file.exists())
            javaPlugin.saveResource(this.name, false);

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        LogUtils.info("Loaded %s configuration.", this.file.getPath());
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    @SneakyThrows
    public void save() {
        fileConfiguration.save(file);
    }
}
