package AST.values;

public class StringValue implements IValue {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    @Override
    public String getName() {
        return "string";
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(IValue v) {
        StringValue str = (StringValue) v;
        return this.value.compareTo(str.value);
    }

    @Override
    public boolean equals(IValue v) {
        StringValue str = (StringValue) v;
        return this.value.equals(str.value);
    }

    @Override
    public String toString() {
        return "string(" + value.toString() + ")";
    }
}
