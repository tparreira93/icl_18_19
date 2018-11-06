import AST.ASTEnvironment;
import AST.ASTNode;
import AST.Exceptions.ASTException;
import parser.ParseException;
import parser.Parser;



public class TestInterpreter {  /** Main entry point. */
    public static void main(String args[]) {
        Parser parser = new Parser(System.in);
        ASTNode exp;

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                ASTEnvironment environment = new ASTEnvironment(null);
                exp = parser.Start();
                System.out.println(exp.eval(environment));
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
                System.out.println(e.getMessage());
                parser.ReInit(System.in);
            } catch (ASTException e) {
                System.out.println("Runtime error!");
                System.out.println(e.getMessage());
                parser.ReInit(System.in);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                parser.ReInit(System.in);
            }
        }
    }
}

