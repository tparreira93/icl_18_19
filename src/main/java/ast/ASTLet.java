package ast;

import exceptions.ASTDifferentTypeException;
import exceptions.ASTDuplicateNameException;
import types.IType;
import utils.Environment;
import values.IValue;
import compiler.*;
import compiler.Compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ASTLet implements ASTNode {
    private final List<Binding> identifiers;
    private final ASTNode body;

    public ASTLet(List<Binding> identifiers, ASTNode body) {
        this.identifiers = identifiers;
        this.body = body;
    }
    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        Environment<IValue> localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            Environment<IValue> bindingScope = environment.beginScope();
            IValue v = n.eval(bindingScope);

            bindingScope.assoc(identifier.getId(), v);
            bindingScope.endScope();

            localScope.assoc(identifier.getId(), v);
        }

        IValue bodyResult = body.eval(localScope);

        localScope.endScope();
        return bodyResult;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        Environment<IType> localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            if (localScope.find(identifier.getId()) != null)
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed.");

            Environment<IType> bindingScope = environment.beginScope();
            bindingScope.assoc(identifier.getId(), identifier.getType());

            IType v = n.typecheck(bindingScope);
            if (!identifier.getType().equals(v))
                throw new ASTDifferentTypeException("Identifier type and binding type do not match. " + "Expected " + identifier.getType() + " but got " + v + "!");

            bindingScope.endScope();

            localScope.assoc(identifier.getId(), identifier.getType());
        }

        IType bodyType = body.typecheck(localScope);

        localScope.endScope();

        return bodyType;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Compiler compiler = Compiler.getInstance();
        int frameID = compiler.getNextFrameID();
        Code finalCode = new Code().addCode("; --- ASTLet Begin ---");
        List<FrameField> frameFields = new ArrayList<>();
        FrameClass frame = new FrameClass(frameID, frameFields, environment.getFrame());
        int SL = compiler.getSL();
        String loadSL = "aload " + SL;
        String storeSL = "astore " + SL;
        CompilerEnvironment localScope = environment.beginScope(frame.getFrame());
        
        IntStream.range(0, identifiers.size()).forEach(i -> {
            Binding b = identifiers.get(i);
            FrameField field = new FrameField(i, b.getType());

            frameFields.add(field);
            localScope.assoc(b.id, new Address(field.getFieldName(), b.getType()));
        });

        finalCode.addCode("; --- Create frame ---")
                .addCode("new " + frame.getClassName())
                .addCode("dup")
                .addCode("invokespecial " + frame.getClassName() + "/<init>()V")
                .addCode("dup")
                .addCode(loadSL)
                .addCode("putfield " + frame.getClassName() + "/sl " + frame.getPreviousFrameClass().getFrameReference())
                .addCode(storeSL);

        finalCode.addCode("; -- Initialize fields ---");
        for (FrameField f : frameFields) {
            finalCode.addCode(loadSL)
                    .addCode(identifiers.get(f.getId()).getExpression().compile(environment))
                    .addCode("putfield " + frame.getClassName() + "/" + f.getFieldName() + " " + f.getCompiledType());
        }

        finalCode.addCode("; -- ASTLet Body ---");
        finalCode.addCode(body.compile(localScope));

        finalCode.addCode("; --- END Body --")
                .addCode("; --- Restore Static link");
        finalCode.addCode(loadSL)
                .addCode("getfield " + frame.getClassName() + "/sl " + environment.getFrame().getFrameReference())
                .addCode(storeSL);

        compiler.addClassFile(frame);
        
        localScope.endScope();
        return finalCode;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
