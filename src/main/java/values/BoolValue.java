package values;

public class BoolValue implements IValue {
    private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return "boolean";
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(IValue v) throws Exception {
        throw new Exception(this + " is not comparable!");
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        if (!(v instanceof BoolValue))
            throw new Exception("Can't compare " + this + " with " + v + ". (" + v + " is not a boolean value).");

        return value == (boolean)v.getValue();
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }
}
