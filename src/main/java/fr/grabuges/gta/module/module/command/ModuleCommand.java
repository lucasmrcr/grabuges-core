package fr.grabuges.gta.module.module.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.grabuges.api.module.Module;
import org.bukkit.entity.Player;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.command.Arguments;
import fr.grabuges.api.command.annotation.Command;
import fr.grabuges.api.utils.ChatUtils;
import fr.grabuges.gta.App;
import fr.grabuges.gta.module.module.CoreModule;
import fr.liskoh.basics.modules.ModuleManager;

public class ModuleCommand extends AbstractCommand {

    private final CoreModule coreModule;

    public ModuleCommand(CoreModule coreModule) {
        this.coreModule = coreModule;
    }

    @Override
    @Command(
            name = "modules",
            helpMessage = "/module [module]",
            permission = "gta.modules",
            aliases = {"plugins", "pl", "bukkit:pl", "bukkit:plugins", "module"}
    )
    public boolean execute(Player sender, Arguments arguments) {
        List<Object> modules = new ArrayList<>(ModuleManager.getInstance().getModules());
        modules.addAll(App.getInstance().getModuleLoader().getModules());

        modules.sort((o1, o2) -> {
            if (o1 instanceof fr.liskoh.api.modules.Module && o2 instanceof fr.liskoh.api.modules.Module) {
                if (!((fr.liskoh.api.modules.Module) o1).isEnabled() && ((fr.liskoh.api.modules.Module) o2).isEnabled())
                    return 1;
                else if (((fr.liskoh.api.modules.Module) o1).isEnabled() && !((fr.liskoh.api.modules.Module) o2).isEnabled())
                    return -1;
                else return 0;
            } else if (o1 instanceof Module && o2 instanceof fr.liskoh.api.modules.Module) {
                return -1;
            } else if (o1 instanceof fr.liskoh.api.modules.Module && o2 instanceof Module) {
                return 1;
            } else {
                return 0;
            }
        });

        if (arguments.equals(0) || arguments.get(0).equalsIgnoreCase("")) {
            ChatUtils.sendMessage(
                    sender,
                    "%sVoici la liste des &bmodules &7:\n%s",
                    coreModule.prefix(),
                    modules.stream().map(module -> {
                        if (module instanceof fr.grabuges.api.module.Module)
                            return String.format(
                                    "» &b%s &7(Core) — &aActif",
                                    module.getClass().getSimpleName()
                            );

                        return String.format(
                                "» &b%s &7(Basics) — %s",
                                ((fr.liskoh.api.modules.Module) module).getName(),
                                ((fr.liskoh.api.modules.Module) module).isEnabled() ? "&aActif" : "&cInactif"
                        );
                    }).collect(Collectors.joining("\n&7"))
            );
        } else {
            String moduleName = arguments.get(0);
            fr.liskoh.api.modules.Module basicsModule = ModuleManager.getInstance().getModuleByName(moduleName);
            Optional<Module> gtaModule = App.getInstance().getModuleLoader().getModule(moduleName);

            if (basicsModule != null && basicsModule.isEnabled()) {
                basicsModule.disable();
                ChatUtils.sendMessage(
                    sender,
                    "%s%s a été &cdésactivé&7.",
                    coreModule.prefix(),
                    basicsModule.getName()
                );
            } else if (basicsModule != null && !basicsModule.isEnabled()) {
                basicsModule.enable();
                ChatUtils.sendMessage(
                    sender,
                    "%s%s a été &aactivé&7.",
                    coreModule.prefix(),
                    basicsModule.getName()
                );
            } else {
                ChatUtils.sendMessage(
                    sender,
                    "%sLe module (&b%s&7) est &cintrouvable&7.",
                    coreModule.prefix(),
                    basicsModule.getName()
                );
            }
        }
        return false;
    }
}
