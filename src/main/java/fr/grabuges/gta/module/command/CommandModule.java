package fr.grabuges.gta.module.command;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.module.Module;
import fr.grabuges.gta.Variables;
import fr.grabuges.gta.module.command.command.MakePortalCommand;
import fr.grabuges.gta.module.command.command.OpenCommand;
import fr.grabuges.gta.module.command.command.RankCommand;
import fr.grabuges.gta.module.command.command.SudoCommand;
import fr.grabuges.gta.module.command.listener.player.PlayerPerformCommandListener;
import lombok.SneakyThrows;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;

import java.lang.reflect.Field;

public class CommandModule implements Module {

    @Override
    public Listener[] listeners() {
        return new Listener[] {
                new PlayerPerformCommandListener()
        };
    }

    @Override
    public AbstractCommand[] commands() {
        return new AbstractCommand[] {
                new OpenCommand(this),
                new SudoCommand(this),
                new MakePortalCommand(this),
                new RankCommand()
        };
    }

    @Override
    public String prefix() {
        return Variables.PREFIX;
    }
}
