
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;



// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Bryan Quirke
 *  @version 12/10/14 11:16:54
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class implements the Iterable<T> interface, allowing to use iterators
 * over the data stored in the doubly-linked list. This is already implemented.
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T>
{
	 int capacity;

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public T data;
        public DLLNode next;
        public DLLNode prev;
       
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode( T theData, DLLNode prevNode, DLLNode nextNode ) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
          capacity++;
        }
    }

    // the DLLNode class is defined at the end of this file.
    // Fields head and tail point to the first and last nodes of the list.
    //
    private DLLNode head, tail;
  

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null; 
      tail = null;
     
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean isEmpty()
    {
      if(head == null){
        return true;
      }
      return false;
    }

    /**
     * Inserts an element at the beginning of the doubly linked list
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void insertFirst( T data ) 
    {
      DLLNode newNode;

      if(isEmpty()){
    	  newNode = new DLLNode(data, null, null);
    	  head = newNode;
    	  tail = newNode;
      }
      
      else{
    	  
    	  newNode = new DLLNode(data, null, head);
    	  head.prev = newNode;
    	  head = newNode;
      }
    	  
      
      }

    /**
     * Inserts an element at the end of the doubly linked list
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void insertLast( T data ) 
    {
    	DLLNode newNode;

        if(isEmpty()){
      	  newNode = new DLLNode(data, null, null);
      	  head = newNode;
      	  tail = newNode;
        }
        
        else{
        	newNode = new DLLNode(data, tail, null);
        	tail.next = newNode;
        	tail = newNode;
        }
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void insertBefore( int pos, T data ) 
    {
    	DLLIterator cycle = new DLLIterator();
    	
    	
      if(pos <= 0){
    	  
    	  insertFirst(data);
      }
      
      else if(pos >= capacity){
    	  insertLast(data);
    	  
      }  
      
      else{
    	  
    	  int x = 0;
    	  
    	  DLLNode insert = cycle.currentNode;
    	  
    	  while(x != pos){
    		  x++;
    		  insert = cycle.nextNode();
    		  
    	  }
    	  
    	  DLLNode before = insert.prev;
    	  DLLNode newNode = new DLLNode(data, before, insert);
    	  
    	  before.next = newNode;
    	  insert.prev = newNode;
    	  
      }
      
    	  
    	  
      
    }

    
    /**
     * @return the data at the beginning of the list, if the list is non-empty, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T getFirst()
    {
    	DLLIterator cycle = new DLLIterator();
    	if(!cycle.hasNext()){
    		return null;
    	}
    	T item = cycle.next();
      
      return item;
    }

    /**
     * @return the data at the of the list, if the list is non-empty, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T getLast()
    {
    	if(isEmpty()){
    		return null;
    	}
    	
    	T item = tail.data;
    	
      return item;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     *
     * Worst-case precise runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T get( int pos ) 
    {
    	if(isEmpty()){
    		return null;
    	}
    	
    	int x = 0;
    	DLLIterator cycle = new DLLIterator();
    	DLLNode insert = cycle.currentNode;
    	
    	while(x != pos){
  		  x++;
  		  insert = cycle.nextNode();
  		  
  	  }
    	T item = insert.data;
    	
      return item;
    }


    /**
     * Deletes an element at the beginning of the doubly linked list
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean deleteFirst() 
    {
    	
    	
    	DLLNode delete = head.next;
    	delete.prev = null;
    	head= delete;
    	
    	return true;
    }

    /**
     * Deletes an element at the end of the doubly linked list
     * @param data : The new data of class T that needs to be added to the list
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean deleteLast() 
    {
    	
    	DLLNode delete = tail;
    	delete = tail.prev;
    	delete.next = null;
    	tail = delete;
    	return true;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position in the list to be deleted.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean deleteAt( int pos ) 
    {
    	int x = 0;
    	DLLIterator cycle = new DLLIterator();
    	DLLNode insert = cycle.currentNode;
    	
    	while(x != pos){
  		  x++;
  		  insert = cycle.nextNode();
  		  
  	  }
    	
    	DLLNode next = insert.next;
    	DLLNode prev = insert.prev;
    	
    	next.prev = prev;
    	prev.next = next;
      return true;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (T i : this)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(i.toString());
      }

      return s.toString();
    }



    /**
     * Returns an iterator over the DLL. The iterator supports the hasNext() and next() methods  but not the remove() method.
     * @return an object implementing the Iterator interface
     */

    public Iterator<T> iterator() { return new DLLIterator(); }

    private class DLLIterator implements Iterator<T>
    {
        private DLLNode current = head;
        
        DLLNode currentNode = head;
        
        DLLNode nextNode;
        

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException("remove() is not supported"); }
        public T next()
        {
            if (current == null)
                throw new NoSuchElementException("No next element in the list");
            T item = current.data;
            current = current.next;
            return item;
        }
        
        public DLLNode nextNode()
        {
        	nextNode = currentNode.next;
        	currentNode = currentNode.next;
        	return nextNode;
        }
    }



    /**
     * Returns a list iterator over the DLL. The iterator supports forwards and backwards iteration over the list but no modification operations.
     * @return an object implementing the ListIterator interface
     */

    public ListIterator<T> listIterator() { return new DLLListIterator(); }

    private class DLLListIterator implements ListIterator<T>
    {
        /**
         * Returns true if this list iterator has more elements when
         * traversing the list in the forward direction. (In other words,
         * returns true if next() would return an element rather
         * than throwing an exception.)
         *
         * @return true if the list iterator has more elements when
         *         traversing the list in the forward direction
         */
        public boolean hasNext()
        {
          // TODO: implement this method
          return false;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to previous() to go back and forth.
         * (Note that alternating calls to next() and previous()
         * will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        public T next()
        {
          // TODO: implement this method
          return null;
        }

        /**
         * Returns true if this list iterator has more elements when
         * traversing the list in the reverse direction.  (In other words,
         * returns true if previous() would return an element
         * rather than throwing an exception.)
         *
         * @return true if the list iterator has more elements when
         *         traversing the list in the reverse direction
         */
        public boolean hasPrevious()
        {
          // TODO: implement this method
          return false;
        }

        /**
         * Returns the previous element in the list and moves the cursor
         * position backwards.  This method may be called repeatedly to
         * iterate through the list backwards, or intermixed with calls to
         * next() to go back and forth.  (Note that alternating calls
         * to next() and previous() will return the same
         * element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous
         *         element
         */
        public T previous()
        {
          // TODO: implement this method
          return null;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to next(). (Returns list size if the list
         * iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a
         *         subsequent call to next(), or list size if the list
         *         iterator is at the end of the list
         */
        public int nextIndex()
        {
          // TODO: implement this method
          return -1;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to previous(). (Returns -1 if the list
         * iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a
         *         subsequent call to previous(), or -1 if the list
         *         iterator is at the beginning of the list
         */
        public int previousIndex()
        {
          // TODO: implement this method
          return -1;
        }

       public void remove() { throw new UnsupportedOperationException("remove() operation is not supported."); }
       public void set(T e) { throw new UnsupportedOperationException("set() operation is not supported."); }
       public void add(T e) { throw new UnsupportedOperationException("set() operation is not supported."); }

    }

}



