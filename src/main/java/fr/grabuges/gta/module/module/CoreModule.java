package fr.grabuges.gta.module.module;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.module.Module;
import fr.grabuges.gta.module.module.command.ModuleCommand;
import org.bukkit.event.Listener;

public class CoreModule implements Module {

    @Override
    public Listener[] listeners() {
        return new Listener[0];
    }

    @Override
    public AbstractCommand[] commands() {
        return new AbstractCommand[] {
                new ModuleCommand(this)
        };
    }

    @Override
    public String prefix() {
        return "&a&l┃ &aModule &7";
    }

}
