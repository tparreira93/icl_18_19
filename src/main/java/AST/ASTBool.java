package AST;

import values.BoolValue;
import values.IValue;

public class ASTBool implements ASTNode {
    private final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public IValue eval(ASTEnvironment environment) {
        return new BoolValue(value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
