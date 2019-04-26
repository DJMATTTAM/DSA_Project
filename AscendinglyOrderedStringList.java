/*
 * Purpose: Data Structure and Algorithms Lab 8 Problem 3
 * Status: Complete and thoroughly tested
 * Last update: 03/31/19
 * Submitted:  04/02/19
 * Comment: test suite and sample run attached
 * @author: Chris Ancheta
 * @version: 2019.31.03
 */
import java.util.StringJoiner;

public class AscendinglyOrderedStringList implements AscendinglyOrderedStringListInterface {
    private static final int MAX_LIST = 3;
    private String[] items;
    private int numItems;

    public AscendinglyOrderedStringList() {
        items = new String[MAX_LIST];
        numItems = 0;
    } // end default constructor

    @Override
    public boolean isEmpty() {
        return (numItems == 0);
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public void add(String item) throws ListIndexOutOfBoundsException, ListException {
        if (numItems == items.length) {
            grow();
        }
        if (numItems == 0) {
            items[0] = item;
            numItems++;
        }
        else {
            int index = search(item);
            if (index < 0) {
                index += numItems + 1;
                if (index < 0 || index > numItems) {
                    throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on add");
                } else {
                    if (items[index] != null) {
                        for (int pos = numItems - 1; pos >= index; pos--)
                        {
                            items[pos + 1] = items[pos];
                        } // end for
                    }
                    items[index] = item;
                    numItems++;
                }
            } else {
                throw new ListException("Name already in use.");
            }
        }
        System.out.println(toString());
    }

    @Override
    public String get(int index) throws ListIndexOutOfBoundsException {
        if (index >= numItems || index < 0) {
            throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on retrieve");
        } else {
            return items[index];
        }
    }

    @Override
    public String remove(int index) throws ListIndexOutOfBoundsException {
    	String result = items[index];
        if (index >= 0 && index < numItems) {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            // textbook code modified to eliminate logic error causing
            // ArrayIndexOutOfBoundsException
            for (int pos = index + 1; pos < numItems; pos++) {
                items[pos - 1] = items[pos];
            } // end for
            items[--numItems] = null;
        } else {
            // index out of range
            throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on remove");
        } // end if
        return result;
    }

    // Binary search
    @Override
    public int search(String item) {
        int low = 0;
        int high = numItems - 1;
        int mid = 0;
        if (item.compareTo(items[high]) > 0) {
            return numItems - (numItems + 1);
        }
        while (low < high) {
            mid = (low + high) / 2;
            if (item.compareTo(items[mid]) <= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (item.equals(items[low])) {
            return low;
        } else {
            return low - (numItems + 1);
        }
    }

    // Clear list
    @Override
    public void clear() {
        items = new String[MAX_LIST];
        numItems = 0;
    }

    // Increase collection size by (1.5 * size) + 1
    private void grow() {
        // copy each element from the old array to the new array
        // then update original reference and mark temporary reference for
        // garbage collection
        String[] temp = new String[(items.length + (items.length / 2)) + 1];
        for (int i = 0; i < items.length; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // Create a formatted string of all elements in the list
    // @return
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < numItems; i++) {
            sj.add(items[i].toString());
        }
        return sj.toString().trim();
    } // end toString
}
