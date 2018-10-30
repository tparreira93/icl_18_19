package values;

public class ReferenceValue implements IValue {
    private IValue value;

    public ReferenceValue(IValue value) {
        this.value = value;
    }

    public void setValue(IValue value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return "reference";
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
        if (!(v instanceof ReferenceValue))
            throw new Exception("Can't compare " + this + " with " + v + ". (" + v + " is not an int value).");

        return value.equals(v);
    }

    @Override
    public String toString() {
        return "Reference: " + value.toString();
    }
}
