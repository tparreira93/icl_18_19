package AST;

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
        FunctionValue functionValue = (FunctionValue) function.eval(environment);
        ASTEnvironment localEnvironment = functionValue.getEnvironment();
        List<String> params = functionValue.getParams();

        if (params.size() != arguments.size())
            throw new Exception("Number of arguments does not match the function definition.");

        List<IValue> argumentsValue = new ArrayList<>();
        for (ASTNode anArgument : arguments) {
            IValue value = anArgument.eval(environment);

            if (value == null)
                throw new Exception("Unable to find definition for argument \"" + anArgument.toString() + "\".");

            argumentsValue.add(anArgument.eval(environment));
        }

        ASTEnvironment functionEnvironment = localEnvironment.beginScope();

        for (int i = 0; i < argumentsValue.size(); i++ )
            functionEnvironment.assoc(params.get(i), argumentsValue.get(i));

        IValue returnValue = functionValue.getExpression().eval(functionEnvironment);
        functionEnvironment.endScope();

        return returnValue;
    }
}
