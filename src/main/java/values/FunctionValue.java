package values;

import AST.ASTEnvironment;
import AST.ASTNode;
import AST.Exceptions.ASTNonComparable;

import java.util.List;

public class FunctionValue implements IValue {
    private final List<String> params;
    private final ASTNode expression;
    private final ASTEnvironment environment;

    public FunctionValue(List<String> params, ASTNode expression, ASTEnvironment environment) {
        this.params = params;
        this.expression = expression;
        this.environment = environment;
    }

    @Override
    public String getName() {
        return "function";
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public int compareTo(IValue v) throws Exception {
        throw new ASTNonComparable(this + " is not comparable!");
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        if (!(v instanceof FunctionValue))
            throw new ASTNonComparable("Can't compare " + this + " with " + v + ". (" + v + " is not a function).");

        FunctionValue f = (FunctionValue) v;

        return this.getParams().equals(f.getParams())
                && this.getExpression().equals(f.getExpression())
                && this.getEnvironment().equals(f.getEnvironment());
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

    @Override
    public String toString() {
        return "function(" + expression.toString() + ")";

    }
}
