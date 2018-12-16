package AST;

import AST.types.IType;
import AST.values.BoolValue;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTOr extends ASTLogicalOperator implements ASTNode {
    public ASTOr(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        BoolValue bl = (BoolValue) left.eval(environment);
        BoolValue br = (BoolValue) right.eval(environment);

        return new BoolValue(bl.getValue() || br.getValue());
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        return super.typecheck(environment);
    }

    @Override
    Code getCompiledOperator() {
        return new Code().addCode("ior");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
