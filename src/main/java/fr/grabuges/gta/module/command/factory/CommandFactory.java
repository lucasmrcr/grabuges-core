package fr.grabuges.gta.module.command.factory;

import fr.grabuges.api.command.AbstractCommand;
import fr.grabuges.api.factory.SimpleAbstractFactoryMap;

public class CommandFactory extends SimpleAbstractFactoryMap<String, AbstractCommand[]> {

    private CommandFactory() { super(); }
    private static final CommandFactory instance = new CommandFactory();
    public static CommandFactory getInstance() {
        return instance;
    }

}
