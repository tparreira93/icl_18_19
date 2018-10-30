package AST;

import values.IValue;

import java.util.List;

public class ASTSequence implements ASTNode{
    private List<ASTNode> sequence;

    public ASTSequence(List<ASTNode> sequence) {
        this.sequence = sequence;
    }

    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        for (int i = 0; i < sequence.size() - 1; i++) {
            sequence.get(i).eval(environment);
        }
        return sequence.get(sequence.size() - 1).eval(environment);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sequence.size(); i++) {
            if (str.length() == 0)
                str.append("->");
            str.append(sequence.get(i));
        }
        return this.getClass().getCanonicalName() + ": " +  str.toString();
    }
}
