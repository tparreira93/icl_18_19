package compiler;

public class MainClassFile extends ClassFile {
    private final Code code;
    private final int stackSize;
    private final int SL;

    public MainClassFile(Code code, int stackSize, int SL) {
        this.code = code;
        this.stackSize = stackSize;
        this.SL = SL;
    }

    // TODO: IMPROVE STACK AND LOCALS
    @Override
    public Code getCode() {
        return new Code()
                .addCode(".class public Main")
                .addCode(".super java/lang/Object")
                .addCode()
                .addCode(generateDefaultConstructor())
                .addCode()
                .addCode(".method public static main([Ljava/lang/String;)V")
                .addCode("; set limits used by this method")
                .addCode("; TODO: IMPROVE STACK AND LOCALS")
                .addCode(".limit locals 10")
                .addCode(".limit stack " + stackSize)
                .addCode("")
                .addCode("aconst_null")
                .addCode("astore " + SL)
                .addCode("")
                .addCode(this.code)
                .addCode("")
                .addCode("return")
                .addCode(".end method");
    }

    @Override
    public String getClassName() {
        return "Main";
    }
}
