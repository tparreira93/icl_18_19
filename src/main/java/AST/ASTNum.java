package AST;

import AST.types.IType;
import AST.types.IntType;
import AST.values.IValue;
import AST.values.IntValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTNum implements ASTNode {
    private final IntValue num;

    public ASTNum(int num) {
        this.num = new IntValue(num);
    }

    public IValue eval(ASTEnvironment<IValue> environment) {
        return num;
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) {
        return IntType.getInstance();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Code compile(CompilerEnvironment environment) {
        return new Code().addCode("; ASTNum").addCode("sipush " + num.getValue());
    }
}
