package AST;

import AST.Exceptions.ASTNonLogical;
import values.BoolValue;
import values.IValue;

public class ASTAnd implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public ASTAnd(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);
        String msg = "Logical operators can only be applied on boolean values. (%s is not a boolean value).";

        if (!(l instanceof BoolValue))
            throw new ASTNonLogical(String.format(msg, l));
        if (!(r instanceof BoolValue))
            throw new ASTNonLogical(String.format(msg, r));

        BoolValue bl = (BoolValue)l;
        BoolValue br = (BoolValue)r;

        return new BoolValue(bl.getValue() && br.getValue());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
