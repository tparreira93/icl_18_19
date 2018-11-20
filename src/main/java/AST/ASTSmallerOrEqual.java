package AST;

import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;

public class ASTSmallerOrEqual extends ASTCompare implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public ASTSmallerOrEqual(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.compareTo(r) <= 0);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        return this.typecheck(environment, left, right);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
