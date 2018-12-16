package AST.types;

public class IntType extends NumericType implements IType {
    private static final IntType instance = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public String getClassName() {
        return getClassReference();
    }

    @Override
    public String getClassReference() {
        return "I";
    }

    @Override
    public String getTypeName() {
        return "int";
    }
}
