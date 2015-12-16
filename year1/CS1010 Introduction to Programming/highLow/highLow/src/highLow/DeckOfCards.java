package highLow;
import java.util.Random;


public class DeckOfCards {
	private PlayingCard[] deck;
    private int cardsRemaining;
   
    public DeckOfCards()
    {
            cardsRemaining = PlayingCard.SUITS * PlayingCard.TYPES;
            deck = new PlayingCard[cardsRemaining];
            for (int suitIndex = 0; (suitIndex < PlayingCard.SUITS); suitIndex++)
            {
                    for (int typeIndex = 0; (typeIndex < PlayingCard.TYPES); typeIndex++)
                    {
                            deck[suitIndex * PlayingCard.TYPES + typeIndex] = new PlayingCard(typeIndex, suitIndex);
                    }
            }
    }
   
    public PlayingCard dealCard()
    {
    	shuffle();
    	PlayingCard dealt = deck[cardsRemaining -1];
    	cardsRemaining--;
    	
    	return dealt;

    }
   
    public boolean cardsRemainingFunc()
    {
        if (cardsRemaining > 0){
        	return true;
        }
        
        return false;
    	
    }
   
    public void shuffle()
    {
    	Random rand = new Random();

        for (int i = cardsRemaining - 1; i > 0; i--)
        {
        	int x = rand.nextInt(i+1);
        	PlayingCard a = deck[x];
        	deck[x] = deck[i];
        	deck[i] = a;
        	
        }
          
    }

}
