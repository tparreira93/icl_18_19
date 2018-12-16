package AST;

import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTSmallerOrEqual extends ASTCompare implements ASTNode {

    public ASTSmallerOrEqual(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.compareTo(r) <= 0);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        return super.typecheck(environment);
    }


    @Override
    public Code compile(CompilerEnvironment environment) {
        return super.compile(environment, "ifle");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
