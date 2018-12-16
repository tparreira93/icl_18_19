package compiler;

import utils.Environment;

public class CompilerEnvironment extends Environment<MemoryLocation> {
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
