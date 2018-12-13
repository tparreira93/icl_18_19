package compiler;

public class Field {
    public enum FieldType {
        Int,
        Bool,
        Ref,
        Frame
    }

    private int id;
    private FieldType fieldType;

    public Field(int id, FieldType fieldType) {
        this.id = id;
        this.fieldType = fieldType;
    }

    public int getId() {
        return id;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public String getCompiledType() {
        switch (fieldType) {
            case Int:
            case Bool:
                return "I";
        }

        return "";
    }
}
