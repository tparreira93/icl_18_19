package AST;

import AST.Exceptions.ASTNotReferenceException;
import AST.types.IType;
import AST.types.RefType;
import AST.values.IValue;
import AST.values.ReferenceValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTDereference implements ASTNode {
    private final ASTNode reference;

    public ASTDereference(ASTNode reference) {
        this.reference = reference;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        ReferenceValue ref = (ReferenceValue) reference.eval(environment);
        return ref.getValue();
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType t = reference.typecheck(environment);
        if (!(t instanceof RefType))
            throw new ASTNotReferenceException(t + " is not a reference.");

        RefType ref = (RefType) t;

        return ref.getReferenceType();
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
