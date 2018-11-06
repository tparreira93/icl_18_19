package AST;

public class Binding {
    private String id;
    private ASTNode expression;

    public Binding(String id, ASTNode expression) {
        this.id = id;
        this.expression = expression;
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
}
