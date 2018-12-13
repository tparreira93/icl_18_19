package AST;

import AST.types.IType;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

import java.util.List;

public class ASTSequence implements ASTNode{
    private final List<ASTNode> sequence;

    public ASTSequence(List<ASTNode> sequence) {
        this.sequence = sequence;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        for (int i = 0; i < sequence.size() - 1; i++) {
            sequence.get(i).eval(environment);
        }
        return sequence.get(sequence.size() - 1).eval(environment);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        for (int i = 0; i < sequence.size() - 1; i++) {
            sequence.get(i).typecheck(environment);
        }
        return sequence.get(sequence.size() - 1).typecheck(environment);
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
