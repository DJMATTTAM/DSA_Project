/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************
public class ListRA<T> implements ListInterface<T>
{

    private static final int DEFAULTSIZE = 120;
    protected int size;
    protected T []items;  // an array of list items
    protected int numItems;  // number of items in list

    public ListRA()
    {
    	size = DEFAULTSIZE;
        items = (T[]) new Object[size];
        numItems = 0;
    }  // end default constructor
    
    public ListRA(int customSize)
    {
    	size = customSize;
        items = (T[]) new Object[size];
        numItems = 0;
    }  // end default constructor

    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty

    public int size()
    {
        return numItems;
    }  // end size

    public void removeAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items = (T[]) new Object[size];
        numItems = 0;
    } // end removeAll

    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems == items.length)
            //fixes implementation errors
            //fixes programming style
        {
        	Object[] newItems = new Object[numItems*(3/2) + 1];
			
			for(int i = 0; i < numItems; i++){ //Only needs to fill entries should be null by default.
				newItems[i] = items[i]; //fills the new array with elements from the old array
			}
			
			items = (T[]) newItems;
            //throw new ListException("ListException on add");
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add

    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }  // end if
    } // end get

    public T remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
        	// saving object for return:
        	T result = items[index];
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException

            {
                items[pos-1] = items[pos];
            }  // end for
            numItems--;
            items[numItems] = null; //fixes memory leak
            
            return result;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }  // end if
    } //end remove
    
    @Override
    public String toString() {
        StringBuilder result;

        result = new StringBuilder("\tList of size ").append(numItems).append(" has the following items : ");

        for(int i = 0; i < numItems; i++) {
            if (get(i) == null)
            {
                result.append("");
            }
            else
            {
                result.append(" ");
                result.append(get(i));
            }
        }

        return result.toString();
    }
}