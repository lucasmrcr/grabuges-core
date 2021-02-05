package fr.grabuges.api.command.sub;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.module.Module;
import fr.grabuges.api.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SubCommand {

    private final Module module;
    private final AbstractCommand[] subCommands;

    public SubCommand(Module module, AbstractCommand[] subCommands) {
        this.module = module;
        this.subCommands = subCommands;
    }

    public void handleSubCommand(Player sender, Arguments arguments) {
        if (arguments.equals(0) || arguments.get(0).equalsIgnoreCase("")) {
            sendHelp(sender);
            return;
        }

        String subCommandText = arguments.get(0);

        for (AbstractCommand subCommand : subCommands) {
            if (subCommand.isThatCommand(subCommandText)) {
                if (subCommand.hasPermissionToPerform(sender)) {
                    if (subCommand.execute(sender, arguments.cloneSubArguments())){
                        sender.sendMessage(ChatUtils.format(
                                "&cUsage : %s",
                                subCommand.getCommand().helpMessage()
                        ));
                    }
                } else
                    sender.sendMessage(ChatUtils.format(subCommand.getCommand().noPermissionMessage()));

                break;
            }
        }
    }

    private void sendHelp(Player sender) {
        sender.sendMessage(ChatUtils.format(
                "%sVoici la liste des commandes :\n%s",
                module.prefix(),
                Arrays.stream(subCommands)
                        .filter(abstractCommand -> abstractCommand.getCommand().permission().equals("")
                                || sender.hasPermission(abstractCommand.getCommand().permission())
                        )
                        .map(abstractCommand -> "&7» " + abstractCommand.getCommand().helpMessage())
                        .collect(Collectors.joining("\n"))
        ));
    }

}
