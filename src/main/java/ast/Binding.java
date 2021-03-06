package ast;

import types.IType;

public class Binding extends SimpleBinding<ASTNode> {
    private IType type;

    public Binding(String id, ASTNode expression, IType type) {
        super(id, expression);
        this.type = type;
    }

    @Override
    public String toString() {
        return id + "=" + expression;
    }

    public IType getType() {
        return type;
    }

    public void setType(IType type) {
        this.type = type;
    }
}
