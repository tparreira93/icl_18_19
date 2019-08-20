package values;

import exceptions.ASTNonComparableException;

public class BoolValue implements IValue<Boolean> {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return "boolean";
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public int compareTo(IValue<?> v) throws Exception {
        throw new ASTNonComparableException(this + " is not comparable!");
    }

    @Override
    public boolean equals(IValue<?> v) throws Exception {
        if (!(v instanceof BoolValue))
            throw new ASTNonComparableException("Can't compare " + this + " with " + v + ". (" + v + " is not a boolean value).");

        return value == (boolean)v.getValue();
    }

    @Override
    public String toString() {
        return "bool(" + (value ? "true" : "false") + ")";
    }
}
