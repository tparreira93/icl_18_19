package AST;

import AST.Exceptions.ASTInvalidIdentifierException;
import AST.types.IType;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTId implements ASTNode {
    private final String name;

    public ASTId(String name) {
        this.name = name;
    }

    public IValue eval(ASTEnvironment<IValue> environment) {
        return environment.find(name);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType value = environment.find(name);
        if (value == null)
            throw new ASTInvalidIdentifierException("Invalid identifier \"" + name + "\".");
        return value;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
