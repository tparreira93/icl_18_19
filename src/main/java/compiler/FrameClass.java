package compiler;

import java.util.List;

public class FrameClass extends ClassFile {
    private int id;
    private List<FrameField> frameFields;
    private String previousFrame;

    public FrameClass(int id, List<FrameField> frameFields, String previousFrame) {
        this.id = id;
        this.frameFields = frameFields;
        this.previousFrame = previousFrame;
    }

    public int getId() {
        return id;
    }

    public List<FrameField> getFrameFields() {
        return frameFields;
    }

    public String getPreviousFrameClass() {
        return previousFrame;
    }

    private String getFrameClassName() {
        return "frame_" + getId();
    }

    @Override
    public Code getCode() {
        Code c = new Code()
                .addCode(".class public " + getFrameClassName())
                .addCode(".super java/lang/Object")
                .addCode(";default constructor")
                .addCode(".method public <init>()V")
                .addCode("aload_0 ; push this")
                .addCode("invokespecial java/lang/Object/<init>()V ; call super")
                .addCode("return")
                .addCode(".end method")
                .addCode(".field public sl L" + previousFrame + ";")
                .addCode(".field sl L" + getPreviousFrameClass());

        getFrameFields().stream().map(frameField -> ".frameField public " + frameField.getFieldName() + frameField.getCompiledType())
                .forEach(c::addCode);
        c.addCode(".end");

        return c;
    }

    @Override
    public String getClassName() {
        return getFrameClassName();
    }
}
