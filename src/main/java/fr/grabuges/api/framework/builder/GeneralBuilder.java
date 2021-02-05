package fr.grabuges.api.framework.builder;

public class GeneralBuilder<T> {

    private final T object;

    public GeneralBuilder(T object) {
        this.object = object;
    }

    public T build(GeneralBuilderInterface<T> generalBuilderInterface) {
        return generalBuilderInterface.flat(object);
    }

}
