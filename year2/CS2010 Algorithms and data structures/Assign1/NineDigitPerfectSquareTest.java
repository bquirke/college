import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for NineDigitPerfectSquare
 * 
 * @author 
 *
 *	TODO: Implement tests to cover the functionality provided by your methods.
 *
 */

public class NineDigitPerfectSquareTest {

	/*@Test
	public void testConstructor() {
		NineDigitPerfectSquare tmp = new NineDigitPerfectSquare();
		assertNotNull("Testing the constructor of NineDigitPerfectSquare", tmp);
	}*/

	/**
	 * Test method for the containsAllDigitsOnce method
	 */
	@Test
	public void testContainsAllDigitsOnce() {
		
		NineDigitPerfectSquare ndps = new NineDigitPerfectSquare();
		int[] array = new int[ndps.countNineDigitPerfectSquares()];
		array = ndps.getNineDigitPerfectSquares();
		assertEquals("Testing containsAllDigitsOnce with 123456789, expected true", true, ndps.containsAllDigitsOnce(123456789) );
		assertEquals("Testing containsAllDigitsOnce with 111111111, expected false", false, ndps.containsAllDigitsOnce(111111111) );
		assertEquals("Testing containsAllDigitsOnce with 176305284, expected true", true, ndps.containsAllDigitsOnce(array[5]) );
		assertEquals("Testing containsAllDigitsOnce with 152843769,  expected true", true, ndps.containsAllDigitsOnce(array[2]) );
	}
	
}