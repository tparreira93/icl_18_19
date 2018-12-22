package compiler;

import types.IType;

public class FrameField {
    private int id;
    private IType type;

    public FrameField(int id, IType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getFieldName() {
        return "location_" + id;
    }

    public String getCompiledType() {
        return type.getClassReference();
    }

    public IType getType() {
        return type;
    }
}
