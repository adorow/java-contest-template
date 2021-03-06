package com.github.adorow.template.datastructure;

/**
 * Standard bounded queue implementation that holds {@code int}s.<br/>
 * This implementation does not check for any boundaries when executing modifying operations, is the user's responsibility to check those whenever necessary.
 */
public class IntQueue {

    private int[] q;
    private int capacity;

    private int ini, end;

    /**
     * Creates a new queue of a fixed size.
     * @param size the maximum number of elements this queue can hold.
     */
    public IntQueue(int size) {
        this.capacity = size;
        this.q = new int[size];
        empty();
    }

    /**
     * The amount of elements that this queue can hold.
     * @return the capacity of the queue.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the amount of elements that the queue currently holds.
     * @return the current size of the queue.
     */
    public int size() {
        if (ini <= end) {
            return end - ini;
        }
        return (capacity - end) + ini;
    }

    /**
     * Empties the queue. Removing every element it contains. Resulting in the next call to #isEmpty() to return {@code true}, if no new element is added.
     */
    public void empty() {
        ini = end = 0;
    }

    /**
     * Tells whether the queue is currently empty.
     * @return {@code true} if the queue is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return ini == end;
    }

    /**
     * Adds a new element to the end of the queue.
     * @param v the element to be added.
     */
    public void enqueue(int v) {
        q[end++ % capacity] = v;
    }

    /**
     * Removes the first element of the queue.
     * @return the first element of the queue.
     */
    public int dequeue() {
        return q[ini++ % capacity];
    }

}