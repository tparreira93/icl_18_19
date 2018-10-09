package AST;

import values.IValue;
import values.NumericValue;

public class ASTDiv implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTDiv(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(ASTEnvironment environment) {
        NumericValue v1 = (NumericValue) left.eval(environment);
        NumericValue v2 = (NumericValue) right.eval(environment);

        return v1.Divide(v2);
    }
}
