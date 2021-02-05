package fr.grabuges.api.framework.builder;

@FunctionalInterface
public interface GeneralBuilderInterface<T> {

    T flat(T object);

}
