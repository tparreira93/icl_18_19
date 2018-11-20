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
        return env.get(identifier);
    }

    public ASTEnvironment<T> beginScope() {
        ASTEnvironment<T> environment = new ASTEnvironment<>(this);
        env.forEach(environment::assoc);
        return environment;
    }

    public ASTEnvironment<T> endScope() {
        return previousScope;
    }
}
