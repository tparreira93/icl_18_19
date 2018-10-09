package values;

public class IntValue extends NumericValue {

    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
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
