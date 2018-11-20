package AST;

import AST.Exceptions.ASTNonLogicalException;
import AST.types.BoolType;
import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;

public class ASTNot implements ASTNode {
    private final ASTNode node;

    public ASTNot(ASTNode node) {
        this.node = node;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        BoolValue b = (BoolValue) node.eval(environment);

        return new BoolValue(!b.getValue());
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType value = node.typecheck(environment);
        String msg = "Logical operators can only be applied on boolean values. (%s is not a boolean value).";

        if (!(value instanceof BoolType))
            throw new ASTNonLogicalException(String.format(msg, value));

        return BoolType.getInstance();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
