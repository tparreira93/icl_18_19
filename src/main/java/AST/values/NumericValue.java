package AST.values;

public abstract class NumericValue<T> implements IValue<T> {
    public abstract NumericValue Sum(NumericValue value);
    public abstract NumericValue Subtract(NumericValue value);
    public abstract NumericValue Divide(NumericValue value);
    public abstract NumericValue Mult(NumericValue value);
}
