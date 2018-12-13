package compiler;

import AST.ASTEnvironment;

public class CompilerEnvironment extends ASTEnvironment<MemoryLocation> {
    private String currentFrame;

    public CompilerEnvironment(){
        super();
        this.currentFrame = "";
    }

    public CompilerEnvironment(String currentFrame){
        super();
        this.currentFrame = currentFrame;
    }

    public String getCurrentFrame() {
        return currentFrame;
    }
}
