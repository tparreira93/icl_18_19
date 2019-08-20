package compiler;

import java.util.List;

public class AnonymousTypeClass extends ClassFile {
    private final String name;
    private final List<ClassField> fields;

    public AnonymousTypeClass(String name, List<ClassField> fields) {
        this.name = name;
        this.fields = fields;
    }

    @Override
    public Code getCode() {
        Code c = new Code()
                .addCode(".class public " + getClassName())
                .addCode(".super java/lang/Object")
                .addCode();

        fields.stream().map(frameField -> ".field public " + frameField.getFieldName() + " " + frameField.getCompiledType())
                .forEach(c::addCode);
        c.addCode()
            .addCode(generateDefaultConstructor())
            .addCode();

        return c;
    }

    @Override
    public String getClassName() {
        return name;
    }
}
