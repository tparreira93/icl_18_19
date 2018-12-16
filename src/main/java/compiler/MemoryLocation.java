package compiler;

import java.util.List;

public class MemoryLocation {
    private int level;
    private int offset;
    private List<String> previousFrames;

    public MemoryLocation(int level, int offset, List<String> previousFrames) {
        this.level = level;
        this.offset = offset;
        this.previousFrames = previousFrames;
    }

    public int getLevel() {
        return level;
    }

    public int getOffset() {
        return offset;
    }

    public List<String> getPreviousFrames() {
        return previousFrames;
    }
}
