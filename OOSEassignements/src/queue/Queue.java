package queue;

/**
 * This class is supposed to represent a queue
 * Finish the methods below
 * The tests can guide you in the right direction but are not absolute infallible
 */
public class Queue implements OOSEQueue {
    /***
     * Leave these three variables if you want to use the tests. RECOMMENDED
     */
    private int size = 0;
    private QueueNode<Double> front;
    private QueueNode<Double> rear;

    /**
     * Inserts the element at the end of the queue
     */
    @Override
    public void add(Double d) {
        QueueNode<Double> newNode = new QueueNode<>(d);
        QueueNode<Double> temp = front;

        if(size == 0){
            front = newNode;
            rear = newNode;
            size++;
            return;
        }

        newNode.setNext(null);

        while(temp.getNext() != null){
            temp = temp.getNext();
        }

        temp.setNext(newNode);
        rear = newNode;
        size++;

    }

    /**
     * Retrieves, but does not remove, the head of this queue or returns null when empty
     */
    @Override
    public Double peek() {

        if(size == 0){
            return null;

        }

        return front.getValue();
    }

    /**
     * Retrieves and removes the head of the queue
     */
    @Override
    public Double remove() {

        if(size == 0){
            throw new NullPointerException("Queue is empty");
        }

        QueueNode<Double> newFront = front;

        if(size == 1){
            front = null;
            rear = null;
            size--;
            return newFront.getValue();
        }

        front = newFront.getNext();
        size--;

        return newFront.getValue();
    }


    /**
     * @return the size of the queue
     */
    @Override
    public Integer getSize(){
            return size;
    }
}
