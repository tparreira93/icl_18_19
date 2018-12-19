package compiler;

import types.IType;

import java.util.List;

public class ClosureClass extends ClassFile {
    private final String closureID;
    private final List<IType> parameters;
    private final List<IType> returnType;

    public ClosureClass(String closureID, List<IType> parameters, List<IType> returnType) {
        this.closureID = closureID;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    @Override
    public Code getCode() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }

    public List<IType> getParameters() {
        return parameters;
    }

    public List<IType> getReturnType() {
        return returnType;
    }

    public String getClosureID() {
        return closureID;
    }
}
