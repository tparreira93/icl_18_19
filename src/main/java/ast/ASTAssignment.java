package ast;

import exceptions.ASTNotReferenceException;
import types.IType;
import types.RefType;
import utils.Environment;
import values.IValue;
import values.ReferenceValue;
import compiler.Code;
import compiler.CompilerEnvironment;
import compiler.ReferenceClass;

public class ASTAssignment implements ASTNode {
    private final ASTNode id;
    private final ASTNode value;
    private RefType idType;
    private IType valueType;

    public ASTAssignment(ASTNode id, ASTNode value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        ReferenceValue reference = (ReferenceValue) id.eval(environment);
        reference.setValue(value.eval(environment));

        return reference;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType ref = id.typecheck(environment);

        if (!(ref instanceof RefType))
            throw new ASTNotReferenceException(ref + " is not a reference!");

        idType = (RefType) ref;
        valueType = value.typecheck(environment);

        idType.setReference(valueType);

        return idType;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return new Code()
                .addCode("; --- BEGIN ASTAssignment ---")
                .addCode(id.compile(environment))
                .addCode("checkcast " + idType.getClassName())
                .addCode(value.compile(environment))
                .addCode("dup")
                .addCode("putfield " + idType.getClassName() + "/" + ReferenceClass.getValueName()
                    + " " + idType.getContentClassReference())
                .addCode("; --- END ASTAssignment ---");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
