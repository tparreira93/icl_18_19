package values;

public interface IValue {
    String getName();
    Object getValue();
    int compareTo(IValue v) throws Exception;
    boolean equals(IValue v) throws Exception;
}
