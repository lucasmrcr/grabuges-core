package fr.grabuges.gta.module.command.command;

import org.bukkit.entity.Player;

//import com.mojang.authlib.GameProfile;
import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.command.annotation.Command;
import fr.grabuges.gta.module.command.CommandModule;

public class OpenCommand extends AbstractCommand {

    private final CommandModule commandModule;

    public OpenCommand(CommandModule commandModule) {
        this.commandModule = commandModule;
    }

    @Override
    @Command(
            name = "open",
            helpMessage = "/open <inventaire, enderchest> <joueur>",
            permission = "gta.open"
    )
    public boolean execute(Player sender, Arguments arguments) {
        if (!arguments.equals(2))
            return true;

        /*String type = arguments.get(0), target = arguments.get(1);
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
        MinecraftServer minecraftServer = MinecraftServer.getServer();
        GameProfile gameProfile = new GameProfile(offlinePlayer.getUniqueId(), offlinePlayer.getName());
        WorldServer worldServer = null;

        for (WorldServer world : minecraftServer.worlds) {
            for (EntityHuman player : world.players) {
                if (player.getName().equals(sender.getName())) {
                    worldServer = world;
                }
            }
        }

        assert worldServer != null;
        EntityPlayer entityPlayer = new EntityPlayer(
                minecraftServer,
                worldServer,
                gameProfile,
                new PlayerInteractManager(worldServer)
        );
        Player targetPlayer = entityPlayer.getBukkitEntity();

        if (type.equals("inventaire"))
            sender.openInventory(targetPlayer.getInventory());
        else if (type.equals("enderchest"))
            sender.openInventory(targetPlayer.getEnderChest());
        else return true;

        sender.sendMessage(ChatUtils.format(
                "%sVous venez d'ouvrir l'&b%s &7de &c%s &7dans le monde &d%s&7.",
                commandModule.prefix(),
                type.toLowerCase(),
                targetPlayer.getName(),
                worldServer.getWorld().getName()
        )); */
        return false;
    }

}