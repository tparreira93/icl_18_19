package AST;

import AST.types.IType;
import AST.types.NumericType;
import AST.values.IValue;
import AST.values.NumericValue;

public class ASTDiv implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public ASTDiv(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        IValue v1 = left.eval(environment);
        IValue v2 = right.eval(environment);

        return ((NumericValue) v1).Divide((NumericValue)v2);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        return NumericType.typecheck(environment, left, right);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
