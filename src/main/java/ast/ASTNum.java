package ast;

import types.IType;
import types.IntType;
import utils.Environment;
import values.IValue;
import values.IntValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTNum implements ASTNode {
    private final IntValue num;

    public ASTNum(int num) {
        this.num = new IntValue(num);
    }

    public IValue eval(Environment<IValue> environment) {
        return num;
    }

    @Override
    public IType typecheck(Environment<IType> environment) {
        return IntType.getInstance();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Code compile(CompilerEnvironment environment) {
        return new Code()
                .addCode("; --- BEGIN ASTNum ---")
                .addCode("sipush " + num.getValue())
                .addCode("; --- END ASTNum ---");
    }
}
