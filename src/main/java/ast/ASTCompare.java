package ast;

import exceptions.ASTInvalidTypeException;
import types.BoolType;
import types.IType;
import types.NumericType;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import utils.Environment;

public abstract class ASTCompare implements ASTNode {
    protected ASTNode left;
    protected ASTNode right;

    public ASTCompare(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IType typecheck(Environment<IType> environment) throws Exception {
        IType l = left.typecheck(environment);
        IType r = right.typecheck(environment);

        if (!(l instanceof NumericType && r instanceof NumericType))
            throw new ASTInvalidTypeException(l + " and " + r + " not comparable.");

        return BoolType.getInstance();
    }

    public Code compile(CompilerEnvironment environment, String comparison) {
        Compiler compiler = Compiler.getInstance();
        String l1 = compiler.generateLabel();
        String l2 = compiler.generateLabel();
        return new Code()
                .addCode(left.compile(environment))
                .addCode(right.compile(environment))
                .addCode("isub")
                .addCode(comparison + " " + l1)
                .addCode(new ASTBool(false).compile(environment))
                .addCode("goto " + l2)
                .addCode(l1 + ":")
                .addCode(new ASTBool(true).compile(environment))
                .addCode(l2 + ":");
    }
}
