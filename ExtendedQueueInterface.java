/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
public interface ExtendedQueueInterface<T> extends QueueInterface<T> {
    public void enqueueFirst(T newItem) throws ExtendedQueueException;
    public T dequeueLast() throws ExtendedQueueException;
    public T peekLast() throws ExtendedQueueException;
}  // end ExtendedQueueInterface

