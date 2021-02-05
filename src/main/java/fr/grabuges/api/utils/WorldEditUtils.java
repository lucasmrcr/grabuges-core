package fr.grabuges.api.utils;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldEditUtils {

    public static List<Block> getBlocksInsideSel(Selection selection) {
        final List<Block> blocks = new ArrayList<>();
        final World world = selection.getWorld();
        final Location minLocation = selection.getMinimumPoint(), maxLocation = selection.getMaximumPoint();
        final int minX = minLocation.getBlockX(), minY = minLocation.getBlockY(), minZ = minLocation.getBlockZ(),
                maxX = maxLocation.getBlockX(), maxY = maxLocation.getBlockY(), maxZ = maxLocation.getBlockZ();

        assert(world != null);

        for (int x = minX; x <= maxX; x++)
            for (int y = minY; y <= maxY; y++)
                for (int z = minZ; z <= maxZ; z++)
                    blocks.add(world.getBlockAt(x, y, z));

        return blocks;
    }

    public static Selection getSelection(Player player) {
        WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        return worldEdit.getSelection(player);
    }

}
