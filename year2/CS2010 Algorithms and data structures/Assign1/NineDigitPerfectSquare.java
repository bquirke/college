/**
 * CS2010 (Hilary Term) - Assignment 1
 * 
 * Nine Digit Perfect Square
 * 
 * A natural number, p, is a perfect square if for some natural number k, k^2=p.
 * For example, 16 is a perfect square, as 4^2=16. The number 20 is not a
 * perfect square as there is no natural number k such that k^2=20.
 * 
 * Problem: Develop an algorithm that will find all nine-digit perfect squares
 * that use all nine digits exactly once. For example, 139,854,276 is a solution
 * (the first) as 11,826^2=139,854,276.
 * 
 * 1) Fill in the implementation of the methods described in this file.
 * 
 * 2) Test your implementation by developing suitable test suite in the
 * NineDigitPerfectSquareTest JUnit test case.
 * 
 * @author:
 * 
 */
import java.util.Arrays;
import java.util.HashSet;

public class NineDigitPerfectSquare {
	
	private static final int[] PERFECT_SQUARES = new int[60];
	
	private static final int FIRST_NUM = 123456789; // smallest candidate for an
													// answer
	private static final int LAST_NUM = 987654321; // largest candidate for an
													// answer

	/**
	 * The nine digit perfect squares containing all 9 digits will never change.
	 * 
	 * It might thus be a good idea to perform the computation in the
	 * constructor, storing the result in a private class variable.
	 */
	public NineDigitPerfectSquare() {
		int x =0;
		for(int i = FIRST_NUM;i<= LAST_NUM;i+=9 ){
			
			if(containsAllDigitsOnce(i)){
				
				if(Math.sqrt((double) i)== Math.round(Math.sqrt((double)i))){
					PERFECT_SQUARES[x]= i;
					x++;
				}
			}
		}

		for(int i= 0; i<PERFECT_SQUARES.length; i++){
			System.out.print(PERFECT_SQUARES[i] + "\n");
		}
	}
	
	/**
	 * A method to determine if the number specified in parameter "number"
	 * contains all 9 digits exactly once.
	 * 
	 * @param number
	 *            : A number to be tested
	 * @return whether the number contains all 9 digits exactly once
	 */
	public boolean containsAllDigitsOnce(int number) {
		
		
		
		int digits[] = new int[10];
		for(int i =0; i!=digits.length;i++ ){
			digits[i] = i;
		}
		
		Integer [] myDigits = getDigits(number); 
		Arrays.sort(myDigits);
		
		int duplicate = (new HashSet<Integer>(Arrays.asList(myDigits)).size() - myDigits.length);// By doing this duplicates are rejected
		if(duplicate != 0){
			return false;
		}
		
		int success = 0;
		for(int i = 0; i!=myDigits.length;i++){
			int j =0;
			while(j!=digits.length){
				
				if (myDigits[i]==digits[j]){
					success++;
					break;
				}
				j++;
			}
		}
		
		if(success == myDigits.length){
			return true;
		}
		return false;
	}

	/**
	 * A method to return the number of perfect nine digit squares, which
	 * contain all digits 1..9 exactly once.
	 * 
	 * @return number of 9-digit perfect squares containing all numbers 1..9
	 */
	public int countNineDigitPerfectSquares() {
		
		return PERFECT_SQUARES.length;
	}

	/**
	 * A method to return an array containing all the squares discovered
	 * 
	 * @return an array containing all of the perfect squares which
	 * contain all digits 1..9 exactly once.
	 */
	public int[] getNineDigitPerfectSquares() {
		// TODO: Implement
		return PERFECT_SQUARES;
	}
	
	public Integer[] getDigits(int number){
		
		
		int size = String.valueOf(number).length();
		Integer myDigits[] = new Integer[size];
		int x = 0;

		while (number > 0) {
			myDigits[x] = number %10;
			x++;
		    number = number / 10;
		    
		}
		
		return myDigits;
	}
}