package compiler;

import types.FunctionType;
import types.IType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ClosureClass extends ClassFile {
    private final FunctionType functionType;
    private final FrameClass argFrame;
    private final String closureName;
    private final ClosureInterfaceClass closureInterface;
    private final Code closureBody;
    private final int localSL;

    public ClosureClass(int closureID, FrameClass argFrame, ClosureInterfaceClass closureInterface, FunctionType functionType, Code closureBody) {
        this.functionType = functionType;
        this.argFrame = argFrame;
        this.closureInterface = closureInterface;
        this.closureName = "closure_" + closureID + "_" + closureInterface.getClassName();
        this.closureBody = closureBody;
        this.localSL = functionType.getArguments().size() + 2;
    }

    @Override
    public Code getCode() {
        List<FrameField> frameFields = new ArrayList<>();
        List<IType> arguments = functionType.getArguments();
        IntStream.range(0, arguments.size()).mapToObj(i -> new FrameField(i, arguments.get(i))).forEach(frameFields::add);
        Code finalCode = new Code()
                .addCode(".class " + closureName)
                .addCode(".super java/lang/Object")
                .addCode(".implements " + closureInterface.getClassName())
                .addCode(".field public sl " + argFrame.getPreviousFrameClass().getFrameReference())
                .addCode()
                .addCode(generateDefaultConstructor())
                .addCode()
                .addCode(".method public " + closureInterface.getCallSignature())
                .addCode(".limit locals 10")
                .addCode(".limit stack 256")
                .addCode("new " + argFrame.getClassName())
                .addCode("dup")
                .addCode("invokespecial " + argFrame.getClassName() + "/<init>()V")
                .addCode("dup")
                .addCode("aload 0")
                .addCode("getfield " + getClassName() + "/sl " + argFrame.getPreviousFrameClass().getFrameReference())
                .addCode("putfield " + argFrame.getClassName() + "/sl " + argFrame.getPreviousFrameClass().getFrameReference());

        for (int i = 0; i < frameFields.size(); i++) {
            FrameField field = frameFields.get(i);
            finalCode.addCode("dup")
                    .addCode(field.getType().getLoadKeyword() + " " + (i + 1))
                    .addCode("putfield " + argFrame.getClassName() + "/" + field.getFieldName() + " " + field.getCompiledType());
        }

        finalCode.addCode("astore " + localSL)
                .addCode()
                .addCode(closureBody)
                .addCode()
                .addCode(functionType.getReturnType().getReturnKeywork())
                .addCode(".end method");

        return finalCode;
    }

    @Override
    public String getClassName() {
        return closureName;
    }
}
