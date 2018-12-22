package types;

public class AnonymousType implements IType, IAnonymousType {
    private static final AnonymousType instance = new AnonymousType();

    private AnonymousType() {
    }

    public static AnonymousType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

    @Override
    public String getClassName() {
        return getTypeName();
    }

    @Override
    public String getClassReference() {
        return "L" + getTypeName();
    }

    @Override
    public String getTypeName() {
        return "anonymous";
    }

    @Override
    public String getReturnKeywork() {
        return "areturn";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnonymousType)
            return true;

        return false;
    }

    @Override
    public String getLoadKeyword() {
        return "aload";
    }
}
