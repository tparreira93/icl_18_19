import AST.ASTEnvironment;
import AST.ASTNode;
import parser.Parser;



public class Test {  /** Main entry point. */
    public static void main(String args[]) {
        Parser parser = new Parser(System.in);
        ASTNode exp;

        while (true) {
            try {
                ASTEnvironment environment = new ASTEnvironment(null);
                exp = parser.Start();
                System.out.println( exp.eval(environment) );
            } catch (Exception e) {
                System.out.println ("Syntax Error!");
                System.out.println (e.getMessage());
                parser.ReInit(System.in);
            }
        }
    }
}

