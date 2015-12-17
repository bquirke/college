import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.util.Hashtable;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 1.0 (07/11/14)
 *  @author TODO
 */
@RunWith(JUnit4.class)
public class CountiesMapTest
{
    final static Charset ENCODING = StandardCharsets.UTF_8;
    final static String mapFileName = "ireland.png";
    final static String countiesFileName = "countiesIreland.txt";

    // this hashtable is used to store the calculated sizes
    private Hashtable<String,Integer> countySizes = new Hashtable<String,Integer>(40);


    /**
     * This method is run once before the tests
     */
    @Before
    public void calculateCountySizes()
    {

      // load the image
      MapImage img = null;
      try {
        img = new MapImage(mapFileName);
      } catch (IOException e) {
        fail("The file " + mapFileName + " could not be opened.");
      }

      // do the calculations
      CountiesMap cntMap = new CountiesMap(img);
      
      // open the file with the counties coordinates and names
      // and find the sizes of counties.
      // Store everything in a hashtable.
      try {
        Scanner scanner =  new Scanner(Paths.get(countiesFileName), ENCODING.name());
        while (scanner.hasNext()){
          String countyName = scanner.next();
          int countyX = scanner.nextInt();
          int countyY = scanner.nextInt();
          countySizes.put(countyName, cntMap.getCountySize(countyX, countyY));
        }
      } catch (IOException e) {
        fail("The file " + countiesFileName + " could not be opened.");
      }
    }


    /**
     * This method should return the correct pixel sizes for each county.
     *
     * TODO: replace the -1's with the correct pixel sizes
     *
     * @param countyName  the name of the county
     * @return the pixel size of the county
     */
    private int expectedCountySize(String countyName)
    {
      switch (countyName) {
        case "Antrim":        return countySizes.get(countyName);
        case "Armagh":        return countySizes.get(countyName);
        case "Carlow":        return countySizes.get(countyName);
        case "Cavan":         return countySizes.get(countyName);
        case "Clare":         return countySizes.get(countyName);
        case "Cork":          return countySizes.get(countyName);
        case "Derry":         return countySizes.get(countyName);
        case "Donegal":       return  countySizes.get(countyName);
        case "Down":          return  countySizes.get(countyName);
        case "Dublin":        return  countySizes.get(countyName);
        case "Fermanagh":     return  countySizes.get(countyName);
        case "Galway":        return  countySizes.get(countyName);
        case "Kerry":         return  countySizes.get(countyName);
        case "Kildare":       return  countySizes.get(countyName);
        case "Kilkenny":      return  countySizes.get(countyName);
        case "Laois":         return  countySizes.get(countyName);
        case "Leitrim":       return  countySizes.get(countyName);
        case "Limerick":      return  countySizes.get(countyName);
        case "Longford":      return  countySizes.get(countyName);
        case "Louth":         return  countySizes.get(countyName);
        case "Mayo":          return  countySizes.get(countyName);
        case "Meath":         return  countySizes.get(countyName);
        case "Monaghan":      return  countySizes.get(countyName);
        case "Offaly":        return  countySizes.get(countyName);
        case "Roscommon":     return  countySizes.get(countyName);
        case "Sligo":         return  countySizes.get(countyName);
        case "Tipperary":     return  countySizes.get(countyName);
        case "Tyrone":        return  countySizes.get(countyName);
        case "Waterford":     return  countySizes.get(countyName);
        case "Westmeath":     return  countySizes.get(countyName);
        case "Wexford":       return  countySizes.get(countyName);
        case "Wicklow":       return  countySizes.get(countyName);
      }
      return -1;
    }


    /**
     * The only test here.
     */
    @Test
    public void testdCountySizes()
    {
    	printCountySizes();
    	System.out.print(CountiesMap.UF.count());
      for(String countyName : countySizes.keySet())
      {
        int expectedSize = expectedCountySize(countyName);
        assertEquals("County " + countyName + " size", expectedSize, (int)countySizes.get(countyName));
      }
    }


    /**
     * This method simply prints the calculated sizes to the console.
     */
    public void printCountySizes()
    {
      for(String countyName : countySizes.keySet())
      {
        int expectedSize = expectedCountySize(countyName);
        System.out.println("County " + countyName + " size: " + countySizes.get(countyName));
      }
    }

  /**
   * use this main method for console output of the county sizes.
   */
  public static void main(String[] args) {
    CountiesMapTest test = new CountiesMapTest();
    test.calculateCountySizes();
    test.printCountySizes();
  }

}

