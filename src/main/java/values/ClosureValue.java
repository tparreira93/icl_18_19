package values;

import utils.Environment;
import ast.ASTNode;
import exceptions.ASTNonComparableException;
import ast.Parameter;

import java.util.List;

public class ClosureValue implements IValue {
    private final List<Parameter> params;
    private final ASTNode expression;
    private final Environment<IValue> environment;

    public ClosureValue(List<Parameter> params, ASTNode expression, Environment<IValue> environment) {
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
        throw new ASTNonComparableException(this + " is not comparable!");
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        if (!(v instanceof ClosureValue))
            throw new ASTNonComparableException("Can't compare " + this + " with " + v + ". (" + v + " is not a function).");

        ClosureValue f = (ClosureValue) v;

        return this.getParams().equals(f.getParams())
                && this.getExpression().equals(f.getExpression())
                && this.getEnvironment().equals(f.getEnvironment());
    }

    public List<Parameter> getParams() {
        return params;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public Environment<IValue> getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "function(" + expression.toString() + ")";
    }
}
