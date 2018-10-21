package AST;

import values.IValue;
import values.NumericValue;

public class ASTMul implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTMul(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(ASTEnvironment environment) throws Exception {
        NumericValue v1 = (NumericValue) left.eval(environment);
        NumericValue v2 = (NumericValue) right.eval(environment);

        return v1.Mult(v2);
    }
}
