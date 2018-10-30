package AST;

import values.IValue;
import values.NumericValue;

public class ASTSub implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTSub(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue v1 = left.eval(environment);
        IValue v2 = right.eval(environment);

        if (v1 instanceof NumericValue && v2 instanceof NumericValue)
            return ((NumericValue) v1).Subtract((NumericValue)v2);
        else
            throw new Exception("Subtract operation is not supported between " + v1.getName() + " and " + v2.getName() + ".");
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ": " + left + " - " + right;
    }
}
