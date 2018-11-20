package AST;

import AST.Exceptions.ASTInvalidTypeException;
import AST.types.BoolType;
import AST.types.IType;
import AST.types.NumericType;

public abstract class ASTCompare implements ASTNode {
    public IType typecheck(ASTEnvironment<IType> environment, ASTNode left, ASTNode right) throws Exception {
        IType l = left.typecheck(environment);
        IType r = right.typecheck(environment);

        if (!(l instanceof NumericType && r instanceof NumericType))
            throw new ASTInvalidTypeException(l + " and " + r + " not comparable.");

        return BoolType.getInstance();
    }
}
