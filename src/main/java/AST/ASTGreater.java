package AST;

import values.BoolValue;
import values.IValue;

public class ASTGreater implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTGreater(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.compareTo(r) > 0);
    }
}
