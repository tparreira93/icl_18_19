package compiler;

import java.util.List;

public class FrameClass extends ClassFile {
    private final int id;
    private final List<FrameField> frameFields;
    private final Frame previousFrame;
    private final Frame frame;

    public FrameClass(int id, List<FrameField> frameFields, Frame previousFrame) {
        this.id = id;
        this.frameFields = frameFields;
        this.previousFrame = previousFrame;
        this.frame = new Frame("frame_" + id);
    }

    public int getId() {
        return id;
    }

    public List<FrameField> getFrameFields() {
        return frameFields;
    }

    public Frame getPreviousFrameClass() {
        return previousFrame;
    }

    @Override
    public Code getCode() {
        Code c = new Code()
                .addCode(".class public " + getClassName())
                .addCode(".super java/lang/Object")
                .addCode();

        getFrameFields().stream().map(frameField -> ".field public " + frameField.getFieldName() + " " + frameField.getCompiledType())
                .forEach(c::addCode);
        c.addCode(".field public sl " + getPreviousFrameClass().getFrameReference());

        c.addCode()
                .addCode(";default constructor")
                .addCode(".method public <init>()V")
                .addCode("aload_0 ; push this")
                .addCode("invokespecial java/lang/Object/<init>()V ; call super")
                .addCode("return")
                .addCode(".end method");

        return c;
    }

    @Override
    public String getClassName() {
        return frame.getFrameName();
    }

    public Frame getFrame() {
        return frame;
    }
}
