package utils;
import java.util.HashMap;

public class Environment<T> {
    private final HashMap<String, T> env;
    private final Environment<T> previousScope;

    private Environment(Environment<T> previousScope){
        env = new HashMap<>();
        this.previousScope = previousScope;
    }
    public Environment(){
        env = new HashMap<>();
        this.previousScope = null;
    }

    public void assoc(String identifier, T value) {
        env.put(identifier, value);
    }

    public T find(String identifier) {
        Environment<T> tmp = previousScope;
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

    public Environment<T> beginScope() {
        return new Environment<>(this);
    }

    public Environment<T> endScope() {
        return previousScope;
    }
}
