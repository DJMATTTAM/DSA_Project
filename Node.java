/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
//please note that this code is different from the textbook code, because the data is encapsulated!

public class Node<T>
{
    protected T item;
    protected Node<T> next;

    public Node(T newItem)
    {
        item = newItem;
        next = null;
    } // end constructor

    public Node(T newItem, Node<T> nextNode)
    {
        item = newItem;
        next = nextNode;
    } // end constructor

    public void setItem(T newItem)
    {
        item = newItem;
    } // end setItem

    public Object getItem()
    {
        return item;
    } // end getItem

    public void setNext(Node<T> nextNode)
    {
        next = nextNode;
    } // end setNext

    public Node<T> getNext()
    {
        return next;
    } // end getNext
} // end class Node
