package ast;

import compiler.*;
import compiler.Compiler;
import exceptions.ASTInvalidIdentifierException;
import types.IType;
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
        Compiler compiler = Compiler.getInstance();
        int SL = compiler.getSL();
        Code code = new Code();
        MemoryLocation location = environment.find(name);
        Frame previousFrame = location.getFrames().get(0);
        Frame frame;
        code.addCode("aload " + SL);
        for (int i = 1; i < location.getFrames().size(); i++) {
            frame = location.getFrames().get(i);
            code.addCode("getfield " + frame.getFrameName() + "/sl " + previousFrame.getFrameReference());
            if (i + 1 < location.getFrames().size())
                previousFrame = frame;
        }
        code.addCode("getfield " + previousFrame.getFrameName() + "/" + location.getOffset().getAddress() + " " + location.getOffset().getType().getClassReference());

        return code;
    }

    @Override
    public String toString() {
        return name;
    }
}
