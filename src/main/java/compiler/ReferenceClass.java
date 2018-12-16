package compiler;

import types.RefType;

public class ReferenceClass extends ClassFile {
    private final RefType reference;

    public ReferenceClass(RefType type) {
        this.reference = type;
    }

    public static String getValueName() {
        return "value";
    }

    @Override
    public Code getCode() {
        return new Code()
                .addCode(".class public " + getClassName())
                .addCode(".super Ljava/lang/Object;")
                .addCode(".field public " + getValueName() + " " + reference.getClassReference())
                .addCode("")
                .addCode(".method public <init>()V")
                .addCode("aload_0")
                .addCode("invokenonvirtual java/lang/Object/<init>()V")
                .addCode("return")
                .addCode(".end method");
    }

    @Override
    public String getClassName() {
        return reference.getClassName();
    }
}
