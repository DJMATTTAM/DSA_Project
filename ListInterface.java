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
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterface<T>
{
    boolean isEmpty();
    int size();
    void add(int index, T item)
    throws ListIndexOutOfBoundsException;
    T get(int index)
    throws ListIndexOutOfBoundsException;
    T remove(int index)
    throws ListIndexOutOfBoundsException;
    void removeAll();
}  // end ListInterface
