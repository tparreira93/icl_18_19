package AST;

import AST.Exceptions.ASTDifferentTypeException;
import AST.types.BoolType;
import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;

public class ASTEqual implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public ASTEqual(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.equals(r));
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType l = left.typecheck(environment);
        IType r = right.typecheck(environment);

        if (!(l.equals(r)))
            throw new ASTDifferentTypeException(l + " and " + r + " should be of the same type.");

        return BoolType.getInstance();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
