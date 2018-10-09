package AST;

import values.FunctionValue;
import values.IValue;

public class ASTFun implements ASTNode {
    private String id_param;
    private ASTNode expression;
    public ASTFun(String id_param, ASTNode expression) {
        this.id_param = id_param;
        this.expression = expression;
    }
    @Override
    public IValue eval(ASTEnvironment environment) {
        return new FunctionValue(id_param, expression, environment);
    }
}
