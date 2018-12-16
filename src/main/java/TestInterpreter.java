import utils.Environment;
import ast.ASTNode;
import exceptions.ASTCompileException;
import exceptions.ASTException;
import types.IType;
import values.IValue;
import parser.ParseException;
import parser.Parser;



public class TestInterpreter {  /** Main entry point. */
    public static void main(String args[]) {
        Parser parser = new Parser(System.in);
        ASTNode exp;

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Environment<IValue> runEnv = new Environment<>();
                Environment<IType> compileEnv = new Environment<>();

                exp = parser.Start();

                System.out.println("Typechecking the input expression...");
                System.out.println("Expected result type: " + exp.typecheck(compileEnv));
                System.out.println("Evaluating expression...");
                System.out.println(exp.eval(runEnv));

            } catch (ParseException e) {
                System.out.println("Syntax Error!");
                System.out.println(e.getMessage());
                parser.ReInit(System.in);
            } catch (ASTCompileException e) {
                System.out.println("Typecheking error!");
                System.out.println(e.getMessage());
                parser.ReInit(System.in);
            } catch (ASTException e) {
                System.out.println("Runtime error!");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                parser.ReInit(System.in);
            }
        }
    }
}

