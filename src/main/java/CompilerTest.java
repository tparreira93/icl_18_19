import utils.Environment;
import ast.ASTNode;
import exceptions.ASTCompileException;
import exceptions.ASTCompilerError;
import exceptions.ASTException;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import parser.ParseException;
import parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompilerTest {
    public static void main(String[] args) {
        try {
            List<String> files;
            if (args.length > 1)
                files = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(args, 1, args.length)));
            else if (args.length == 1)
                files = FileParser.getFiles("src\\main\\resources\\compiler_test");
            else
                throw new ASTCompilerError("Jasmin jar location is need for compilation.");

            Compiler compiler = Compiler.getInstance(args[0]);
            for (String f : files) {
                File file = new File(f);
                String name = file.getName();
                int pos = name.lastIndexOf('.');
                if (pos > 0)
                    name = name.substring(0, pos);

                System.out.println("Compiling file " + file.getName() + "...");
                Parser parser = new Parser(new FileInputStream(file));
                ASTNode exp = parser.Start();

                System.out.println("Typechecking " + file.getName() + "...");
                exp.typecheck(new Environment<>());

                System.out.println("Assembling " + file.getName() + "...");
                Code generatedCode = exp.compile(new CompilerEnvironment());
                //String workingDirectory = "D:\\jasmin\\" + name;
                String workingDirectory = "D:\\libs\\jasmin-2.4";
                compiler.addMainClass("src\\templates\\base.j", generatedCode);

                System.out.println("Compiling generated files...");
                System.out.println(compiler.assembleFiles(workingDirectory));

                System.out.println("Executing...");
                System.out.println(compiler.execute(workingDirectory));
            }
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
