package AST;

import AST.Exceptions.ASTDifferentTypeException;
import AST.Exceptions.ASTDuplicateNameException;
import AST.types.IType;
import AST.values.IValue;
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
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        ASTEnvironment<IValue> localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            ASTEnvironment<IValue> bindingScope = environment.beginScope();
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
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        ASTEnvironment<IType> localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            if (localScope.find(identifier.getId()) != null)
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed.");

            ASTEnvironment<IType> bindingScope = environment.beginScope();
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
        Code finalCode = new Code();
        FrameClass frame;
        List<FrameField> frameFields = new ArrayList<>();
        int SL = compiler.getSL();
        String loadSL = "aload " + SL;
        String storeSL = "astore " + SL;
        IntStream.range(0, identifiers.size()).forEach(i -> {
            Binding b = identifiers.get(i);
            frameFields.add(new FrameField(i, b.getType()));
        });

        frame = new FrameClass(frameID, frameFields, compiler.getCurrentFrame());

        finalCode.addCode("new " + frame.getClassName())
                .addCode("dup")
                .addCode("invokespecial " + frame.getClassName() + "/<init>()V")
                .addCode("dup")
                .addCode(loadSL)
                .addCode("putfield " + frame.getClassName() + "/sl L" + compiler.getCurrentFrame())
                .addCode(storeSL);

        for (FrameField f : frameFields) {
            finalCode.addCode(loadSL)
                    .addCode(identifiers.get(f.getId()).getExpression().compile(environment))
                    .addCode("putfield " + frame.getClassName() + "/" + f.getFieldName() + " " + f.getCompiledType());
        }

        finalCode.addCode(body.compile(environment))
                .addCode(loadSL)
                .addCode("getfield  " + frame.getClassName() + " /sl " + compiler.getCurrentFrame())
                .addCode(storeSL);

        compiler.addClassFile(frame);

        return finalCode;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
