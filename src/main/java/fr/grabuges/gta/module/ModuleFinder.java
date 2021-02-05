package fr.grabuges.gta.module;

import fr.grabuges.api.module.Module;
import fr.grabuges.gta.App;

import java.util.Optional;

public class ModuleFinder {

    private final Class<? extends Module> clazz;

    public ModuleFinder(Class<? extends Module> clazz) {
        this.clazz = clazz;
    }

    public Optional<Module> get() {
        return App.getInstance().getModuleLoader().getModule(clazz);
    }

}
