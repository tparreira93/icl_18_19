package AST;

import values.IValue;

public class ASTId implements ASTNode {
    private String name;

    public ASTId(String name) {
        this.name = name;
    }

    public IValue eval(ASTEnvironment environment) {
        return environment.find(name);
    }
}
