package AST;

import values.BoolValue;
import values.IValue;

public class ASTBool implements ASTNode {
    private boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        return new BoolValue(value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + (value ? "true" : "false");
    }
}
