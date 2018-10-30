package AST;

import values.IValue;
import values.NumericValue;

public class ASTPlus implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTPlus(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue v1 = left.eval(environment);
        IValue v2 = right.eval(environment);
        this.toString();
        if (v1 instanceof NumericValue && v2 instanceof NumericValue)
            return ((NumericValue) v1).Sum((NumericValue)v2);
        else
            throw new Exception("Plus operation is not supported between " + v1.getName() + " and " + v2.getName() + ".");
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ": " + left + " - " + right;
    }
}
