import AST.ASTEnvironment;
import org.junit.Assert;
import org.junit.Test;
import parser.Parser;
import AST.values.BoolValue;
import AST.values.IValue;
import AST.values.IntValue;

import java.io.ByteArrayInputStream;
public class TestParser {
    @Test
    public void test_1_Plus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1+1;;".getBytes() ));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(2, (int) result.getValue());
    }
    @Test
    public void test_1_Plus_1_Plus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1+1+1;;".getBytes() ));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(3, (int) result.getValue());
    }


    @Test
    public void test_1_Minus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1-1;;".getBytes() ));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(0, (int) result.getValue());
    }

    @Test
    public void test_5_Times_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("5*5;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(25, (int) result.getValue());
    }

    @Test
    public void test_100_Divided_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("100/5;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(20, (int) result.getValue());
    }

    @Test
    public void test_Let_X_equal_1_x_Plus_10() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:int = 1 in x + 10 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(11, (int) result.getValue());
    }

    @Test
    public void test_Let_X_equal_1_y_equal_5_x_Plus_y_Plus_10() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:int = 1, y:int = 5 in x + y + 10 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(16, (int) result.getValue());
    }

    @Test
    public void test_Let_X_equal_1_y_equal_5_z_equal_10_x_Plus_y_Times_z_Plus_10() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:int = 1, y:int = 5, z:int = 10 in x + y * z + 10 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(61, (int) result.getValue());
    }

    @Test
    public void test_Let_f_function_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()int = function => 1 end in f() end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(1, (int) result.getValue());
    }

    @Test
    public void test_Let_f_function_x_in_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:(int)int = function x:int => x end in f(1) end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(1, (int) result.getValue());
    }

    @Test
    public void test_Let_f_function_x_y___x_Plus_y__IN_f_1_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:(int,int)int = function x:int, y:int => x + y end in f(1, 1) end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(2, (int) result.getValue());
    }

    @Test
    public void test_Let_f_function_x_y_z___x_Plus_y_Times_z_IN_f_2_3_4() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:(int,int,int)int = function x:int, y:int, z:int => x + y * z end in f(2, 3, 4) end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(14, (int) result.getValue());
    }

    @Test
    public void test_Let_f_function_1_f_Plus_f() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()int = function => 1 end in f() + f() end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(2, (int) result.getValue());
    }

    @Test
    public void test_Let_f_function_5_f_Times_f() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()int = function => 5 end in f() * f() end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(25, (int) result.getValue());
    }

    @Test
    public void test_if_true_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if true then 1 else 0 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(1, (int) result.getValue());
    }

    @Test
    public void test_if_false_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if false then 1 else 0 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(0, (int) result.getValue());
    }

    @Test
    public void test_if_1_equal_1_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if 1 == 1 then 1 else 0 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(1, (int) result.getValue());
    }

    @Test
    public void test_if_1_not_equal_1_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if 1 <> 1 then 1 else 0 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(0, (int) result.getValue());
    }

    @Test
    public void test_1_Equal_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 == 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_1_Equal_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 == 2;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_1_NotEqual_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <> 2;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_1_NotEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <> 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_1_GreaterThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 > 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_2_GreaterThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 > 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_1_GreaterThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 >= 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_2_GreaterThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 >= 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_1_GreaterThanOrEqual_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 >= 2;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 < 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThan_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 < 2;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_2_SmallerThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 < 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <= 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_2_SmallerThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 <= 1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThanOrEqual_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <= 2;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_true_or_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("true || true;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_true_or_false_and_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("true || false && true;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_false_and_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("false && true;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_true_and_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("true && true;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_reference_1_plus_1_IN_dereference_minus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:ref int = new (1 + 1) in !x - 1 end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(1, (int) result.getValue());
    }

    @Test
    public void test_function_1_IN_f_equals_1_equals_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()int = function => 1 end in (f() == 1) == true end;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_function_argument_compare_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream(("let \n" +
                "    f:()int = function f:(int)bool => f(5) end,\n" +
                "    comp5:(int)bool = function x:int => x == 5 end\n" +
                "in \n" +
                "    f(comp5)\n" +
                "end;;").getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_function_reference_passes_ref_function_as_argument_to_function_compare_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream(("let \n" +
                "    f:(ref (int)bool)bool = function f:ref (int)bool => !f(5) end,\n" +
                "    comp5:ref (int)bool = new function x:int => x == 5 end\n" +
                "in \n" +
                "    f(comp5)\n" +
                "end;;").getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_not_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("~true;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_not_false() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("~false;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((boolean) result.getValue());
    }

    @Test
    public void test_not_1_equal_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("~(1 == 1);;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(boolean) result.getValue());
    }

    @Test
    public void test_negative() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("-1;;".getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(-1, (int)result.getValue());
    }


    @Test
    public void fact_10() throws Exception {
        String factorial = "let\n" +
                "    factorial:(int)int = function x:int =>\n" +
                "        let\n" +
                "            result:ref int = new 1\n" +
                "        in\n" +
                "            if x > 1 then\n" +
                "                x * factorial(x - 1)\n" +
                "            else\n" +
                "                x\n" +
                "            end\n" +
                "        end\n" +
                "    end\n" +
                "in\n" +
                "    factorial(10)\n" +
                "end;;";
        Parser parser = new Parser(new ByteArrayInputStream(String.format(factorial, 10).getBytes()));
        ASTEnvironment<IValue> env = new ASTEnvironment<>();
        IValue result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals(3628800, (int)result.getValue());
    }
}