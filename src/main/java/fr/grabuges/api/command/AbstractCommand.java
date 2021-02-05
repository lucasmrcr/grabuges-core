package fr.grabuges.api.command;

import fr.grabuges.api.command.annotation.Command;
import fr.grabuges.api.command.exception.CommandRuntimeException;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Arrays;

public abstract class AbstractCommand {

    public abstract boolean execute(Player sender, Arguments arguments);
    private Command command = null;

    public boolean isThatCommand(String commandText) {
        final Command command = getCommand();
        final String[] commands = ArrayUtils.addAll(command.aliases(), command.name());

        return Arrays.asList(commands).contains(commandText);
    }

    public boolean hasPermissionToPerform(Player player) {
        return player.hasPermission(getCommand().permission());
    }

    public Command getCommand() {
        if (command != null)
            return command;

        try {
            Method execute = getClass().getMethod("execute", Player.class, Arguments.class);
            command = execute.getAnnotation(Command.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (command == null)
            throw new CommandRuntimeException("Command (%s) does not have Command annotation.",
                    getClass().getSimpleName());

        return command;
    }

}
