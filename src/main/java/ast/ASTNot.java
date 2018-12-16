package ast;

import exceptions.ASTNonLogicalException;
import types.BoolType;
import types.IType;
import utils.Environment;
import values.BoolValue;
import values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTNot implements ASTNode {
    private final ASTNode node;

    public ASTNot(ASTNode node) {
        this.node = node;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        BoolValue b = (BoolValue) node.eval(environment);

        return new BoolValue(!b.getValue());
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType value = node.typecheck(environment);
        String msg = "Logical operators can only be applied on boolean values. (%s is not a boolean value).";

        if (!(value instanceof BoolType))
            throw new ASTNonLogicalException(String.format(msg, value));

        return BoolType.getInstance();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
