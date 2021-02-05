package fr.grabuges.vendor;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourceList {

    @SneakyThrows
    public static Map<String, FileConfiguration> getConfigsFromJarFile(final File file) {
        final Map<String, FileConfiguration> fileConfigurations = new HashMap<>();
        final ZipFile zipFile = new ZipFile(file);
        final Enumeration<? extends ZipEntry> enumeration = zipFile.entries();

        while (enumeration.hasMoreElements()) {
            final ZipEntry zipEntry = enumeration.nextElement();
            final String fileName = zipEntry.getName();

            if (fileName.endsWith(".yml")) {
                InputStreamReader inputStreamReader = new InputStreamReader(zipFile.getInputStream(zipEntry));
                fileConfigurations.put(fileName, YamlConfiguration.loadConfiguration(inputStreamReader));
            }
        }
        zipFile.close();
        return fileConfigurations;
    }

    @SneakyThrows
    public static List<String> getClasses(final File file) {
        final List<String> classes = new ArrayList<>();
        final ZipFile zipFile = new ZipFile(file);
        final Enumeration<? extends ZipEntry> enumeration = zipFile.entries();

        while (enumeration.hasMoreElements()) {
            final ZipEntry zipEntry = enumeration.nextElement();
            final String fileName = zipEntry.getName();

            if (fileName.endsWith(".class")) {
                classes.add(fileName.replaceAll("/", "."));
            }
        }
        zipFile.close();
        return classes;
    }

}