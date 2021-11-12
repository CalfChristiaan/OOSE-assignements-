package queue.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queue.OOSEQueue;
import queue.Queue;
import queue.QueueNode;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private final Double firstElement = 1.0;
    private final Double secondElement = 2.0;
    private final Double thirdElement = 3.0;
    private final Double fourthElement = 4.0;
    private OOSEQueue queue = null;

    private Field frontField;
    private Field rearField;
    private Field sizeField;
    private QueueNode frontNode;
    private QueueNode rearNode;
    private int size;

    @BeforeEach
    void before() throws NoSuchFieldException {
        queue = new Queue();
        frontField = Queue.class.getDeclaredField("front");
        frontField.setAccessible(true);

        rearField = Queue.class.getDeclaredField("rear");
        rearField.setAccessible(true);

        sizeField = Queue.class.getDeclaredField("size");
        sizeField.setAccessible(true);

        frontNode = null;
        rearNode = null;
        size = 0;
    }

    @Test
    void add_should_set_both_front_and_rear_when_adding_first_element() throws IllegalAccessException {
        queue.add(firstElement);

        frontNode = (QueueNode) frontField.get(queue);
        rearNode = (QueueNode) rearField.get(queue);

        assertEquals(firstElement, frontNode.getValue());
        assertEquals(firstElement, rearNode.getValue());
    }

    @Test
    void add_should_update_next_variable_correctly_if_queue_not_empty() throws IllegalAccessException {
        queue.add(firstElement);
        queue.add(secondElement);
        queue.add(thirdElement);

        frontNode = (QueueNode) frontField.get(queue);
        rearNode = (QueueNode) rearField.get(queue);

        assertEquals(firstElement, frontNode.getValue());
        assertEquals(thirdElement, rearNode.getValue());

        assertEquals(secondElement, frontNode.getNext().getValue());
        assertEquals(3, calculateSize(queue));

    }

    @Test
    void add_should_increase_size() throws IllegalAccessException {
        size = (int) sizeField.get(queue);
        assertEquals(0, size);
        assertEquals(0, calculateSize(queue));

        queue.add(firstElement);
        size = (int) sizeField.get(queue);
        assertEquals(1, size);
        assertEquals(1, calculateSize(queue));


        queue.add(secondElement);
        size = (int) sizeField.get(queue);
        assertEquals(2, size);
        assertEquals(2, calculateSize(queue));
    }

    @Test
    void peek_should_return_front_but_not_remove_it() {
        queue.add(firstElement);

        assertEquals(firstElement, queue.peek());
        assertEquals(firstElement, queue.peek());
    }

    @Test
    void peek_should_return_null_when_queue_empty() {
        assertNull(queue.peek());
    }

    @Test
    void remove_should_return_front() {
        queue.add(firstElement);

        assertEquals(firstElement, queue.remove());
    }

    @Test
    void remove_should_update_next_node() throws IllegalAccessException {
        queue.add(firstElement);
        queue.add(secondElement);
        queue.add(thirdElement);

        assertEquals(firstElement, queue.remove());

        frontNode = (QueueNode) frontField.get(queue);
        rearNode = (QueueNode) rearField.get(queue);
        assertEquals(secondElement, frontNode.getValue());
        assertEquals(thirdElement, rearNode.getValue());


    }

    @Test
    void remove_should_clear_both_front_and_rear_when_only_on_element_present() throws IllegalAccessException {
        queue.add(firstElement);

        assertEquals(firstElement, queue.remove());
        frontNode = (QueueNode) frontField.get(queue);
        rearNode = (QueueNode) rearField.get(queue);

        assertNull(frontNode);
        assertNull(rearNode);
    }

    @Test
    void remove_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSize(queue));
        size = (int) sizeField.get(queue);
        assertEquals(0, size);

        queue.add(firstElement);
        assertEquals(1, calculateSize(queue));
        size = (int) sizeField.get(queue);
        assertEquals(1, size);

        assertEquals(firstElement, queue.remove());
        assertEquals(0, calculateSize(queue));
        size = (int) sizeField.get(queue);
        assertEquals(0, size);
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void remove_should_throw_nullpointer_exception_when_queue_empty() {
        assertThrows(NullPointerException.class, () -> queue.remove());

        queue.add(firstElement);
        assertDoesNotThrow(() -> queue.remove());
    }

    @Test
    void getSize_should_return_correct_size() throws IllegalAccessException {
        assertEquals(0,queue.getSize());
        assertEquals(0,calculateSize(queue));

        queue.add(firstElement);
        assertEquals(1,queue.getSize());
        assertEquals(1,calculateSize(queue));

        queue.add(secondElement);
        assertEquals(2,queue.getSize());
        assertEquals(2,calculateSize(queue));


    }


    /*** UTILITY METHODS***/

    private int calculateSize(OOSEQueue queue) throws IllegalAccessException {
        int counter = 0;
        frontNode = (QueueNode) frontField.get(queue);


        if (queue != null && frontNode != null) {
            counter++;
            while (frontNode.hasNext()) {
                counter++;
                frontNode = frontNode.getNext();
            }
        }

        return counter;
    }
}