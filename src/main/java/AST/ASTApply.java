package AST;

import AST.Exceptions.ASTInvalidNumberOfArguments;
import AST.Exceptions.ASTNotFunction;
import values.FunctionValue;
import values.IValue;

import java.util.ArrayList;
import java.util.List;

public class ASTApply implements ASTNode {
    private ASTNode function;
    private List<ASTNode> arguments;

    public ASTApply(ASTNode function, List<ASTNode> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        IValue value = function.eval(environment);

        if (!(value instanceof FunctionValue))
            throw new ASTNotFunction(value + " is not a function!");

        FunctionValue functionValue = (FunctionValue) value;
        ASTEnvironment localEnvironment = functionValue.getEnvironment();

        if (functionValue.getParams().size() != arguments.size())
            throw new ASTInvalidNumberOfArguments("Number of arguments does not match the function definition.");

        ASTEnvironment functionEnvironment = localEnvironment.beginScope();

        for (int i = 0; i < arguments.size(); i++ )
            functionEnvironment.assoc(functionValue.getParams().get(i), arguments.get(i).eval(environment));

        IValue returnValue = functionValue.getExpression().eval(functionEnvironment);
        functionEnvironment.endScope();

        return returnValue;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + function + "-" + String.join(", ", arguments.toString());
    }
}
