package ast;

import types.IType;
import types.NumericType;
import compiler.Code;
import compiler.CompilerEnvironment;
import utils.Environment;

public abstract class ASTArithmetic implements ASTNode {
    protected final ASTNode left;
    protected final ASTNode right;

    protected ASTArithmetic(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    protected Code compile(CompilerEnvironment environment, String operation) {
        Code code = new Code();
        code.addCode(left.compile(environment));
        code.addCode(right.compile(environment));
        code.addCode(operation);
        return code;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        return NumericType.typecheck(environment, left, right);
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }
}
