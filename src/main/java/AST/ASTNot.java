package AST;

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
            throw new Exception(String.format(msg, value));

        return new BoolValue(!(boolean) value.getValue());
    }
}
