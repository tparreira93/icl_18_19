package AST;

import AST.types.IType;

public class Parameter {
    private final IType type;
    private final String name;

    public Parameter(String name, IType type) {
        this.type = type;
        this.name = name;
    }

    public IType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
