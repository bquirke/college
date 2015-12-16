package highLow;

public class PlayingCard {
	public static final int HEARTS = 0;
    public static final int DIAMONDS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;
   
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;
   
    public static final int TYPES = 13;
    public static final int SUITS = 4;
   
    private static final int LOWEST = 2;
    private final int type; // invariant: 0 <= (type-LOWEST) < TYPES
    private final int suit; // invariant: 0 <= suit < SUITS
   
    // construct PlayingCard value with given type and suit
    // type (typeIndex) respectively: 2(0), 3(1)
    public PlayingCard(int typeIndex, int suitIndex)
    {
            type = (0 <= typeIndex && typeIndex < TYPES) ? typeIndex + LOWEST : LOWEST;
            suit = (0 <= suitIndex && suitIndex < SUITS) ? suitIndex: 0;
    }
   
    // is type of current card less than type of given card?
    public boolean lessThan(PlayingCard card)
    {
            return (this.type < card.type);
    }
   
    public boolean equal(PlayingCard card)
    {
            return (this.type == card.type);
    }
    public boolean higherThan(PlayingCard card)
    {
    	return (this.type > card.type);
    }
   
    // convert to a String data type value
    public String toString()
    {
            String cardType = (type < JACK) ? ("" + type) :
                    (type == JACK) ? "Jack" :
                            (type == QUEEN) ? "Queen" :
                                    (type == KING) ? "King" : "Ace";
            String cardSuit = (suit == HEARTS) ? "Hearts" :
                    (suit == DIAMONDS) ? "Diamonds" :
                            (suit == CLUBS) ? "Clubs" : "Spades";
            return cardType + " of " + cardSuit;
    }

}
