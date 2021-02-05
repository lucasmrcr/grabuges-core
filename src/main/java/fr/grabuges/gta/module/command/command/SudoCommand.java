package fr.grabuges.gta.module.command.command;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.command.annotation.Command;
import fr.grabuges.api.utils.ChatUtils;
import fr.grabuges.gta.module.command.CommandModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SudoCommand extends AbstractCommand {

    private final CommandModule commandModule;

    public SudoCommand(CommandModule commandModule) {
        this.commandModule = commandModule;
    }

    @Override
    @Command(
            name = "sudo",
            permission = "gta.sudo",
            helpMessage = "/sudo <pseudo> <message>"
    )
    public boolean execute(Player sender, Arguments arguments) {
        if (arguments.lessOrEqualsThan(2))
            return true;

        String targetName = arguments.get(0),
                message = arguments.buildFrom(1);

        Player target = Bukkit.getPlayerExact(targetName);

        if (target == null) {
            sender.sendMessage(ChatUtils.format(
                    "%s&8%s &7est hors-ligne.",
                    commandModule.prefix(),
                    targetName
            ));
            return false;
        }

        target.chat(message);
        sender.sendMessage(ChatUtils.format(
                "%s&8%s &7a envoyé %s",
                commandModule.prefix(),
                target.getName(),
                message
        ));

        return false;
    }
}
