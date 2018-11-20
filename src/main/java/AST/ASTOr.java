package AST;

import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;

public class ASTOr extends ASTLogicalOperator implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public ASTOr(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        BoolValue bl = (BoolValue) left.eval(environment);
        BoolValue br = (BoolValue) right.eval(environment);

        return new BoolValue(bl.getValue() || br.getValue());
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
