import static org.junit.Assert.*;
  
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
  
//-------------------------------------------------------------------------
/**
*  Test class for Doubly Linked List
*
*  @version 3.0 26/11/14 16:53:05
*
*  @author  Bryan Quirke
*/
  
@RunWith(JUnit4.class)
public class BSTTest
{   
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
        
	 @Test
	 public void testPrettyPrint()
	 {
	     BST<Integer, Integer> bst = new BST<Integer, Integer>();
	     assertEquals("Checking pretty printing of empty tree",
	             "-null", bst.prettyPrintKeys());
	        
	                          //  -7
	                          //   |-3
	                          //   | |-1
	                          //   | | |-null
	     bst.put(7, 7);       //   | |  -2
	     bst.put(8, 8);       //   | |   |-null
	     bst.put(3, 3);       //   | |    -null
	     bst.put(1, 1);       //   |  -6
	     bst.put(2, 2);       //   |   |-4
	     bst.put(6, 6);       //   |   | |-null
	     bst.put(4, 4);       //   |   |  -5
	     bst.put(5, 5);       //   |   |   |-null
	                          //   |   |    -null
	                          //   |    -null
	                          //    -8
	                          //     |-null
	                          //      -null
	       
	     String result = 
	      "-7\n" +
	      " |-3\n" + 
	      " | |-1\n" +
	      " | | |-null\n" + 
	      " | |  -2\n" +
	      " | |   |-null\n" +
	      " | |    -null\n" +
	      " |  -6\n" +
	      " |   |-4\n" +
	      " |   | |-null\n" +
	      " |   |  -5\n" +
	      " |   |   |-null\n" +
	      " |   |    -null\n" +
	      " |    -null\n" +
	      "  -8\n" +
	      "   |-null\n" +
	      "    -null\n";
	     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }
  
    
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete()
     {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
           
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
           
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
           
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
   
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
   
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
   
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
     }
       
     @Test
     public void testHeight()
     {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Checking Height", -1, bst.height());
         bst.put(7, 7);
         assertEquals("Checking Height", 0, bst.height());
         bst.put(8, 8);
         assertEquals("checking Height", 2, bst.height());
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);
         //bst.put(9, 9);
         //bst.put(15, 15);
         //bst.put(55, 55);
         assertEquals("Checking Height", 5, bst.height());
     }
       
     @Test
     public void testMedian()
     {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Checking Median", null, bst.median());
         bst.put(7, 7);
         assertEquals("Checking Median", "7", bst.median().toString());
         bst.put(8, 8);
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);
         assertEquals("Checking Median", "4", bst.median().toString());
     }
       
     @Test
     public void testPrintInOrder()
     {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Checking print", "()", bst.printKeysInOrder());
         bst.put(7, 7);
         assertEquals("Checking print", "(()7())", bst.printKeysInOrder());
         bst.put(8, 8);
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);
         assertEquals("Checking print", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
     }
     
     @Test
 	public void testGet()
 	{
 		BST<Integer, Integer> bst = new BST<Integer, Integer>();
 		
 		bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        
        assertEquals("4", bst.get(4).toString());
        //assertEquals("null", bst.get(55).toString());
 	}
}