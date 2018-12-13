package AST.types;

public class StringType implements IType, IValueType {
    private static final StringType instance = new StringType();

    private StringType() {
    }

    public static StringType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }
}
