package AST;

import values.IValue;
import values.ReferenceValue;

public class ASTAssignment implements ASTNode {
    private ASTNode id;
    private ASTNode value;

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
        throw new Exception(ref + " is not a reference!");
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ": " + id.toString() + " := " + value.toString();
    }
}