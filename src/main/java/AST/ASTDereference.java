package AST;

import AST.Exceptions.ASTNotReference;
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
            throw new ASTNotReference(value + " is not a reference.");
        ReferenceValue ref = (ReferenceValue) value;
        return ref.getValue();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + reference.toString();
    }
}
