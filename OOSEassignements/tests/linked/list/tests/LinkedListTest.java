package linked.list.tests;

import linked.list.LinkedList;
import linked.list.LinkedListNode;
import linked.list.OOSELinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private final Double firstElement = 1.0;
    private final Double secondElement = 2.0;
    private final Double thirdElement = 3.0;
    private final Double fourthElement = 4.0;
    private OOSELinkedList list = null;

    private Field headField;
    private Field tailField;
    private Field sizeField;
    private LinkedListNode headNode;
    private LinkedListNode tailNode;
    private int size;


    @BeforeEach
    void before() throws NoSuchFieldException {
        list = new LinkedList();
        headField = LinkedList.class.getDeclaredField("head");
        headField.setAccessible(true);

        tailField = LinkedList.class.getDeclaredField("tail");
        tailField.setAccessible(true);

        sizeField = LinkedList.class.getDeclaredField("size");
        sizeField.setAccessible(true);

        headNode = null;
        tailNode = null;
        size=0;
    }


    @Test
    void addFirst_should_set_firstElement_correctly() throws IllegalAccessException {
        LinkedList list = new LinkedList();

        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);

        assertEquals(firstElement, headNode.getValue());

        list.addFirst(secondElement);
        headNode = (LinkedListNode) headField.get(list);

        assertEquals(secondElement, headNode.getValue());
    }

    @Test
    void addFirst_should_set_lastElement_if_lastElement_is_null() throws IllegalAccessException {
        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());

        list.addFirst(secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());
    }

    @Test
    void addFirst_should_update_previous_and_next_nodes_elements_correctly() throws IllegalAccessException {
        list.addFirst(firstElement);
        list.addFirst(secondElement);
        list.addFirst(thirdElement);

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);


        assertEquals(thirdElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(firstElement, headNode.getNext().getNext().getValue());
        assertEquals(firstElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(thirdElement, tailNode.getPrevious().getPrevious().getValue());

    }

    @Test
    void addFirst_should_increase_size_when_adding_element() throws IllegalAccessException {

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);


        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);


        list.addFirst(secondElement);
        assertEquals(2, calculateSizeForward(list));
        assertEquals(2, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(2, size);

    }

    @Test
    void get_should_return_correct_element() {

        list.addFirst(thirdElement);
        list.addFirst(secondElement);
        list.addFirst(firstElement);

        assertEquals(firstElement, list.get(0));
        assertEquals(secondElement, list.get(1));
        assertEquals(thirdElement, list.get(2));
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void get_should_throw_exception_if_index_out_of_bounds() {

        list.addFirst(thirdElement);
        list.addFirst(secondElement);
        list.addFirst(firstElement);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }


    @Test
    void addLast_should_set_lastElement_correctly() throws IllegalAccessException {
        list.addLast(firstElement);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, tailNode.getValue());

        list.addLast(secondElement);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void addLast_should_set_firstElement_if_firstElement_is_null() throws IllegalAccessException {
        list.addLast(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());

        list.addLast(secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void addLast_should_increase_size_when_adding_element() throws IllegalAccessException {

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addLast(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.addLast(secondElement);
        assertEquals(2, calculateSizeForward(list));
        assertEquals(2, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(2, size);
    }

    @Test
    void addLast_should_update_previous_and_next_nodes_elements_correctly() throws IllegalAccessException {
        list.addLast(firstElement);
        list.addLast(secondElement);
        list.addLast(thirdElement);

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);


        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(thirdElement, headNode.getNext().getNext().getValue());
        assertEquals(thirdElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getValue());

    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void add_should_throw_exception_if_index_out_of_bounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, firstElement));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, firstElement));

        assertDoesNotThrow(() -> list.add(0, firstElement));
    }

    @Test
    void add_should_fill_first_element_correctly_when_list_empty() throws IllegalAccessException {
        list.add(0, firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());
    }

    @Test
    void add_should_fill_last_element_correctly_when_index_equals_size() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void add_should_increase_size_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, thirdElement);
        list.add(1, secondElement);

        assertEquals(3, calculateSizeForward(list));
        assertEquals(3, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(3, size);
    }

    @Test
    void add_should_set_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, thirdElement);
        list.add(1, secondElement);

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(thirdElement, headNode.getNext().getNext().getValue());

        assertEquals(thirdElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getValue());

    }

    @Test
    void removeFirst_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeFirst();
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);
    }

    @Test
    void removeFirst_should_clear_both_head_and_tail_when_list_contains_only_one_element() throws IllegalAccessException {
        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeFirst();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        assertNull(headNode);
        assertNull(tailNode);
    }

    @Test
    void removeFirst_should_update_head_if_more_than_one_element_exits() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        list.removeFirst();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);
    }

    @Test
    void removeFirst_should_update_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);

        list.removeFirst();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(headNode.getPrevious());
        assertNull(headNode.getNext());

        list.add(1, thirdElement);
        list.add(2, fourthElement);
        list.removeFirst();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(thirdElement, headNode.getValue());
        assertEquals(fourthElement, headNode.getNext().getValue());
        assertNull(headNode.getNext().getNext());

        assertEquals(fourthElement, tailNode.getValue());
        assertEquals(thirdElement, tailNode.getPrevious().getValue());
        assertNull(tailNode.getPrevious().getPrevious());

    }

    @Test
    void removeLast_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeLast();
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);
    }

    @Test
    void removeLast_should_clear_both_head_and_tail_when_list_contains_only_one_element() throws IllegalAccessException {
        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeLast();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(headNode);
        assertNull(tailNode);

    }

    @Test
    void removeLast_should_should_update_tail_if_more_than_one_element_exits() throws IllegalAccessException {
        list.addFirst(firstElement);
        list.addLast(secondElement);
        list.addLast(thirdElement);

        list.removeLast();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void removeLast_should_update_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);

        list.removeLast();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(tailNode.getNext());
        assertNull(tailNode.getPrevious());

        list.add(1, thirdElement);
        list.add(2, fourthElement);
        list.removeLast();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(thirdElement, headNode.getNext().getValue());
        assertNull(headNode.getNext().getNext());

        assertEquals(thirdElement, tailNode.getValue());
        assertEquals(firstElement, tailNode.getPrevious().getValue());
        assertNull(tailNode.getPrevious().getPrevious());
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void remove_should_throw_exception_if_index_out_of_bounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertDoesNotThrow(() -> {
            list.add(0, firstElement);
            list.remove(0);
        });
    }


    @Test
    void remove_should_remove_head_and_tail_when_list_will_be_empty() throws IllegalAccessException {
        list.add(0, firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.remove(0);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        assertNull(headNode);
        assertNull(tailNode);
    }


    @Test
    void remove_should_set_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        list.add(2, thirdElement);
        list.add(3, fourthElement);

        list.remove(2);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(3, calculateSizeForward(list));
        assertEquals(3, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(3, size);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(fourthElement, headNode.getNext().getNext().getValue());
        assertNull(headNode.getNext().getNext().getNext());

        assertEquals(fourthElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getValue());
        assertNull(tailNode.getPrevious().getPrevious().getPrevious());

    }

    @Test
    void remove_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.remove(0);
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);    }

    @Test
    void set_should_replace_an_existing_value() throws IllegalAccessException {
        list.add(0, firstElement);

        list.set(0, secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, headNode.getValue());
        assertNotEquals(firstElement, headNode.getValue());
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void set_should_throw_index_out_of_bounds_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, firstElement));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, firstElement));
        assertDoesNotThrow(() -> {
            list.add(0, firstElement);
            list.set(0, secondElement);
        });
    }

    @Test
    void getSize_should_return_correct_size() throws IllegalAccessException {
        assertEquals(0,list.getSize());
        assertEquals(0,calculateSizeForward(list));
        assertEquals(0,calculateSizeBackwards(list));

        list.add(0,firstElement);
        assertEquals(1,list.getSize());
        assertEquals(1,calculateSizeForward(list));
        assertEquals(1,calculateSizeBackwards(list));

        list.add(1,secondElement);
        assertEquals(2,list.getSize());
        assertEquals(2,calculateSizeForward(list));
        assertEquals(2,calculateSizeBackwards(list));


    }


    /*** UTILITY METHODS   ***/
    private int calculateSizeForward(OOSELinkedList list) throws IllegalAccessException {
        int counter = 0;
        if (list != null) {

            LinkedListNode headNode = (LinkedListNode) headField.get(list);
            if (headNode == null)
                return counter;
            counter++;

            while (headNode.hasNext()) {
                counter++;
                headNode = headNode.getNext();
            }
        }
        return counter;
    }

    private int calculateSizeBackwards(OOSELinkedList list) throws IllegalAccessException {
        int counter = 0;
        if (list != null) {
            LinkedListNode tailNode = (LinkedListNode) tailField.get(list);
            if (tailNode == null)
                return counter;
            counter++;


            while (tailNode.hasPrevious()) {
                counter++;
                tailNode = tailNode.getPrevious();
            }
        }
        return counter;
    }


}