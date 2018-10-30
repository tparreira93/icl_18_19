package AST;

import values.FunctionValue;
import values.IValue;
import java.util.List;

public class ASTFun implements ASTNode {
    private List<String> params;
    private ASTNode expression;
    public ASTFun(List<String> params, ASTNode expression) {
        this.params = params;
        this.expression = expression;
    }
    @Override
    public IValue eval(ASTEnvironment environment) {
        return new FunctionValue(params, expression, environment);
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ": " + expression + " - " + String.join(", ", params);
    }
}
