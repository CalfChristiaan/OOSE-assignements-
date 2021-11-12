package stack;

import java.util.EmptyStackException;

/**
 * This class is supposed to represent a queue
 * Finish the methods below
 * The tests can guide you in the right direction but are not absolute infallible
 */
public class Stack implements OOSEStack {
    /***
     * Leave these two variables if you want to use the tests. RECOMMENDED
     */
    private StackNode<Double> top;
    int size = 0;

    /**
     * @param d Pushes element to the top of the stack.
     *          If an element already exists on the top. Update the reference (Stacknode.next)
     *          Also increases size
     */
    @Override
    public void push(Double d) {
        StackNode<Double> temp = new StackNode<>(d);
        temp.setNext(null);

        if(size != 0){
            temp.setNext(top);
        }

        top = temp;
        size++;
    }

    /**
     * @return The element on top of the stack but does NOT remove it.
     * If stack is empty throw an EmptyStackException
     */
    @Override
    public Double peek() {

        if(size == 0){
            throw new EmptyStackException();
        }

        return top.getValue();
    }

    /**
     * @return The element on top of the stack and removes it.
     * If stack is empty throw an EmptyStackException
     *
     */
    @Override
    public Double pop() {

        if(size == 0){
            throw new EmptyStackException();
        }

        StackNode<Double> temp = top;
        top = temp.getNext();
        size--;
        return temp.getValue();
    }

    /**
     * @return the size of the stack
     */
    @Override
    public Integer getSize() {
        return size;
    }

}
