package AST;

import values.BoolValue;
import values.NothingValue;
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
        IValue result = new NothingValue();

        if (!(condition_value instanceof BoolValue))
            throw new Exception("While condition should be a boolean value! " + "(it is " + condition_value.getName() + ")");

        while ((boolean)condition_value.getValue())
        {
            ASTEnvironment localScope = environment.beginScope();
            action.eval(localScope);
            condition_value = condition.eval(environment);
            localScope.endScope();
        }

        return result;
    }
}
