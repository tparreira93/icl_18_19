package AST;

import AST.Exceptions.ASTNotReference;
import values.IValue;
import values.ReferenceValue;

public class ASTAssignment implements ASTNode {
    private final ASTNode id;
    private final ASTNode value;

    public ASTAssignment(ASTNode id, ASTNode value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue ref = id.eval(environment);
        if (ref instanceof ReferenceValue) {
            ReferenceValue reference = (ReferenceValue) ref;

            reference.setValue(value.eval(environment));

            return reference;
        }
        throw new ASTNotReference(ref + " is not a reference!");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
