import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Bryan Quirke
 *  @version 2014.01.29
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructors ........................................................
    @Test
    public void testConstructorInteger()
    {
      new DoublyLinkedList<Integer>();
    }
    
    @Test
	public void testConstructorString()
	{
		new DoublyLinkedList<String>();
	}


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if insert works at the beginning
     */
    @Test
    public void testInsertFirst()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertFirst(1);
        assertEquals( "Checking insertion at beginning", "1", testDLL.toString() );
        testDLL.insertFirst(2);
        assertEquals( "Checking insertion at beginning", "2,1", testDLL.toString() );
        testDLL.insertFirst(3);        
        assertEquals( "Checking insertion at beginning", "3,2,1", testDLL.toString() );
    }

    // ----------------------------------------------------------
    /**
     * Check if the insert works at the end
     */
    @Test
    public void testInsertLast()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertLast(1);
        assertEquals( "Checking insertion at end", "1", testDLL.toString() );
        testDLL.insertLast(2);
        assertEquals( "Checking insertion at end", "1,2", testDLL.toString() );
        testDLL.insertLast(3);        
        assertEquals( "Checking insertion at end", "1,2,3", testDLL.toString() );
    }

    // ----------------------------------------------------------
    /**
     * Check if the insert works in the middle
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertLast(1);
        testDLL.insertLast(2);
        testDLL.insertLast(3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertion at 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertion at 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);        
        assertEquals( "Checking insertion at 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertion at 2", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertion at 2", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertion at 2", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertion at 2", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertion at 2", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertion at 2", "1", testDLL.toString() );      
     }
    
 //********____________________________ Tests implemented by Author_______________________________________********   
   @Test
    public void testBeforeStrings()
    {
    	
        DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
        testDLL.insertBefore(0, "March");
        testDLL.insertBefore(0, "Feb");
        testDLL.insertBefore(0, "Jan");
        assertEquals("Checking insertion at 0", "Jan,Feb,March", testDLL.toString() );
  
    } 
   
   @Test
   public void testGetFirst()
   {
   	DoublyLinkedList< Integer > testDLL = new DoublyLinkedList< Integer >();
   	
   	assertEquals( null, testDLL.getFirst() );
   	
   	testDLL.insertFirst( 1 );
   	assertEquals( new Integer( 1 ), testDLL.getFirst() );

   	testDLL.insertFirst( 2 );
   	assertEquals( new Integer( 2 ), testDLL.getFirst() );	
   }
   

   @Test
   public void testGetLast()
   {
	   
	DoublyLinkedList< Integer > testDLL = new DoublyLinkedList< Integer >();
	
	assertEquals( null, testDLL.getLast() );
	
   	testDLL.insertLast( 1 );
   	assertEquals( new Integer( 1 ), testDLL.getLast() );

   	testDLL.insertLast( 2 );
   	assertEquals( new Integer( 2 ), testDLL.getLast() );

   	testDLL.insertLast( 3 );
   	assertEquals( new Integer( 3 ), testDLL.getLast() );
   }

   
   @Test
   public void test_get()
   {
	   DoublyLinkedList< Integer > testDLL = new DoublyLinkedList< Integer >();
	   
	   testDLL.get(0);
	   assertEquals( null, testDLL.get(0) );
	   
	   testDLL.insertLast(1);
       testDLL.insertLast(2);
       testDLL.insertLast(3);
       
       testDLL.get(1);
       assertEquals( new Integer(2), testDLL.get(1));

   }
   
   @Test
   public void testDeleteLast()
   {
   	DoublyLinkedList< Integer > testDLL = new DoublyLinkedList< Integer >();
   	
   	  testDLL.insertLast(1);
      testDLL.insertLast(2);
      testDLL.insertLast(3);
      testDLL.insertLast(4);
      testDLL.insertLast(5);

      testDLL.deleteLast();
      assertEquals( "Delete last element", "1,2,3,4", testDLL.toString() );

      testDLL.deleteLast();
      assertEquals( "Delete last element", "1,2,3", testDLL.toString() );

      testDLL.deleteLast();
      assertEquals( "Delete last element", "1,2", testDLL.toString() );

      testDLL.deleteLast();
      assertEquals( "Delete last element", "1", testDLL.toString() );

     
      
   }
   
   @Test
   public void testDeleteFirst()
   {
   	DoublyLinkedList< Integer > testDLL = new DoublyLinkedList< Integer >();
   	testDLL.insertLast(25);
      testDLL.insertLast(36);
      testDLL.insertLast(47);
      testDLL.insertLast(58);
      testDLL.insertLast(69);

      testDLL.deleteFirst();
      assertEquals( "Delete head element", "36,47,58,69", testDLL.toString() );

      testDLL.deleteFirst();
      assertEquals( "Delete head element", "47,58,69", testDLL.toString() );

      testDLL.deleteFirst();
      assertEquals( "Delete head element", "58,69", testDLL.toString() );
      
      testDLL.deleteFirst();
      assertEquals( "Delete head element", "69", testDLL.toString() );
      
   }
   
   @Test
   public void test_deleteAt()
   {
	   DoublyLinkedList< Integer > testDLL = new DoublyLinkedList< Integer >();
	   
	   testDLL.insertLast(1);
	   testDLL.insertLast(2);
	   testDLL.insertLast(3);
	   testDLL.insertLast(4);
	   testDLL.insertLast(5);
	   
	   testDLL.deleteAt(1);
	   assertEquals( "1,3,4,5", testDLL.toString() );
	   
	   testDLL.deleteAt(2);
	   assertEquals( "1,3,5", testDLL.toString() );
	   
   }
   
    
   
    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.



}


