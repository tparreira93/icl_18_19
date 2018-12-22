package compiler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ClassFile implements IClassFile{
    protected void generateFile(String fileName) throws IOException {
        generateFile("", fileName, 0);
    }

    protected void generateFile(String path, String fileName, int initialTab) throws IOException {
        String content = getCode().Dump();
        File f = new File(path + "\\" + fileName + ".j");

        if (f.exists())
            f.delete();
        if (f.createNewFile())
            Files.write(Paths.get(f.getAbsolutePath()), content.getBytes(StandardCharsets.UTF_8));
    }

    protected Code generateDefaultConstructor() {
        return new Code()
                .addCode(";default constructor")
                .addCode(".method public <init>()V")
                .addCode("aload_0 ; push this")
                .addCode("invokespecial java/lang/Object/<init>()V ; call super")
                .addCode("return")
                .addCode(".end method");
    }

    @Override
    public String getFileName() {
        return getClassName() + ".j";
    }
}
