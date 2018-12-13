package AST;

import AST.types.IType;
import AST.types.RefType;
import AST.values.IValue;
import AST.values.ReferenceValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTReference implements ASTNode {
    private final ASTNode value;

    public ASTReference(ASTNode value) {
        this.value = value;
    }
    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        return new ReferenceValue(value.eval(environment));
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType t = value.typecheck(environment);
        return new RefType(t);
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
