
/**
 * The Stack class is  generic stack implementation that supports basic stack operations such as
 * push, pop, peek, and checking if the stack is empty.
 * The stack automatically expands its capacity when it is full.
 * @param <T> the type of elements in this stack
 */
public class Stack<T> {
    private T[] array;
    private int size;
    private int capacity;

    /**
     * Constructs an empty stack with an initial capacity of 10
     */
    public Stack() {
        this.capacity = 10;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Pushes the specified element onto the top of the stack
     *
     * @param value the element to be pushed onto the stack
     */
    public void push(T value) {
        if (size == capacity) {
            expandCapacity();
        }
        array[size] = value;
        size++;
    }

    /**
     * Removes and returns the element at the top of the stack
     *
     * @return the element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack underflow");
        }
        size--;
        return array[size];
    }

    /**
     * Returns the element at the top of the stack without removing it
     *
     * @return the element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) array[size - 1];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    public int getSize() {
        return size;
    }

    /**
     * Doubles the capacity of the stack.
     */
    private void expandCapacity() {
        int newCapacity = capacity * 2;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newCapacity;
    }
}