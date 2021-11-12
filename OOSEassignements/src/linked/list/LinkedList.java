package linked.list;

/**
 * This class is supposed to represent a doubly linked list
 * Finish the methods below
 * The tests can guide you in the right direction but are not absolute infallible
 */
public class LinkedList implements OOSELinkedList {
    /***
     * Leave these three variables if you want to use the tests. RECOMMENDED
     */
    private LinkedListNode<Double> head;
    private LinkedListNode<Double> tail;
    private Integer size = 0;

    /**
     * @return The value of the head of the linked list
     */
    @Override
    public Double getFirst() {

        if (size == 0) {
            return null;
        }

        return head.getValue();
    }

    /**
     * @return The value of the tail of the linked list
     */
    @Override
    public Double getLast() {

        if (size == 0) {
            return null;
        }

        return tail.getValue();
    }

    /**
     * @return The size of the linked list
     */
    @Override
    public Integer getSize() {
        return size;
    }


    /**
     * @param index
     * @return An element of the LinkedList based on an index.
     * If index is not present (List to small or negative index), throw IndexOutOfBoundsException
     * STUDENTS: implementing the exception is optional. You are allowed to return null a test will fail.
     */
    @Override
    public Double get(int index) {

        if (size == 0) {
            return null;
        }

        return getNode(index).getValue();
    }

    public LinkedListNode<Double> getNode(int index) {

        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bound");
            // return null;
        }

        LinkedListNode<Double> newHead = head;

        for (int i = 0; i < index; i++) {
            newHead = newHead.getNext();
        }
        return newHead;
    }


    /**
     * @param d Adds an element to head of the linked list and increases the size.
     *          In case the head is already filled, this method also updates the previous/next node attributes.
     *          In case this is the element added, also fill the tail.
     */
    @Override
    public void addFirst(Double d) {

        LinkedListNode<Double> temp = new LinkedListNode<>(d);

        temp.setNext(head);
        temp.setPrevious(null);

        if (head != null) {
            head.setPrevious(temp);
        }

        if (tail == null) {
            tail = temp;
        }

        head = temp;

        size++;
    }

    /**
     * @param d Adds an element to tail of the linked list and increases the size.
     *          In case the tail is already filled, this method also updates the previous/next node attributes.
     *          In case this is the element added, also fill the head.
     */
    @Override
    public void addLast(Double d) {

        LinkedListNode<Double> temp = new LinkedListNode<>(d);

        temp.setNext(null);

        if (head == null) {
            temp.setPrevious(null);
            head = temp;
            tail = temp;
            size++;
            return;
        }


        tail.setNext(temp);
        temp.setPrevious(tail);
        tail = temp;

        size++;
    }

    /**
     * @param index
     * @param d     Adds an element to the linked list based on an index
     *              In case index out of bounds, throw and IndexOutOfBoundsException (optional but a test will fail if you don't)
     *              Updates the surrounding nodes' previous and next variables
     *              Also updates the size
     *              <p>
     *              TIP: be smart with how you 'move' to the correct element.
     *              Mind the time complexity - O(?)
     */
    @Override
    public void add(int index, Double d) {

        if (index == 0) {
            addFirst(d);
            return;

        } else if (index == size) {
            addLast(d);
            return;

        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bound");

        }

        LinkedListNode<Double> newNode = new LinkedListNode<Double>(d);
        LinkedListNode<Double> newHead = head;

        for (int i = 0; i < index; i++) {
            newHead = newHead.getNext();
        }

        LinkedListNode<Double> predecessor = newHead.getPrevious();

        newNode.setPrevious(predecessor);
        newNode.setNext(newHead);

        predecessor.setNext(newNode);
        newHead.setPrevious(newNode);

        size++;

    }

    /**
     * Removes the head of the linked list.
     * If there is only one element - also remove tail
     * Otherwise it updates the next elements previous/next node variables
     * Also updates the size
     */
    @Override
    public void removeFirst() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedListNode<Double> temp = head.getNext();
            temp.setPrevious(null);
            head = temp;
        }
        size--;
    }

    /**
     * Removes the tail of the linked list.
     * If there is only one element - also remove head
     * Otherwise it updates the previous elements previous/next node variables
     * Also updates the size
     */
    @Override
    public void removeLast() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedListNode<Double> temp = tail.getPrevious();
            temp.setNext(null);
            tail = temp;
        }
        size--;
    }

    /**
     * @param index Removes an element based on an index.
     *              In case index out of bounds, throw and IndexOutOfBoundsException (optional but a test will fail if you don't)
     *              Updates the surrounding nodes' previous and next variables
     *              Also updates the size
     */
    @Override
    public void remove(int index) {

        if (index == 0) {
            removeFirst();
            return;

        } else if (index == size) {
            removeLast();
            return;

        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bound");

        }

        LinkedListNode<Double> newHead = head;

        for (int i = 0; i < index; i++) {
            newHead = newHead.getNext();
        }

        LinkedListNode<Double> predecessor = newHead.getPrevious();
        LinkedListNode<Double> successor = newHead.getNext();

        successor.setPrevious(predecessor);
        predecessor.setNext(successor);

        size--;

    }

    /**
     * Sets the value of an element based on an index.
     * In case index out of bounds, throw and IndexOutOfBoundsException (optional but a test will fail if you don't)
     *
     * @param index
     * @param value
     */
    @Override
    public void set(int index, Double value) {
        LinkedListNode<Double> temp = getNode(index);
        temp.setValue(value);
    }

    /*
     * OPTIONAL:
     *  Make the most out of the doubly linked list by determining whether to start from the head or from the tail
     *  Can be interesting for the Add(), Get() and Remove() methods
     *
     *  Also, optional: implement the following methods:
     *  Clear()
     *  Contains()
     *  IndexOf()
     * */


}
