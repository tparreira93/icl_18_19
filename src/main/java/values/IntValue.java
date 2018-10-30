package values;

public class IntValue extends NumericValue {

    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return "int";
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(IValue v) throws Exception {
        if (!(v instanceof IntValue))
            throw new Exception("Can't compare " + this + " with " + v + ". (" + v + " is not an int value).");

        int other = (int) v.getValue();

        if (this.value < other)
            return -1;
        else if (this.value > other)
            return 1;

        return 0;
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        int c = compareTo(v);

        return c == 0;
    }

    @Override
    public NumericValue Sum(NumericValue value) {
        return new IntValue(this.value + (int)value.getValue());
    }

    @Override
    public NumericValue Subtract(NumericValue value) {
        return new IntValue(this.value - (int)value.getValue());
    }

    @Override
    public NumericValue Divide(NumericValue value) {
        return new IntValue(this.value / (int)value.getValue());
    }

    @Override
    public NumericValue Mult(NumericValue value) {
        return new IntValue(this.value * (int)value.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
