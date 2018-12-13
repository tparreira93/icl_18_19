package AST.types;

public class BoolType implements IType, IValueType {
    private static final BoolType instance = new BoolType();

    private BoolType() {
    }

    public static BoolType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }
}
