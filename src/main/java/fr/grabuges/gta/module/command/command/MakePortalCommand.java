package fr.grabuges.gta.module.command.command;

import com.sk89q.worldedit.bukkit.selections.Selection;
import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.command.annotation.Command;
import fr.grabuges.api.utils.ChatUtils;
import fr.grabuges.api.utils.WorldEditUtils;
import fr.grabuges.gta.module.command.CommandModule;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class MakePortalCommand extends AbstractCommand {

    private final CommandModule commandModule;

    public MakePortalCommand(CommandModule commandModule) {
        this.commandModule = commandModule;
    }

    @Override
    @Command(
            name = "makeportal",
            helpMessage = "/makeportal <end|nether>",
            permission = "gta.makeportal"
    )
    public boolean execute(Player sender, Arguments arguments) {
        if (!arguments.equals(1))
            return  true;

        String type = arguments.get(0);
        Selection selection = WorldEditUtils.getSelection(sender);
        List<Block> blocks;

        if (selection == null) {
            sender.sendMessage(ChatUtils.format(
                    "%sVous n'avez pas de selection WorldEdit.",
                    commandModule.prefix()
            ));
            return false;
        }

        blocks = WorldEditUtils.getBlocksInsideSel(selection);

        if (type.equals("end"))
            setBlocksType(blocks, Material.ENDER_PORTAL);
        else if (type.equals("nether"))
            setBlocksType(blocks, Material.PORTAL);
        else return true;

        sender.sendMessage(ChatUtils.format(
                "%sVous venez de créer un portail &5%s&7.",
                commandModule.prefix(),
                type.toLowerCase()
        ));
        return false;
    }

    private void setBlocksType(List<Block> blocks, Material material) {
        for (Block block : blocks)
            block.setType(material);
    }

}
