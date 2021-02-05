package fr.grabuges.gta.module.command.listener.player;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.utils.ChatUtils;
import fr.grabuges.gta.module.command.factory.CommandFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerPerformCommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerPerformCommand(PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage().substring(1) + " ",
                command = message.split(" ")[0].toLowerCase();
        final CommandFactory commandFactory = CommandFactory.getInstance();
        final String[] arguments = message.substring(command.length()+1).split(" ");

        List<AbstractCommand> abstractCommands = new ArrayList<>();
        for (AbstractCommand[] value : commandFactory.get().values())
            abstractCommands.addAll(Arrays.asList(value));

        for (AbstractCommand abstractCommand : abstractCommands) {
            if (abstractCommand.isThatCommand(command)) {
                if (abstractCommand.hasPermissionToPerform(player)) {
                    if (abstractCommand.execute(player, new Arguments(arguments))){
                        player.sendMessage(ChatUtils.format(
                                "&cUsage : %s",
                                abstractCommand.getCommand().helpMessage()
                        ));
                    }
                } else
                    player.sendMessage(ChatUtils.format(abstractCommand.getCommand().noPermissionMessage()));

                event.setCancelled(true);
                break;
            }
        }
    }

}
