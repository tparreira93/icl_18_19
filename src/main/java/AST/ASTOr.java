package AST;

import values.BoolValue;
import values.IValue;

public class ASTOr implements ASTNode {
    private ASTNode left;
    private ASTNode right;

    public ASTOr(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);
        String msg = "Logical operators should evaluate to boolean values. (%s is not a boolean value).";

        if (!(l instanceof BoolValue))
            throw new Exception(String.format(msg, l));
        if (!(r instanceof BoolValue))
            throw new Exception(String.format(msg, r));

        return new BoolValue((boolean)l.getValue() || (boolean)r.getValue());
    }
}
