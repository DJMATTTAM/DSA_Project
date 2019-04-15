
public class DEQ<T> extends QueueRA<T> implements ExtendedQueueInterface<T> {

    public DEQ() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void enqueueFirst(T newItem) throws ExtendedQueueException {
        // TODO Auto-generated method stub
        if (!isEmpty() && front == back) {
            resize();
        }
        int index = ((items.length + front) - 1)%items.length;
        items[index] = newItem;
        numItems++;
        front=index;

        if (isEmpty()) {
            front = back;
        }
    }

    @Override
    public T dequeueLast() throws ExtendedQueueException {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            int index = ((items.length + back) - 1)%items.length;
            T result = items[index];
            items[index] = null;
            numItems--;
            back=index;
            return result;
        } else {
            throw new QueueException("Error in dequeueLast");
        }
    }

    @Override
    public T peekLast() throws ExtendedQueueException {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            return items[((items.length + back) - 1)%items.length];
        } else {
            throw new QueueException("Error in peekLast");
        }
    }
}
