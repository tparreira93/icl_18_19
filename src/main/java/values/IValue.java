package values;

public interface IValue<T> {
    String getName();
    T getValue();
    int compareTo(IValue<?> v) throws Exception;
    boolean equals(IValue<?> v) throws Exception;
}
