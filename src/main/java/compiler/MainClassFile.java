package compiler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClassFile extends ClassFile {
    private final String templatePath;
    private final Code code;
    private final int stackSize;

    public MainClassFile(String templatePath, Code code, int stackSize) {
        this.templatePath = templatePath;
        this.code = code;
        this.stackSize = stackSize;
    }

    private Code parseTemplate() throws IOException {
        Path path = Paths.get(templatePath);
        Charset charset = StandardCharsets.UTF_8;
        return new Code(Files.readAllLines(path, charset));
    }

    // TODO: IMPROVE STACK AND LOCALS
    @Override
    public Code getCode() {
        return new Code()
                .addCode(".class public Main")
                .addCode(".super java/lang/Object")
                .addCode("")
                .addCode(".method public <init>()V")
                .addCode("aload_0")
                .addCode("invokenonvirtual java/lang/Object/<init>()V")
                .addCode("return")
                .addCode(".end method")
                .addCode("")
                .addCode(".method public static main([Ljava/lang/String;)V")
                .addCode("; set limits used by this method")
                .addCode("; TODO: IMPROVE STACK AND LOCALS")
                .addCode(".limit locals 10")
                .addCode(".limit stack 256")
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
