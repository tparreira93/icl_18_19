package AST;

import values.IValue;
import values.IntValue;

public class ASTNum implements ASTNode {
    private IntValue num;

    public ASTNum(int num) {
        this.num = new IntValue(num);
    }

    public IValue eval(ASTEnvironment environment) {
        return num;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + num;
    }
}
