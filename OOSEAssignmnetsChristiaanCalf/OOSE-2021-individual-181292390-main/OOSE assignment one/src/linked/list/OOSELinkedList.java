package linked.list;

public interface OOSELinkedList {
    Double getFirst();

    Double getLast();

    Integer getSize();

    Double get(int index);

    void addFirst(Double d);

    void addLast(Double d);

    void add(int index, Double d);

    void removeFirst();

    void removeLast();

    void remove(int index);

    void set(int index, Double value);
}
