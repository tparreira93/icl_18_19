package compiler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClassFile implements IClassFile {
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

    @Override
    public Code getCode() {
        Code finalCode = new Code();
        try {
            Code code = parseTemplate();
            int sz = code.getCode().size();
            for (int i = 0; i < sz; i++) {
                String x = code.getCode().get(i);
                if (x.contains("REPLACE_THIS_WITH_CODE"))
                    x = x.replace("REPLACE_THIS_WITH_CODE", this.code.Dump(0));

                else if (x.contains("STACK_SIZE"))
                    x = x.replace("STACK_SIZE", String.valueOf(stackSize));

                finalCode.addCode(x);
            }
        } catch (IOException ignored) {
        }

        return finalCode;
    }

    @Override
    public String getClassName() {
        return "main";
    }
}
