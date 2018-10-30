package AST;

public class Assignment {
    private String id;
    private ASTNode expression;

    public Assignment(String id, ASTNode expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public ASTNode getExpression() {
        return expression;
    }
}
