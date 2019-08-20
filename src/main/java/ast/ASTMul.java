package ast;

import utils.Environment;
import values.IValue;
import values.NumericValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTMul extends ASTArithmetic {
    private static final String jasminMult = "imul";

    public ASTMul(ASTNode left, ASTNode right) {
        super(left, right);
    }

    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        IValue<?> v1 = left.eval(environment);
        IValue<?> v2 = right.eval(environment);

        return ((NumericValue<?>) v1).Mult((NumericValue<?>)v2);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return super.compile(environment, jasminMult);
    }
}
