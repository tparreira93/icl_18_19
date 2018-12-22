package compiler;

import types.IType;

public class MemoryAddress {
    private final String address;
    private final IType type;

    public MemoryAddress(String address, IType type){
        this.address = address;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public IType getType() {
        return type;
    }
}