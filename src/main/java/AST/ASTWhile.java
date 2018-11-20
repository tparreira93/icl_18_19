package AST;

import AST.Exceptions.ASTNonLogicalException;
import AST.types.BoolType;
import AST.types.IType;
import AST.values.IValue;

public class ASTWhile implements ASTNode {
    private final ASTNode condition;
    private final ASTNode action;

    public ASTWhile(ASTNode condition, ASTNode action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        IValue condition_value = condition.eval(environment);

        ASTEnvironment<IValue> localScope = environment.beginScope();
        while ((boolean)condition_value.getValue())
        {
            action.eval(localScope);
            condition_value = condition.eval(environment);
        }
        localScope.endScope();

        return condition_value;
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType condition_type = condition.typecheck(environment);

        if (!(condition_type instanceof BoolType))
            throw new ASTNonLogicalException("While condition should evaluate to a bool type! " + "(it is " + condition_type + ")");

        ASTEnvironment<IType> localScope = environment.beginScope();

        action.typecheck(localScope);

        localScope.endScope();

        return condition_type;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
