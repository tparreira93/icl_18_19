package AST;

import AST.Exceptions.ASTNonLogicalException;
import AST.types.BoolType;
import AST.types.IType;

public abstract class ASTLogicalOperator implements ASTNode {

    public IType typecheck(ASTEnvironment<IType> environment, ASTNode left, ASTNode right) throws Exception {
        IType l = left.typecheck(environment);
        IType r = right.typecheck(environment);

        String msg = "Logical operators can only be applied on boolean values. (%s is not a boolean value).";

        if (!(l instanceof BoolType))
            throw new ASTNonLogicalException(String.format(msg, l));
        if (!(r instanceof BoolType))
            throw new ASTNonLogicalException(String.format(msg, r));

        return BoolType.getInstance();
    }
}
