package AST;

import AST.Exceptions.ASTNonLogicalException;
import AST.types.BoolType;
import AST.types.IType;
import compiler.Code;
import compiler.CompilerEnvironment;

public abstract class ASTLogicalOperator implements ASTNode {
    protected final ASTNode left;
    protected final ASTNode right;

    protected ASTLogicalOperator(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType l = left.typecheck(environment);
        IType r = right.typecheck(environment);

        String msg = "Logical operators can only be applied on boolean values. (%s is not a boolean value).";

        if (!(l instanceof BoolType))
            throw new ASTNonLogicalException(String.format(msg, l));
        if (!(r instanceof BoolType))
            throw new ASTNonLogicalException(String.format(msg, r));

        return BoolType.getInstance();
    }

    abstract Code getCompiledOperator();

    @Override
    public Code compile(CompilerEnvironment environment) {
        Code c = new Code();
        c.addCode(left.compile(environment));
        c.addCode(right.compile(environment));
        c.addCode(getCompiledOperator());
        return c;
    }
}
