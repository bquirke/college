
/*****************************************************************************
 * CountiesMap
 *
 * @version 1.0
 * @author Bryan Quirke
 *
 * The constructor of this class loads a MapImage object.
 * The constructor then computes the regions with the same colour on the map.
 * We call these regions <b>counties</b>.
 * 
 * Once the counties have been computed, their size can be obtained by calling
 * the method <code>getCountySize</code>.
 *
 * For this assignment i chose to use the Weighted Quick Union Find data structure.
 * I choose this as it allowed me to to union pixels of the same color relevant to the 
 * current pixel.
 * This was helpful as i was able to distinguish between 2 same colored counties. As the pixels of one county
 * always linked back to the same root.
 * From here i could access the size of the component.
 * 
 * N + M(log)N = amortized time for WQUPC.
 * 
 * Memory usage = N
 * 
 * This implementation is based on the data-structure/algorithm: Weighted Quick Union Find (based on princeton implementation)
 *
 *****************************************************************************/
public class CountiesMap
{
  private final MapImage map;
  
  private final static int height = 2810;
  private final static int width = 3600;
  
  public static WeightedQuickUnionUF UF = new WeightedQuickUnionUF(height*width);

  

  /**
   * The constructor does all the map calculations.
   * The parameter of the class contains a map of counties of a country.
   * There is no text on the map. It has only single-colour regions.
   * Some of these single-colour regions represent counties (you don't know which ones).
   * There might be other regions on the map such as lakes, oceans, islands etc.
   *
   * @param map this is a MapImage object
   */
  public CountiesMap(MapImage map)
  {
    this.map = map;
    int x;
    int y;
    
    for(y = map.getMinY(); y < map.getHeight() -1; y++){
    	for(x = map.getMinX(); x < map.getWidth() - 1; x++ ){
    		
    		
    		int pixelColour = map.getRGB(x, y);
    		int pixelIndex = this.getIndex(x, y);
    		
    		//looking Right
    		if(x < (map.getWidth() -1)){
    			if(pixelColour == map.getRGB(x + 1, y)){
        			UF.union(pixelIndex, this.getIndex(x +1,y));
        			
        		}
    			
    		}
    		
    		//Looking left
    		/* if(x > 0){
    			if(pixelColour == map.getRGB(--x, y)){
    				UF.union(pixelIndex, this.getIndex(--x, y));
    				System.out.println("left");
    			}
    		}
    		
    		//Looking up
    		 if( y > 0){
    			if(pixelColour == map.getRGB(x, --y)){
    				UF.union(pixelIndex, this.getIndex(x, --y));
    				System.out.println("up");
        		}
    		}*/
    		
    		//Looking down
    		 if(y < map.getHeight() - 1){
    			if(pixelColour == map.getRGB(x, y +1)){
    				UF.union(pixelIndex, this.getIndex(x, y + 1));
    				//System.out.println("down" + x + " "+ y);
    			}
    		}
    		
    		
    	}
    } 
    
  }

  /**
   * This method returns the size in pixels of the region which includes the point (x,y).
   *
   * @param x the x-coordinate of the point in the region.
   * @param y the y-coordinate of the point in the region.
   * @return the size of the region in pixels.
   */
  public int getCountySize(int x, int y)
  {
	  //int index = getIndex(x, y);
	  //int size = UF.getSize(x, y); 
    return UF.getSize()[UF.find(getIndex(x, y))];
  } 

  /**
   * This method can be used to convert the map's (x,y) coordinates to a unique linear index.
   * Suppose we want to store all pixels of the map in a one-dimentional array.
   * Then the array will have to have size (map.getHeight() * map.getWidth()).
   * Pixel (x,y) will be at position getIndex(x,y) in the array.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the index in a 1-dimentional array corresponding to pixel (x,y).
   */
  private int getIndex(int x, int y)
  {
    return y * map.getWidth()  +  x;
  }

}
