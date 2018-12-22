package compiler;

import types.FunctionType;
import types.IType;

public class ClosureInterfaceClass extends ClassFile {
    private final FunctionType functionType;
    private final String interfaceName;
    private final String callSignature;

    public ClosureInterfaceClass(FunctionType functionType) {
        this.functionType = functionType;
        this.interfaceName = getInterfaceName(functionType);
        this.callSignature = getCallSignature(functionType);
    }

    public static String getInterfaceName(FunctionType functionType) {
        String closureName;
        StringBuilder tmp = new StringBuilder();
        for (IType arg : functionType.getArguments()) {
            if (!tmp.toString().equals(""))
                tmp.append("x");
            tmp.append(arg.getTypeName().toUpperCase());
        }
        closureName = "closure_interface_type_" + tmp.toString() + "_" + "2" + functionType.getReturnType().getTypeName().toUpperCase();

        return closureName;
    }

    public static String getCallSignature(FunctionType functionType) {
        StringBuilder builder = new StringBuilder();
        for (IType t : functionType.getArguments())
            builder.append(t.getClassReference());

        return "call(" + builder.toString() + ")" + functionType.getReturnType().getClassReference();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClosureInterfaceClass) {
            ClosureInterfaceClass closureInterface = (ClosureInterfaceClass)obj;
            return closureInterface.getClassName().equals(this.getClassName());
        }
        return super.equals(obj);
    }

    @Override
    public Code getCode() {
        return new Code()
                .addCode(".interface " + interfaceName)
                .addCode(".super java/lang/Object")
                .addCode()
                .addCode(".method public abstract " + getCallSignature(functionType))
                .addCode("return")
                .addCode(".end method");
    }

    @Override
    public String getClassName() {
        return interfaceName;
    }

    public String getCallSignature() {
        return callSignature;
    }
}
