package ast;

import types.BoolType;
import types.IType;
import utils.Environment;
import values.BoolValue;
import values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTBool implements ASTNode {
    private final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> environment) {
        return new BoolValue(value);
    }

    @Override
    public IType typecheck(Environment<IType> environment) {
        return BoolType.getInstance();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return new Code().addCode("sipush " + (value ? "1" : "0"));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
