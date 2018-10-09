package values;

public abstract class NumericValue implements IValue {
    public abstract NumericValue Sum(NumericValue value);
    public abstract NumericValue Subtract(NumericValue value);
    public abstract NumericValue Divide(NumericValue value);
    public abstract NumericValue Mult(NumericValue value);
}
