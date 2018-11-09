package values;

import AST.Exceptions.ASTNonComparable;

public class ReferenceValue implements IValue<IValue> {
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
    public IValue getValue() {
        return value;
    }

    @Override
    public int compareTo(IValue v) throws Exception {
        throw new ASTNonComparable(this + " is not comparable!");
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        if (!(v instanceof ReferenceValue))
            throw new ASTNonComparable("Can't compare " + this + " with " + v + ". (" + v + " is not a reference).");

        return this == v;
    }

    @Override
    public String toString() {
        return "reference(" + value.toString() + ")";
    }
}
