package AST;

import AST.types.IType;
import AST.types.NumericType;
import AST.values.IValue;
import AST.values.NumericValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTPlus extends ASTArithmetic {
    private static final String jasminSum = "iadd";

    public ASTPlus(ASTNode left, ASTNode right) {
        super(left, right);
    }

    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        IValue v1 = left.eval(environment);
        IValue v2 = right.eval(environment);

        return ((NumericValue) v1).Sum((NumericValue) v2);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return super.compile(environment, jasminSum);
    }
}
