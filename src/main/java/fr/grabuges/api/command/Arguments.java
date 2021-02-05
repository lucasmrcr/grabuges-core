package fr.grabuges.api.command;

import fr.grabuges.api.command.exception.CommandRuntimeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arguments {

    private final String[] arguments;

    public Arguments(String[] arguments) {
        this.arguments = arguments;
    }

    public String get(int index) {
        if (index > arguments.length-1 || index < 0)
            throw new CommandRuntimeException("Index (%d) out of range [0, %d]", index, arguments.length-1);

        else return arguments[index];
    }

    public boolean equals(int size) { return size() == size; }
    public boolean moreThan(int size) { return size() > size; }
    public boolean moreOrEqualsThan(int size) { return size() >= size; }
    public boolean lessThan(int size) { return size() < size; }
    public boolean lessOrEqualsThan(int size) { return size() <= size; }

    public int size() { return arguments.length; }

    public String buildFrom(int index) {
        if (index > arguments.length-1 || index < 0)
            throw new CommandRuntimeException("Index (%d) out of range [0, %d]", index, arguments.length-1);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = index; i < arguments.length; i++)
            stringBuilder.append(arguments[i]).append(" ");

        return stringBuilder.toString();
    }

    public Arguments cloneSubArguments() {
        List<String> arguments = new ArrayList<>(Arrays.asList(this.arguments));
        arguments.remove(0);

        return new Arguments(arguments.toArray(new String[0]));
    }
}
