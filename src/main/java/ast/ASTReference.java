package ast;

import types.IType;
import types.RefType;
import utils.Environment;
import values.IValue;
import values.ReferenceValue;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import compiler.ReferenceClass;

public class ASTReference implements ASTNode {
    private final ASTNode value;
    private RefType value_type;

    public ASTReference(ASTNode value) {
        this.value = value;
    }
    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        return new ReferenceValue(value.eval(environment));
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        value_type = new RefType(value.typecheck(environment));
        return value_type;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Compiler compiler = Compiler.getInstance();
        ReferenceClass reference = compiler.newReference(value_type);
        String className = value_type.getClassName();
        compiler.addClassFile(reference);
        Code finalCode = new Code()
                .addCode("; --- BEGIN ASTReference ---")
                .addCode("new " + className)
                .addCode("dup")
                .addCode("invokespecial " + className + "/<init>()V")
                .addCode("dup")
                .addCode(value.compile(environment));

        if (value_type.getReferenceType() instanceof RefType)
            finalCode.addCode("checkcast " + value_type.getContentClassReference());

        finalCode.addCode("putfield " + className + "/" + ReferenceClass.getValueName() + " " + value_type.getContentClassReference());

        return finalCode
                .addCode("; --- END ASTReference ---");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
