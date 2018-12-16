package AST;

import AST.types.IType;
import AST.types.StringType;
import AST.values.IValue;
import AST.values.StringValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTString implements ASTNode {
    private final String value;

    public ASTString(String value) {
        this.value = value;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        return new StringValue(value);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        return StringType.getInstance();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return new Code().addCode("ldc \"" + value + "\"");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
