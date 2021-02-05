package fr.grabuges.api.command.exception;

import fr.grabuges.api.exception.AbstractRuntimeException;

public class CommandRuntimeException extends AbstractRuntimeException {

    public CommandRuntimeException(String txt, Object... objects) {
        super(txt, objects);
    }
}
