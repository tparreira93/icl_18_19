package types;

public class StringType implements IType {
    private static final StringType instance = new StringType();

    private StringType() {
    }

    public static StringType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String getClassName() {
        return getClassReference();
    }

    @Override
    public String getClassReference() {
        return "Ljava/lang/String;";
    }

    @Override
    public String getTypeName() {
        return "string";
    }

    @Override
    public String getReturnKeywork() {
        return "areturn";
    }

    @Override
    public String getLoadKeyword() {
        return "aload";
    }
}
