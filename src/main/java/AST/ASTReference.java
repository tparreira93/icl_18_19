package AST;

import values.IValue;
import values.ReferenceValue;

public class ASTReference implements ASTNode {
    private final ASTNode value;

    public ASTReference(ASTNode value) {
        this.value = value;
    }
    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        return new ReferenceValue(value.eval(environment));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
