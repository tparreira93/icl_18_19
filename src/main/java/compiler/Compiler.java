package compiler;

import AST.ASTNode;
import AST.ASTNum;
import AST.ASTPlus;
import parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compiler {
    private static final Compiler instance = new Compiler();
    private int nextFrameID;
    private String currentFrame;
    private int stackSize;
    private int SL;
    private List<IClassFile> files;

    private static final Compiler getInstance() {
        return instance;
    }

    private Compiler() {
        files = new ArrayList<>();
        currentFrame = "";
        stackSize = 1;
        SL = 2;
    }

    public int getNextFrameID() {
        return nextFrameID++;
    }

    public String getCurrentFrame() {
        return currentFrame;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getSL() {
        return SL;
    }

    public void addClassFile(IClassFile file) {
        this.files.add(file);
    }

    public void addMainClass(String fileName, Code code) {
        this.files.add(new MainClassFile(fileName, code, stackSize));
    }

    public static void main(String[] args) {
        Compiler compiler = Compiler.getInstance();
        Parser parser = new Parser(System.in);
        ASTNode exp;

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                exp = parser.Start();
                Code generatedCode = exp.compile(new CompilerEnvironment());

                compiler.addMainClass("src\\templates\\base.j", generatedCode);
                compiler.generateFiles("D:\\libs\\jasmin-2.4");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void generateFiles(String path) throws IOException {
        for (IClassFile f : files){
            String content = f.getCode().Dump();
            Path finalPath = Paths.get(path, f.getClassName() + ".j");
            File file = new File(finalPath.toString());
            FileOutputStream stream = new FileOutputStream(file, false);
            stream.write(content.getBytes(StandardCharsets.UTF_8));
            stream.close();
        }
    }
}
