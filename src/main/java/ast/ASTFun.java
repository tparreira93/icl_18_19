package ast;

import compiler.*;
import compiler.Compiler;
import exceptions.ASTDuplicateNameException;
import types.FunctionType;
import types.IType;
import utils.Environment;
import values.ClosureValue;
import values.IValue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ASTFun implements ASTNode {
    private final List<Parameter> params;
    private final ASTNode expression;
    private FunctionType functionType;

    public ASTFun(List<Parameter> params, ASTNode expression) {
        this.params = params;
        this.expression = expression;
    }
    @Override
    public IValue eval(Environment<IValue> environment) {
        return new ClosureValue(params, expression, environment);
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        HashSet<String> tmp = new HashSet<>();
        for (Parameter param: params) {
            if(tmp.contains(param.getName()))
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed");

            tmp.add(param.getName());
        }
        Environment<IType> localScope = environment.beginScope();
        for (Parameter par : params) {
            localScope.assoc(par.getName(), par.getType());
        }
        IType returnType = expression.typecheck(localScope);

        functionType = new FunctionType(params.stream().map(Parameter::getType).collect(Collectors.toList()),
                returnType);

        localScope.endScope();

        return functionType;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Compiler compiler = Compiler.getInstance();
        List<FrameField> frameFields = new ArrayList<>();
        FrameClass argFrame = compiler.newFrame("closure", frameFields, environment.getFrame());
        CompilerEnvironment localScope = environment.beginScope(argFrame.getFrame(), params.size() + 2);
        ClosureInterfaceClass closureInterface = compiler.newClosureInterface(functionType);

        IntStream.range(0, params.size()).forEach(i -> {
            Parameter par = params.get(i);
            FrameField field = new FrameField(i, par.getType());
            frameFields.add(field);
            localScope.assoc(par.getName(), new MemoryAddress(field.getFieldName(), par.getType()));
        });
        ClosureClass closure = compiler.newClosure(functionType, closureInterface, argFrame, expression.compile(localScope));
        localScope.endScope();

        return new Code()
                .addCode("new " + closure.getClassName())
                .addCode("dup")
                .addCode("invokespecial " + closure.getClassName() + "/<init>()V")
                .addCode("dup")
                .addCode("aload " + environment.getSL())
                .addCode("putfield " + closure.getClassName() + "/sl " + environment.getFrame().getFrameReference());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
