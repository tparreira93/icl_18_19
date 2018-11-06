import AST.ASTEnvironment;
import AST.Exceptions.ASTException;
import parser.ParseException;
import parser.Parser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    public static void main(String[ ]args) {
        List<String> files = new ArrayList<>();
        if (args.length > 0) {
            files.addAll(Arrays.asList(args));
        }
        else {
            File folder = new File("src\\main\\resources");
            File[] filesInFolder = folder.listFiles();

            if (filesInFolder != null) {
                for (File aFilesInFolder : filesInFolder)
                    if (aFilesInFolder.isFile())
                        files.add(aFilesInFolder.getPath());
            }
        }

        for (String f : files) {
            try {
                System.out.println("Parsing file " + f.substring(f.lastIndexOf('\\') + 1) + "...");
                Parser parser = new Parser(new FileInputStream(new File(f)));
                System.out.println(parser.Start().eval(new ASTEnvironment(null)));
                System.out.println();
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
                System.out.println(e.getMessage());
            } catch (ASTException e) {
                System.out.println("Runtime error!");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
