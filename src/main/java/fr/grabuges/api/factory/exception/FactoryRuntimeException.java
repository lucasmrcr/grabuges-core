package fr.grabuges.api.factory.exception;

import fr.grabuges.api.exception.AbstractRuntimeException;

public class FactoryRuntimeException extends AbstractRuntimeException {

    public FactoryRuntimeException(String txt, Object... objects) {
        super(txt, objects);
    }
}
