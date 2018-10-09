package values;

import AST.ASTEnvironment;
import AST.ASTNode;

public class FunctionValue implements IValue {
    private String id_param;
    private ASTNode expression;
    private ASTEnvironment environment;

    public FunctionValue(String id_param, ASTNode expression, ASTEnvironment environment) {
        this.id_param = id_param;
        this.expression = expression;
        this.environment = environment;
    }

    @Override
    public Object getValue() {
        return null;
    }

    public String getId_param() {
        return id_param;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public ASTEnvironment getEnvironment() {
        return environment;
    }
}
