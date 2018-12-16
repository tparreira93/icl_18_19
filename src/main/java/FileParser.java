import utils.Environment;
import ast.ASTNode;
import exceptions.ASTCompileException;
import exceptions.ASTException;
import parser.ParseException;
import parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    public static void main(String[ ]args) {
        List<String> files = new ArrayList<>();
        if (args.length > 0)
            files.addAll(Arrays.asList(args));
        else
            files.addAll(getFiles("src\\main\\resources"));

        for (String f : files) {
            try {
                System.out.println("Parsing file " + f.substring(f.lastIndexOf('\\') + 1) + "...");
                Parser parser = new Parser(new FileInputStream(new File(f)));

                ASTNode exp = parser.Start();

                System.out.println("Expected result type: " + exp.typecheck(new Environment<>()));

                System.out.println(exp.eval(new Environment<>()));
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
                System.out.println(e.getMessage());
            } catch (ASTCompileException e) {
                System.out.println("Typecheking error!");
                System.out.println(e.getMessage());
            } catch (ASTException e) {
                System.out.println("Runtime error!");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<String> getFiles(String pathname) {
        List<String> files = new ArrayList<>();
        File folder = new File(pathname);
        File[] filesInFolder = folder.listFiles();

        if (filesInFolder != null) {
            for (File aFilesInFolder : filesInFolder)
                if (aFilesInFolder.isFile())
                    files.add(aFilesInFolder.getPath());
        }

        return files;
    }
}
