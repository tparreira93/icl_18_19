package AST;

import AST.Exceptions.ASTInvalidIdentifier;
import values.IValue;

public class ASTId implements ASTNode {
    private String name;

    public ASTId(String name) {
        this.name = name;
    }

    public IValue eval(ASTEnvironment environment) throws Exception {

        IValue value = environment.find(name);
        if (value == null)
            throw new ASTInvalidIdentifier("Unable to find definition for identifier \"" + name + "\".");
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
