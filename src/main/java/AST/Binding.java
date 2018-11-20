package AST;

import AST.types.IType;

public class Binding {
    private final String id;
    private final ASTNode expression;
    private final IType type;

    public Binding(String id, ASTNode expression, IType type) {
        this.id = id;
        this.expression = expression;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public ASTNode getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return id + "=" + expression;
    }

    public IType getType() {
        return type;
    }
}
