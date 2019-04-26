/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
public class QueueRA<T> implements QueueInterface<T> {

	private final int DEFAULTSIZE = 10;
    protected T items[];
    protected int front, back, numItems;

    public QueueRA() {
        super();
        // TODO Auto-generated constructor stub
        items = (T[]) new Object[DEFAULTSIZE];
        front = 0;
        back = 0;
        numItems = 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return numItems == 0;
    }

    @Override
    public void enqueue(T newItem) throws QueueException {
        // TODO Auto-generated method stub
        if (!isEmpty() && front == back) {
            resize();
        }
        items[back] = newItem;
        numItems++;
        back=(back + 1) % items.length;
        //System.out.println(back);
    }

    @Override
    public T dequeue() throws QueueException {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            T result = items[front];
            items[front] = null;
            numItems--;
            front = (front+1)%items.length;
            return result;
        } else {
            throw new QueueException("Error in dequeue");
        }
    }

    @Override
    public void dequeueAll() {
        // TODO Auto-generated method stub
        items = (T[]) new Object[DEFAULTSIZE];
        front = 0;
        back = 0;
        numItems = 0;
    }

    @Override
    public T peek() throws QueueException {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            return items[front];
        } else {
            throw new QueueException("Error in peek");
        }
    }

    protected void resize() {
        T[] resize = (T[]) new Object[numItems*(3/2) + 1];

        for (int i = 0; i < numItems; i++) {
            resize[i] = items[(front + i) % items.length];
        }

        front = 0;
        back = numItems;
        items = resize;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for(int i = 0; i < numItems; i++) {
            result.append(items[(front + i) % items.length].toString());
            result.append("\n");
        }

        return result.toString();
    }
    
    public int size() {
    	return numItems;
    }
}
