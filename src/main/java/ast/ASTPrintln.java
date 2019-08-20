package ast;

import types.IType;
import utils.Environment;
import values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTPrintln implements ASTNode {
    private ASTNode toPrint;
    private IType printType;

    public ASTPrintln(ASTNode toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        System.out.println(toPrint.eval(environment).getValue());
        return null;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        printType = toPrint.typecheck(environment);
        return null;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return new Code()
                .addCode("; --- BEGIN ASTPrintln")
                .addCode("; the PrintStream object held in java.lang.out")
                .addCode("getstatic java/lang/System/out Ljava/io/PrintStream;")
                .addCode(toPrint.compile(environment))
                .addCode("; call println ")
                .addCode("invokevirtual java/io/PrintStream/println(" + printType.getClassReference() + ")V")
                .addCode("; --- END ASTPrintln ---");
    }
}
