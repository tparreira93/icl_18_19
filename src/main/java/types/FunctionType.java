package types;

import java.util.List;

public class FunctionType implements IType {
    private final IType returnType;
    private final List<IType> arguments;

    public FunctionType(List<IType> arguments, IType returnType) {
        this.arguments = arguments;
        this.returnType = returnType;
    }

    public IType getReturnType() {
        return returnType;
    }

    public List<IType> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FunctionType) {
            FunctionType functionType = (FunctionType) obj;
            boolean equal = true;

            if (functionType.getArguments().size() == this.getArguments().size()) {
                for (int i = 0; i < arguments.size(); i++) {
                    if (!arguments.get(i).equals(functionType.getArguments().get(i)))
                        equal = false;
                }
            }
            else
                equal = false;

            return getReturnType().equals((functionType.getReturnType())) && equal;
        }
        return false;
    }

    @Override
    public String getClassName() {
        return getClassReference();
    }

    @Override
    public String getClassReference() {
        return null;
    }

    @Override
    public String getTypeName() {
        return "function";
    }
}
