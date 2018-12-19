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

public class ASTDereference implements ASTNode {
    private final ASTNode reference;
    private RefType referenceType;

    public ASTDereference(ASTNode reference) {
        this.reference = reference;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        ReferenceValue ref = (ReferenceValue) reference.eval(environment);
        return ref.getValue();
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType t = reference.typecheck(environment);
        if (!(t instanceof RefType))
            throw new ASTNotReferenceException(t + " is not a reference.");

        referenceType = (RefType) t;
        return referenceType.getReferenceType();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Code finalCode = new Code()
                .addCode("; --- Begin ASTDereference ---")
                .addCode("")
                .addCode(reference.compile(environment));

        finalCode.addCode("getfield " + referenceType.getClassName()
                        + "/" + ReferenceClass.getValueName()
                        + " " + referenceType.getContentClassReference())
                .addCode("");

        if (referenceType.getReferenceType() instanceof RefType)
            finalCode.addCode("checkcast " + referenceType.getReferenceType().getClassName());

        return finalCode.addCode("; --- End ASTDereference ---");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
