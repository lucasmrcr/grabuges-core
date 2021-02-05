package fr.grabuges.api.framework.configuration;

import fr.grabuges.api.framework.configuration.annotation.ConfigurationProperty;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;

public class ConfigurationBuilder<T extends Configuration> {

    private final T configuration;

    public ConfigurationBuilder(T configuration) {
        this.configuration = configuration;
    }

    @lombok.SneakyThrows
    public T fromFramework(ConfigurationFramework configurationFramework) {
        FileConfiguration fileConfiguration = configurationFramework.getFileConfiguration();

        return getConfiguration(fileConfiguration);
    }

    @lombok.SneakyThrows
    public T fromMemorySection(MemorySection memorySection) {
        return getConfiguration(memorySection);
    }

    private T getConfiguration(MemorySection memorySection) throws IllegalAccessException {
        for (Field declaredField : configuration.getClass().getDeclaredFields()) {
            ConfigurationProperty configurationProperty = declaredField.getAnnotation(ConfigurationProperty.class);

            if (configurationProperty != null) {
                declaredField.setAccessible(true);
                declaredField.set(configuration, memorySection.get(configurationProperty.path()));
            }
        }
        return configuration;
    }

}
