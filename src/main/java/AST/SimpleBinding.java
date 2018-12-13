package AST;

public class SimpleBinding<V> {
    protected String id;
    protected V expression;

    public SimpleBinding(String id, V expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }
    public V getExpression() {
        return expression;
    }
}
