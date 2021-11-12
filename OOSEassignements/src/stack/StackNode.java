package stack;

/**
 * This class contains the actual data of a single element in the queue
 * It also contains a reference to the next element.
 * <p>
 * STUDENTS: You can optionally make this class use generics.
 */
public class StackNode<Double> {
    private Double value;
    private StackNode<Double> next;

    /**
     * CONSTRUCTOR
     */
    public StackNode(Double value) {
        this.value = value;
    }

    /**
     * Basic get and set methods below
     */

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public StackNode<Double> getNext() {
        return next;
    }

    public void setNext(StackNode<Double> next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }
}

