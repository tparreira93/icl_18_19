package compiler;

import java.util.List;

public class MemoryLocation {
    private int level;
    private Address offset;
    private List<Frame> previousFrames;

    public MemoryLocation(){
        
    }

    public MemoryLocation(int level, Address offset, List<Frame> previousFrames) {
        this.level = level;
        this.offset = offset;
        this.previousFrames = previousFrames;
    }

    public int getLevel() {
        return level;
    }

    public Address getOffset() {
        return offset;
    }

    public List<Frame> getFrames() {
        return previousFrames;
    }
}
