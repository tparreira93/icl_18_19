package AST;

import AST.Exceptions.ASTNonLogical;
import values.BoolValue;
import values.IValue;

public class ASTWhile implements ASTNode {
    private ASTNode condition;
    private ASTNode action;

    public ASTWhile(ASTNode condition, ASTNode action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue condition_value = condition.eval(environment);
        IValue result = null;
        boolean cond = (boolean) condition_value.getValue();

        if (!(condition_value instanceof BoolValue))
            throw new ASTNonLogical("While condition should be a boolean value! " + "(it is " + condition_value.getName() + ")");

        if (!cond)
            return condition_value;

        ASTEnvironment localScope = environment.beginScope();
        while ((boolean)condition_value.getValue())
        {
            result = action.eval(localScope);
            condition_value = condition.eval(environment);
        }
        localScope.endScope();

        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " Condition=" + condition + " Body=" + action;
    }
}
