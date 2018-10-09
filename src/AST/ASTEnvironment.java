package AST;

import values.IValue;

import java.util.HashMap;
import java.util.Map;

public class ASTEnvironment {
    private HashMap<String, IValue> env;
    private ASTEnvironment previousScope;

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
        for (Map.Entry<String, IValue> entry : env.entrySet()) {
            environment.assoc(entry.getKey(), entry.getValue());
        }
        return environment;
    }

    public ASTEnvironment endScope() {
        return previousScope;
    }
}
