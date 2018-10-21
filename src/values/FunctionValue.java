package values;

import AST.ASTEnvironment;
import AST.ASTNode;
import java.util.List;

public class FunctionValue implements IValue {
    private List<String> params;
    private ASTNode expression;
    private ASTEnvironment environment;

    public FunctionValue(List<String> params, ASTNode expression, ASTEnvironment environment) {
        this.params = params;
        this.expression = expression;
        this.environment = environment;
    }

    @Override
    public Object getValue() {
        return null;
    }

    public List<String> getParams() {
        return params;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public ASTEnvironment getEnvironment() {
        return environment;
    }
}
