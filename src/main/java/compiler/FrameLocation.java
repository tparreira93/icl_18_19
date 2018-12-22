package compiler;

import types.FunctionType;
import types.RefType;

import java.util.List;

public class FrameLocation {
    private int level;
    private MemoryAddress offset;
    private List<Frame> framePath;

    public FrameLocation(int level, MemoryAddress offset, List<Frame> framePath) {
        this.level = level;
        this.offset = offset;
        this.framePath = framePath;
    }

    public int getLevel() {
        return level;
    }

    public MemoryAddress getOffset() {
        return offset;
    }

    public List<Frame> getFrames() {
        return framePath;
    }

    public Code generatePath(int SL) {
        Code code = new Code();
        Frame previousFrame = getFrames().get(getFrames().size() - 1);
        Frame frame;
        code.addCode("aload " + SL);
        for (int i = getFrames().size() - 2; i >= 0; i--) {
            frame = getFrames().get(i);
            code.addCode("getfield " + previousFrame.getFrameName() + "/sl " + frame.getFrameReference());
            previousFrame = frame;
        }
        String tmp = "";
        if (getOffset().getType() instanceof FunctionType)
            tmp = ((FunctionType) getOffset().getType()).getReturnType().getClassReference();
        //else
            tmp = getOffset().getType().getClassReference();

        code.addCode("getfield " + previousFrame.getFrameName() + "/" + getOffset().getAddress() + " " + tmp);
        if (getOffset().getType() instanceof RefType)
            code.addCode("checkcast " + getOffset().getType().getClassName());

        return code;
    }
}
