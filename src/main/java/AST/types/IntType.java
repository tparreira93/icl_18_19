package AST.types;

public class IntType extends NumericType implements IType, IValueType {
    private static final IntType instance = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }
}
