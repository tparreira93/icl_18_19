package AST;

import values.IValue;

import java.util.HashMap;
import java.util.Map;

public class ASTEnvironment {
    private final HashMap<String, IValue> env;
    private final ASTEnvironment previousScope;

    public ASTEnvironment(ASTEnvironment previousScope){
        env = new HashMap<>();
        this.previousScope = previousScope;
    }

    public void assoc(String identifier, IValue value) {
        env.put(identifier, value);
    }

    public IValue find(String identifier) {
        return env.get(identifier);
    }

    public ASTEnvironment beginScope() {
        ASTEnvironment environment = new ASTEnvironment(this);
        env.forEach(environment::assoc);
        return environment;
    }

    public ASTEnvironment endScope() {
        return previousScope;
    }
}
