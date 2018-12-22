package compiler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompilerEnvironment {
    private final Frame frame;
    private final HashMap<String, MemoryAddress> env;
    private final CompilerEnvironment previousScope;
    private final int localSL;

    private CompilerEnvironment(CompilerEnvironment previousScope, Frame frame, int SL){
        this.env = new HashMap<>();
        this.frame = frame;
        this.previousScope = previousScope;
        this.localSL = SL;
    }
    public CompilerEnvironment(int SL){
        env = new HashMap<>();
        this.previousScope = null;
        this.frame = new Frame("");
        this.localSL = SL;
    }

    public void assoc(String identifier, MemoryAddress value) {
        env.put(identifier, value);
    }

    public FrameLocation find(String identifier) {
        CompilerEnvironment tmp = previousScope;
        MemoryAddress value = env.get(identifier);
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
        
        return new FrameLocation(level, value, frames);
    }

    public Frame getFrame() {
        return frame;
    }

    public CompilerEnvironment beginScope(Frame frame){
        return new CompilerEnvironment(this, frame, getSL());
    }

    public CompilerEnvironment beginScope(Frame frame, int SL){
        return new CompilerEnvironment(this, frame, SL);
    }

    public CompilerEnvironment endScope() {
        return previousScope;
    }

    public int getSL() {
        return localSL;
    }
}
