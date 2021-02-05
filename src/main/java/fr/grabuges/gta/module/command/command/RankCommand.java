package fr.grabuges.gta.module.command.command;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.command.annotation.Command;
import fr.grabuges.api.utils.ChatUtils;
import fr.grabuges.gta.App;
import fr.grabuges.gta.Variables;
import fr.grabuges.gta.module.command.inventory.provider.RankProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RankCommand extends AbstractCommand {

    private final SmartInventory.Builder inventory = SmartInventory.builder()
            .id("rank")
            .size(4, 9)
            .closeable(true);

    @Override
    @Command(name = "rank", helpMessage = "/rank <joueur>", permission = "gta.rank")
    public boolean execute(Player sender, Arguments arguments) {
        if (arguments.equals(0)) return true;

        String target = arguments.get(0);

        inventory
                .title(ChatUtils.format(
                        "%s&7Ranks (&b%s &7— %s&7)",
                        Variables.PREFIX,
                        target,
                        App.getInstance().getLuckPerms().getPrefix(target)
                ))
                .provider(new RankProvider(Bukkit.getOfflinePlayer(target)))
                .build()
                .open(sender);

        return false;
    }
}
