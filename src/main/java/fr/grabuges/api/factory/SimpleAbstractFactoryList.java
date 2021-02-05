package fr.grabuges.api.factory;

import fr.grabuges.api.factory.exception.FactoryRuntimeException;

import java.util.ArrayList;
import java.util.List;

public class SimpleAbstractFactoryList<T> {

    private final List<T> objects;

    protected SimpleAbstractFactoryList() {
        objects = new ArrayList<>();
    }

    public void add(T object) {
        if (objects.contains(object))
            throw new FactoryRuntimeException("Object (%s) already in object list.", object.getClass().getSimpleName());

        objects.add(object);
    }

    public void remove(T object) {
        objects.remove(object);
    }

    public void clear() {
        objects.clear();
    }

    public List<T> get() {
        return objects;
    }


}
