package ast;

import types.IType;
import utils.Environment;
import values.BoolValue;
import values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTSmaller extends ASTCompare implements  ASTNode {

    public ASTSmaller(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        IValue l = left.eval(environment);
        IValue r = right.eval(environment);

        return new BoolValue(l.compareTo(r) < 0);
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        return super.typecheck(environment);
    }


    @Override
    public Code compile(CompilerEnvironment environment) {
        return super.compile(environment, "iflt");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
