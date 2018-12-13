package AST;

import AST.Exceptions.ASTNotReferenceException;
import AST.types.IType;
import AST.types.RefType;
import AST.values.IValue;
import AST.values.ReferenceValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTAssignment implements ASTNode {
    private final ASTNode id;
    private final ASTNode value;

    public ASTAssignment(ASTNode id, ASTNode value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        ReferenceValue reference = (ReferenceValue) id.eval(environment);
        reference.setValue(value.eval(environment));

        return reference;
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType ref = id.typecheck(environment);

        if (!(ref instanceof RefType))
            throw new ASTNotReferenceException(ref + " is not a reference!");

        RefType refType = (RefType) ref;
        IType v_type = value.typecheck(environment);

        refType.setReference(v_type);

        return refType;
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
