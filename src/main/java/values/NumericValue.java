package values;

public abstract class NumericValue<T> implements IValue<T> {
    public abstract NumericValue<T> Sum(NumericValue<?> value);
    public abstract NumericValue<T> Subtract(NumericValue<?> value);
    public abstract NumericValue<T> Divide(NumericValue<?> value);
    public abstract NumericValue<T> Mult(NumericValue<?> value);
}
