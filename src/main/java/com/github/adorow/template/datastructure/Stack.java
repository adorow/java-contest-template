package com.github.adorow.template.datastructure;

/**
 * Standard Stack implementation.<br/>
 * This implementation does not check for any boundaries when executing modifying operations, is the user's responsibility to check those whenever necessary.
 */
public class Stack<T> {

    private T[] S;
    private int len;

    /**
     * Creates a new Stack of a fixed size.
     * @param size the maximum number of elements this Stack can hold.
     */
    public Stack(int size) {
        S = (T[]) new Object[size];
        len = 0;
    }

    /**
     * Cleans up the Stack, removing all its elements.
     */
    public void empty() {
        len = 0;
    }

    /**
     * Pushes a new element into the top of the stack.
     * @param v the element to add to the top of the stack.
     */
    public void push(T v) {
        S[len++] = v;
    }

    /**
     * Pops and element out of the top of the stack.
     * @return the top element in the stack.
     */
    public T pop() {
        return S[--len];
    }

    /**
     * Retrieves the top element of the stack, without modifying the stack.
     * @return the top element in the stack.
     */
    public T peek() {
        return S[len - 1];
    }

    /**
     * Tells whether the stack is full.
     * @return {@code true} if the stack is full, {@code false} otherwise.
     */
    public boolean isFull() {
        return len == S.length;
    }

    /**
     * Tells whether the stack is empty.
     * @return {@code true} if the stack is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return len == 0;
    }

}
