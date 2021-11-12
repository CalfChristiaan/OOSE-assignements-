package linked.list;

/**
 * This class contains the actual data of a single element in the linked list.
 * It also contains a reference to the previous and next element in the chain (link).
 * <p>
 * STUDENTS: You can optionally make this class use generics.
 */
public class LinkedListNode<Double> {
    /***
     * Leave these three variables if you want to use the tests. RECOMMENDED
     */
    private Double value;
    private LinkedListNode<Double> previous;
    private LinkedListNode<Double> next;

    /**
     * CONSTRUCTOR
     */
    public LinkedListNode(Double value) {
        this.value = value;
    }

    /**
     * Basic get and set methods below
     **/

    public Double getValue() {
        return value;
    }


    public void setValue(Double value) {
        this.value = value;
    }

    public LinkedListNode<Double> getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedListNode<Double> previous) {
        this.previous = previous;
    }

    public LinkedListNode<Double> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<Double> next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
}
