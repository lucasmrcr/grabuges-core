package fr.grabuges.api.exception;

public class AbstractRuntimeException extends RuntimeException {

    public AbstractRuntimeException(String txt, Object... objects) {
        super(String.format(txt, objects));
    }
}
