package AST;

import AST.Exceptions.ASTDuplicateNameException;
import values.FunctionValue;
import values.IValue;

import java.util.HashSet;
import java.util.List;

public class ASTFun implements ASTNode {
    private List<String> params;
    private ASTNode expression;
    public ASTFun(List<String> params, ASTNode expression) {
        this.params = params;
        this.expression = expression;
    }
    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        HashSet<String> tmp = new HashSet<>();
        for (String str: params) {
            if(tmp.contains(str))
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed");

            tmp.add(str);
        }

        return new FunctionValue(params, expression, environment);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + expression + " - " + String.join(", ", params);
    }
}
