package stack.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stack.OOSEStack;
import stack.Stack;
import stack.StackNode;

import java.lang.reflect.Field;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests {

    private final Double firstElement = 1.0;
    private final Double secondElement = 2.0;
    private final Double thirdElement = 3.0;
    private OOSEStack stack = null;

    private Field topField;
    private Field sizeField;
    private StackNode topNode;
    private int size;

    @BeforeEach
    void before() throws NoSuchFieldException {
        stack = new Stack();
        topField = Stack.class.getDeclaredField("top");
        topField.setAccessible(true);

        sizeField = Stack.class.getDeclaredField("size");
        sizeField.setAccessible(true);

        topNode = null;
        size = 0;
    }

    @Test
    void push_should_put_element_on_top() throws IllegalAccessException {
        topNode = (StackNode) topField.get(stack);
        assertNull(topNode);

        stack.push(firstElement);
        topNode = (StackNode) topField.get(stack);
        assertNotNull(topNode);
        assertEquals(firstElement, topNode.getValue());
    }

    @Test
    void push_should_update_tops_next_variable_when_stack_not_empty() throws IllegalAccessException {
        stack.push(firstElement);
        topNode = (StackNode) topField.get(stack);
        assertNull(topNode.getNext());

        stack.push(secondElement);
        topNode = (StackNode) topField.get(stack);
        assertNotNull(topNode.getNext());
        assertEquals(firstElement, topNode.getNext().getValue());

    }

    @Test
    void push_should_increase_size() throws IllegalAccessException {
        size = (int) sizeField.get(stack);
        assertEquals(0, size);
        assertEquals(0, calculateSize(stack));

        stack.push(firstElement);
        size = (int) sizeField.get(stack);
        assertEquals(1, size);
        assertEquals(1, calculateSize(stack));

        stack.push(secondElement);
        size = (int) sizeField.get(stack);
        assertEquals(2, size);
        assertEquals(2, calculateSize(stack));
    }

    @Test
    void peek_should_throw_EmptyStackException_when_stack_empty() {
        assertThrows(EmptyStackException.class, () -> stack.peek());

        stack.push(firstElement);

        assertDoesNotThrow(() -> stack.peek());
    }
    @Test
    void peek_should_return_top_and_should_not_remove_top() throws IllegalAccessException {
        stack.push(firstElement);

        assertEquals(firstElement,stack.peek());
        topNode = (StackNode) topField.get(stack);
        assertEquals(firstElement,topNode.getValue());
    }

    @Test
    void pop_should_throw_EmptyStackException_when_stack_empty() {
        assertThrows(EmptyStackException.class, () -> stack.pop());

        stack.push(firstElement);

        assertDoesNotThrow(() -> stack.pop());
    }
    @Test
    void pop_should_return_top_and_should_update_top_if_stack_not_empty() throws IllegalAccessException {
        stack.push(firstElement);

        assertEquals(firstElement,stack.pop());
        topNode = (StackNode) topField.get(stack);
        assertNull(topNode);

        stack.push(secondElement);
        stack.push(thirdElement);
        assertEquals(thirdElement,stack.pop());
        topNode = (StackNode) topField.get(stack);
        assertEquals(secondElement,topNode.getValue());
    }
    @Test
    void pop_should_reduce_size() throws IllegalAccessException {
        stack.push(firstElement);
        stack.push(secondElement);

        assertEquals(secondElement,stack.pop());
        size = (int) sizeField.get(stack);
        assertEquals(1, size);
        assertEquals(1, calculateSize(stack));

        assertEquals(firstElement,stack.pop());
        size = (int) sizeField.get(stack);
        assertEquals(0, size);
        assertEquals(0, calculateSize(stack));
    }

    @Test
    void getSize_should_return_correct_size() throws IllegalAccessException {
        assertEquals(0,stack.getSize());
        assertEquals(0,calculateSize(stack));

        stack.push(firstElement);
        assertEquals(1,stack.getSize());
        assertEquals(1,calculateSize(stack));

        stack.push(secondElement);
        assertEquals(2,stack.getSize());
        assertEquals(2,calculateSize(stack));


    }


    /***UTILITY METHODS***/
    private int calculateSize(OOSEStack stack) throws IllegalAccessException {
        int counter = 0;
        topNode = (StackNode) topField.get(stack);

        if (stack != null && topNode != null) {
            counter++;
            while (topNode.hasNext()) {
                counter++;
                topNode = topNode.getNext();
            }
        }
        return counter;
    }

}
