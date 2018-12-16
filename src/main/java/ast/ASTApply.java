package ast;

import exceptions.ASTDifferentTypeException;
import exceptions.ASTInvalidNumberOfArgumentsException;
import exceptions.ASTNotFunctionException;
import types.FunctionType;
import types.IType;
import utils.Environment;
import values.ClosureValue;
import values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

import java.util.List;

public class ASTApply implements ASTNode {
    private final ASTNode function;
    private final List<ASTNode> arguments;

    public ASTApply(ASTNode function, List<ASTNode> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        IValue value = function.eval(environment);

        ClosureValue functionValue = (ClosureValue) value;

        Environment<IValue> localEnvironment = functionValue.getEnvironment();
        Environment<IValue> functionEnvironment = localEnvironment.beginScope();

        for (int i = 0; i < arguments.size(); i++ )
            functionEnvironment.assoc(functionValue.getParams().get(i).getName(), arguments.get(i).eval(environment));

        IValue returnValue = functionValue.getExpression().eval(functionEnvironment);
        functionEnvironment.endScope();

        return returnValue;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType f = function.typecheck(environment);

        if (!(f instanceof FunctionType))
            throw new ASTNotFunctionException(f + " is not a function!");

        FunctionType functionType = (FunctionType)f;

        if (functionType.getArguments().size() != arguments.size())
            throw new ASTInvalidNumberOfArgumentsException("Number of arguments does not match the function definition.");

        for (int i = 0; i < arguments.size(); i++) {
            IType argType = arguments.get(i).typecheck(environment);
            IType expected = functionType.getArguments().get(i);

            if (!(argType.equals(expected)))
                throw new ASTDifferentTypeException("Argument type does not match the expected type. Expected " + expected + " but got " + argType + ").");
        }

        return functionType.getReturnType();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
