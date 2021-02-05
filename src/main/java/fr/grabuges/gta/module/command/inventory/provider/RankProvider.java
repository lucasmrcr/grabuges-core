package fr.grabuges.gta.module.command.inventory.provider;

import fr.grabuges.api.framework.builder.GeneralBuilder;
import fr.grabuges.api.utils.ChatUtils;
import fr.grabuges.gta.App;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class RankProvider implements InventoryProvider {

    private final OfflinePlayer target;

    public RankProvider(OfflinePlayer target) {
        this.target = target;
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        List<Group> groups = App.getInstance().getLuckPerms().getGroups()
                .stream()
                .sorted(Comparator.comparingInt(o -> o.getWeight().isPresent() ? o.getWeight().getAsInt() : -1))
                .collect(Collectors.toList());

        for (Group group : groups) {
            String prefix = group.getCachedData().getMetaData().getPrefix();

            ItemStack itemStack = new GeneralBuilder<>(new ItemStack(Material.IRON_CHESTPLATE)).build(item -> {
                item.setItemMeta(new GeneralBuilder<>(item.getItemMeta()).build(meta -> {
                    meta.setDisplayName(ChatUtils.format("&7» %s", prefix));

                    Map<String, Boolean> permissionMap = group.getCachedData().getPermissionData().getPermissionMap();
                    List<String> lore = new ArrayList<>(Arrays.asList(
                       "&7Voici une liste des permissions :",
                       "&r"
                    ));

                    List<String> collect = permissionMap.keySet().stream()
                            .map(permission -> (permissionMap.get(permission) ? "&a" : "&c") + " • " + permission)
                            .collect(Collectors.toList());

                    if (collect.size() >= 10) {
                        collect = collect.stream().limit(20).collect(Collectors.toList());
                        collect.add("&b • ...");
                    }

                    lore.addAll(collect);

                    lore.addAll(Arrays.asList(
                            "&r",
                            String.format("&7&l┃ &7&oCliquez pour attribuer le rôle %s &7&oà &b%s", prefix, target.getName())
                    ));

                    meta.setLore(lore.stream().map(s -> ChatUtils.format(s)).collect(Collectors.toList()));
                    return meta;
                }));
                return item;
            });

            contents.add(ClickableItem.empty(itemStack));
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
