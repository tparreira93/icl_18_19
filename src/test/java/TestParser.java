import utils.Environment;
import org.junit.Assert;
import org.junit.Test;
import parser.Parser;
import values.BoolValue;
import values.IValue;
import values.IntValue;

import java.io.ByteArrayInputStream;
public class TestParser {
    @Test
    public void test_1_Plus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1+1;;".getBytes() ));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)2, (Integer) result.getValue());
    }
    @Test
    public void test_1_Plus_1_Plus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1+1+1;;".getBytes() ));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)3, (Integer) result.getValue());
    }


    @Test
    public void test_1_Minus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1-1;;".getBytes() ));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)0, (Integer) result.getValue());
    }

    @Test
    public void test_5_Times_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("5*5;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)25, (Integer) result.getValue());
    }

    @Test
    public void test_100_Divided_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("100/5;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)20, (Integer) result.getValue());
    }

    @Test
    public void test_Let_X_equal_1_x_Plus_10() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:Integer = 1 in x + 10 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)11, (Integer) result.getValue());
    }

    @Test
    public void test_Let_X_equal_1_y_equal_5_x_Plus_y_Plus_10() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:Integer = 1, y:Integer = 5 in x + y + 10 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)16, (Integer) result.getValue());
    }

    @Test
    public void test_Let_X_equal_1_y_equal_5_z_equal_10_x_Plus_y_Times_z_Plus_10() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:Integer = 1, y:Integer = 5, z:Integer = 10 in x + y * z + 10 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)61, (Integer) result.getValue());
    }

    @Test
    public void test_Let_f_fun_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()Integer = fun -> 1 end in f() end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)1, (Integer) result.getValue());
    }

    @Test
    public void test_Let_f_fun_x_in_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:(Integer)Integer = fun x:Integer -> x end in f(1) end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)1, (Integer) result.getValue());
    }

    @Test
    public void test_Let_f_fun_x_y___x_Plus_y__IN_f_1_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:(Integer,Integer)Integer = fun x:Integer, y:Integer -> x + y end in f(1, 1) end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)2, (Integer) result.getValue());
    }

    @Test
    public void test_Let_f_fun_x_y_z___x_Plus_y_Times_z_IN_f_2_3_4() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:(Integer,Integer,Integer)Integer = fun x:Integer, y:Integer, z:Integer -> x + y * z end in f(2, 3, 4) end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)14, (Integer) result.getValue());
    }

    @Test
    public void test_Let_f_fun_1_f_Plus_f() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()Integer = fun -> 1 end in f() + f() end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)2, (Integer) result.getValue());
    }

    @Test
    public void test_Let_f_fun_5_f_Times_f() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()Integer = fun -> 5 end in f() * f() end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)25, (Integer) result.getValue());
    }

    @Test
    public void test_if_true_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if true then 1 else 0 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)1, (Integer) result.getValue());
    }

    @Test
    public void test_if_false_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if false then 1 else 0 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)0, (Integer) result.getValue());
    }

    @Test
    public void test_if_1_equal_1_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if 1 == 1 then 1 else 0 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)1, (Integer) result.getValue());
    }

    @Test
    public void test_if_1_not_equal_1_then_1_else_0() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("if 1 <> 1 then 1 else 0 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)0, (Integer) result.getValue());
    }

    @Test
    public void test_1_Equal_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 == 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_1_Equal_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 == 2;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_1_NotEqual_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <> 2;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_1_NotEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <> 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_1_GreaterThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 > 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_2_GreaterThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 > 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_1_GreaterThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 >= 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_2_GreaterThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 >= 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_1_GreaterThanOrEqual_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 >= 2;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 < 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThan_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 < 2;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_2_SmallerThan_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 < 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <= 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_2_SmallerThanOrEqual_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("2 <= 1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_1_SmallerThanOrEqual_2() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("1 <= 2;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_true_or_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("true || true;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_true_or_false_and_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("true || false && true;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_false_and_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("false && true;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_true_and_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("true && true;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_reference_1_plus_1_IN_dereference_minus_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let x:ref Integer = new (1 + 1) in !x - 1 end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer) 1, (Integer) result.getValue());
    }

    @Test
    public void test_fun_1_IN_f_equals_1_equals_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("let f:()Integer = fun -> 1 end in (f() == 1) == true end;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_fun_argument_compare_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream(("let \n" +
                "    f:()Integer = fun f:(Integer)bool -> f(5) end,\n" +
                "    comp5:(Integer)bool = fun x:Integer -> x == 5 end\n" +
                "in \n" +
                "    f(comp5)\n" +
                "end;;").getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_fun_reference_passes_ref_fun_as_argument_to_fun_compare_5() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream(("let \n" +
                "    f:(ref (Integer)bool)bool = fun f:ref (Integer)bool -> !f(5) end,\n" +
                "    comp5:ref (Integer)bool = new fun x:Integer -> x == 5 end\n" +
                "in \n" +
                "    f(comp5)\n" +
                "end;;").getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_not_true() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("~true;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_not_false() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("~false;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue((Boolean) result.getValue());
    }

    @Test
    public void test_not_1_equal_1() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("~(1 == 1);;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof BoolValue);
        Assert.assertTrue(!(Boolean) result.getValue());
    }

    @Test
    public void test_negative() throws Exception {
        Parser parser = new Parser(new ByteArrayInputStream("-1;;".getBytes()));
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)(-1), (Integer)result.getValue());
    }


    @Test
    public void fact_10() throws Exception {
        String factorial = "let\n" +
                "    factorial:(Integer)Integer = fun x:Integer ->\n" +
                "        let\n" +
                "            result:ref Integer = new 1\n" +
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
        Environment<IValue<?>> env = new Environment<>();
        IValue<?> result = parser.Start().eval(env);

        Assert.assertTrue(result instanceof IntValue);
        Assert.assertEquals((Integer)3628800, (Integer)result.getValue());
    }
}