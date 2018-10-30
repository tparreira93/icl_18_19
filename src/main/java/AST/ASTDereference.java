package AST;

import values.IValue;
import values.ReferenceValue;

public class ASTDereference implements ASTNode {
    private ASTNode reference;

    public ASTDereference(ASTNode reference) {
        this.reference = reference;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue value = reference.eval(environment);
        if (!(value instanceof ReferenceValue))
            throw new Exception(value + " is not a reference.");

        return (IValue) value.getValue();
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ": " + reference.toString();
    }
}
