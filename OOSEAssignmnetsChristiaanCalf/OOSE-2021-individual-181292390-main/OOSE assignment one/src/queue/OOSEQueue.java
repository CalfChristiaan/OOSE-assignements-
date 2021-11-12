package queue;

public interface OOSEQueue {
    void add(Double d);

    Double peek();

    Double remove();

    Integer getSize();
}
