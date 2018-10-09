package AST;

import values.FunctionValue;
import values.IValue;

public class ASTApply implements ASTNode {
    private ASTNode function;
    private ASTNode argument;

    public ASTApply(ASTNode function, ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }

    @Override
    public IValue eval(ASTEnvironment environment) {
        FunctionValue functionValue = (FunctionValue) function.eval(environment);
        IValue argumentValue = argument.eval(environment);
        ASTEnvironment localEnvironment = functionValue.getEnvironment();

        ASTEnvironment functionEnvironment = localEnvironment.beginScope();
        functionEnvironment.assoc(functionValue.getId_param(), argumentValue);
        IValue returnValue = functionValue.getExpression().eval(functionEnvironment);
        functionEnvironment.endScope();

        return returnValue;
    }
}
