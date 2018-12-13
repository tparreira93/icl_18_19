package compiler;

import java.util.List;

public class FrameClass implements IClassFile {
    private int id;
    private List<Field> fields;
    private String previousFrame;

    public FrameClass(int id, List<Field> fields, String previousFrame) {
        this.id = id;
        this.fields = fields;
        this.previousFrame = previousFrame;
    }

    public int getId() {
        return id;
    }

    public List<Field> getFields() {
        return fields;
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

        getFields().stream().map(field -> String.format(".field public location_%d %s",
                    field.getId(), field.getCompiledType())).forEach(c::addCode);
        c.addCode(".end");

        return c;
    }

    @Override
    public String getClassName() {
        return getFrameClassName();
    }
}
