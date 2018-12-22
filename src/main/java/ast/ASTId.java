package ast;

import compiler.*;
import compiler.Compiler;
import exceptions.ASTInvalidIdentifierException;
import types.IType;
import types.RefType;
import utils.Environment;
import values.IValue;

public class ASTId implements ASTNode {
    private final String name;

    public ASTId(String name) {
        this.name = name;
    }

    public IValue eval(Environment<IValue> environment) {
        return environment.find(name);
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType value = environment.find(name);
        if (value == null)
            throw new ASTInvalidIdentifierException("Invalid identifier \"" + name + "\".");
        return value;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        FrameLocation location = environment.find(name);
        return location.generatePath(environment.getSL());
    }



    @Override
    public String toString() {
        return name;
    }
}
