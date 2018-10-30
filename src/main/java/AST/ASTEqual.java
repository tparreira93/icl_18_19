package AST;

import values.BoolValue;
import values.IValue;

public class ASTEqual implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTEqual(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.equals(r));
    }
}
