package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompilerEnvironment {
    private final Frame frame;
    private final HashMap<String, Address> env;
    private final CompilerEnvironment previousScope;

    private CompilerEnvironment(CompilerEnvironment previousScope, Frame frame){
        this.env = new HashMap<>();
        this.frame = frame;
        this.previousScope = previousScope;
    }
    public CompilerEnvironment(){
        env = new HashMap<>();
        this.previousScope = null;
        this.frame = new Frame("");
    }

    public void assoc(String identifier, Address value) {
        env.put(identifier, value);
    }

    public MemoryLocation find(String identifier) {
        CompilerEnvironment tmp = previousScope;
        Address value = env.get(identifier);
        int level = 0;
        List<Frame> frames = new ArrayList<>();
        frames.add(getFrame());
        if (value == null){
            while (tmp != null) {
                frames.add(0, tmp.getFrame());
                value = tmp.env.get(identifier);
                level++;
                tmp = tmp.previousScope;
                if (value != null)
                    break;
            }
        }
        
        return new MemoryLocation(level, value, frames);
    }

    public Frame getFrame() {
        return frame;
    }

    public CompilerEnvironment beginScope(Frame frame){
        return new CompilerEnvironment(this, frame);
    }

    public CompilerEnvironment endScope() {
        return previousScope;
    }
}
