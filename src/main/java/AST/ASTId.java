package AST;

import AST.Exceptions.ASTInvalidIdentifierException;
import AST.types.IType;
import AST.values.IValue;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import compiler.MemoryLocation;

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
        Compiler compiler = Compiler.getInstance();
        int SL = compiler.getSL();
        Code code = new Code()
                .addCode("aload_" + SL);
        MemoryLocation location = environment.find(name);
        String currentFrame = environment.getCurrentFrame();

        for (String frame : location.getPreviousFrames()) {
            code.addCode("getfield " + "/sl Lancestor_frame_id");
        }
        return code;
    }

    @Override
    public String toString() {
        return name;
    }
}
