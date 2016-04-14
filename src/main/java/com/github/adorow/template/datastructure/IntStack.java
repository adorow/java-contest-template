package com.github.adorow.template.datastructure;

/**
 * Standard bounded stack implementation that holds {@code int}s.<br/>
 * This implementation does not check for any boundaries when executing modifying operations, is the user's responsibility to check those whenever necessary.
 */
public class IntStack {

    private int[] S;
    private int len;

    /**
     * Creates a new stack of a fixed size.
     * @param size the maximum number of elements this Stack can hold.
     */
    public IntStack(int size) {
        S = new int[size];
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
    public void push(int v) {
        S[len++] = v;
    }

    /**
     * Pops and element out of the top of the stack.
     * @return the top element in the stack.
     */
    public int pop() {
        return S[--len];
    }

    /**
     * Retrieves the top element of the stack, without modifying the stack.
     * @return the top element in the stack.
     */
    public int peek() {
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