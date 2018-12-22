package compiler;

import java.util.List;

public class FrameClass extends ClassFile {
    private final List<ClassField> classFields;
    private final Frame previousFrame;
    private final Frame frame;

    public FrameClass(String name, List<ClassField> classFields, Frame previousFrame) {
        this.classFields = classFields;
        this.previousFrame = previousFrame;
        this.frame = new Frame("frame_" + name);
    }

    public List<ClassField> getClassFields() {
        return classFields;
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

        getClassFields().stream().map(frameField -> ".field public " + frameField.getFieldName() + " " + frameField.getCompiledType())
                .forEach(c::addCode);
        c.addCode(".field public sl " + getPreviousFrameClass().getFrameReference())
            .addCode()
            .addCode(generateDefaultConstructor())
            .addCode();

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
