package AST;

import AST.Exceptions.ASTNonLogical;
import values.BoolValue;
import values.IValue;

public class ASTIfThenElse implements ASTNode {
    private final ASTNode node_if;
    private final ASTNode node_then;
    private final ASTNode node_else;

    public ASTIfThenElse(ASTNode node_if, ASTNode node_then, ASTNode node_else) {
        this.node_if = node_if;
        this.node_then = node_then;
        this.node_else = node_else;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue if_value = node_if.eval(environment);
        IValue result;
        if (!(if_value instanceof BoolValue))
            throw new ASTNonLogical("If condition should be a boolean value! " + "(it is " + if_value.getName() + ")");
        BoolValue ifb = (BoolValue) if_value;

        if (ifb.getValue())
        {
            ASTEnvironment localScope = environment.beginScope();
            result = node_then.eval(localScope);
            localScope.endScope();
        }
        else
        {
            ASTEnvironment localScope = environment.beginScope();
            result = node_else.eval(localScope);
            localScope.endScope();
        }

        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
