package types;

public class BoolType implements IType {
    private static final BoolType instance = new BoolType();

    private BoolType() {
    }

    public static BoolType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
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
        return "bool";
    }
}
