package AST;

import AST.Exceptions.ASTNonLogical;
import values.BoolValue;
import values.IValue;

public class ASTNot implements ASTNode {
    private ASTNode node;

    public ASTNot(ASTNode node) {
        this.node = node;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue value = node.eval(environment);
        String msg = "Logical operators should evaluate to boolean values. (%s is not a boolean value).";

        if (!(value instanceof BoolValue))
            throw new ASTNonLogical(String.format(msg, value));

        BoolValue b = (BoolValue) value;

        return new BoolValue(!b.getValue());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " Not=" + node;
    }
}
