package ast;

import exceptions.ASTDifferentTypeException;
import types.BoolType;
import types.IType;
import utils.Environment;
import values.BoolValue;
import values.IValue;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;

public class ASTEqual implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;
    private IType leftType;
    private IType rightType;

    public ASTEqual(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.equals(r));
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        leftType = left.typecheck(environment);
        rightType = right.typecheck(environment);

        if (!(leftType.equals(rightType)))
            throw new ASTDifferentTypeException(leftType + " and " + rightType + " are not of the same type.");

        return BoolType.getInstance();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Compiler compiler = Compiler.getInstance();
        String l1 = compiler.generateLabel();
        String l2 = compiler.generateLabel();
        return new Code()
                .addCode("; --- BEGIN ASTEqual ---")
                .addCode(left.compile(environment))
                .addCode(right.compile(environment))
                .addCode("isub")
                .addCode("ifeq " + l1)
                .addCode(new ASTBool(false).compile(environment))
                .addCode("goto " + l2)
                .addCode(l1 + ":")
                .addCode(new ASTBool(true).compile(environment))
                .addCode(l2 + ":")
                .addCode("; --- END ASTEqual ---");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
