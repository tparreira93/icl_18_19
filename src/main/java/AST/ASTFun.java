package AST;

import AST.Exceptions.ASTDuplicateNameException;
import AST.types.FunctionType;
import AST.types.IType;
import AST.values.ClosureValue;
import AST.values.IValue;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ASTFun implements ASTNode {
    private final List<Parameter> params;
    private final ASTNode expression;
    public ASTFun(List<Parameter> params, ASTNode expression) {
        this.params = params;
        this.expression = expression;
    }
    @Override
    public IValue eval(ASTEnvironment<IValue> environment) {
        return new ClosureValue(params, expression, environment);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        HashSet<String> tmp = new HashSet<>();
        for (Parameter param: params) {
            if(tmp.contains(param.getName()))
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed");

            tmp.add(param.getName());
        }
        ASTEnvironment<IType> localScope = environment.beginScope();
        for (Parameter par : params) {
            localScope.assoc(par.getName(), par.getType());
        }
        IType returnType = expression.typecheck(localScope);

        FunctionType functionType = new FunctionType(params.stream().map(Parameter::getType).collect(Collectors.toList()),
                returnType);

        localScope.endScope();

        return functionType;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
