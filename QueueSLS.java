/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
public class QueueSLS<T> implements QueueInterface<T> {

    private Node<T> front, back;

    public QueueSLS() {
        super();
        // TODO Auto-generated constructor stub

        front = null;
        back = null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return (front == null);
    }

    @Override
    public void enqueue(T newItem) throws QueueException {
        // TODO Auto-generated method stub
        if(isEmpty()) {
            back = new Node<T>(newItem);
            front = back;
        } else {
            back.setNext(new Node<T>(newItem));
            back = back.getNext();
        }
    }

    @Override
    public T dequeue() throws QueueException {
        // TODO Auto-generated method stub
        T result = null;

        if(isEmpty()) {
            throw new QueueException("Error in dequeue");
        } else {
            result = (T) front.getItem();
            front = front.getNext();
        }
        return result;
    }

    @Override
    public void dequeueAll() {
        // TODO Auto-generated method stub

        front = null;
        back = null;
    }

    @Override
    public T peek() throws QueueException {
        // TODO Auto-generated method stub
        T result = null;

        if(isEmpty()) {
            throw new QueueException("Error in dequeue");
        } else {
            result = (T) front.getItem();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result;

        if (isEmpty()) {
            result = new StringBuilder("Queue is empty.");
        } else {
            result = new StringBuilder("\tQueue has the following items : ");

            Node<T> curr = front;
            while(curr != null) {
                result.append(" ");
                result.append(curr.getItem().toString());

                curr = curr.getNext();
            }
        }

        return result.toString();
    }

}
