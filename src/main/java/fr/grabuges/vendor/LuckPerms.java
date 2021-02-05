package fr.grabuges.vendor;

import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Set;

public class LuckPerms {

    private final net.luckperms.api.LuckPerms luckPerms;

    public LuckPerms(net.luckperms.api.LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    public String getPrefix(Player player) {
        return getPrefix(player.getName());
    }

    public String getPrefix(String player) {
        User user = luckPerms.getUserManager().getUser(player);
        Group group = luckPerms.getGroupManager().getGroup(Objects.requireNonNull(user).getPrimaryGroup());
        return Objects.requireNonNull(group).getCachedData().getMetaData(QueryOptions.nonContextual()).getPrefix();
    }

    public Set<Group> getGroups() {
        return luckPerms.getGroupManager().getLoadedGroups();
    }

}
