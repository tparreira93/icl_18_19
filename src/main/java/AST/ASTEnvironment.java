package AST;
import java.util.HashMap;

public class ASTEnvironment<T> {
    private final HashMap<String, T> env;
    private final ASTEnvironment<T> previousScope;

    private ASTEnvironment(ASTEnvironment<T> previousScope){
        env = new HashMap<>();
        this.previousScope = previousScope;
    }
    public ASTEnvironment(){
        env = new HashMap<>();
        this.previousScope = null;
    }

    public void assoc(String identifier, T value) {
        env.put(identifier, value);
    }

    public T find(String identifier) {
        ASTEnvironment<T> tmp = previousScope;
        T value = env.get(identifier);

        if (value != null)
            return value;

        while (tmp != null) {
            value = tmp.find(identifier);
            if (value != null)
                break;
            tmp = tmp.previousScope;
        }
        return value;
    }

    public ASTEnvironment<T> beginScope() {
        return new ASTEnvironment<>(this);
    }

    public ASTEnvironment<T> endScope() {
        return previousScope;
    }
}
