package AST;

import AST.types.BoolType;
import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTBool implements ASTNode {
    private final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) {
        return new BoolValue(value);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) {
        return BoolType.getInstance();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
