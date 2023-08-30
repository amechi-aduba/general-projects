import java.util.Iterator;

/**
 * Provides an array based implementation of the Deck interface
 */
public class ArrayDeck implements Deck, Iterable<Card> {

    private final int MAX_SUIT = 4;
    private final char[] SUITS = { 'C', 'D', 'H', 'S' };
    private final int MAX_RANK = 13;

    private Card[] deck;
    private int max_rank;

    /**
     * Ensure that no one OUTSIDE of this class and its subclasses invokes the
     * no-argument constructor. Don't make any change to this method.
     */
    protected ArrayDeck() {

    }


    /**
     * Construct a deck includes all four-suit cards up to the specified {maxRank}
     * in the factory order.
     * <p>
     * Note that the suites are ordered Club < Diamond < Hearts < Spades.
     * <p>
     * At the end of this method, your array of cards representing a deck should
     * look like the following (if maxRank is 3).
     * <p>
     * AC 2C 3C AD 2D 3D AH 2D 3H AS 2S 3S
     * <p>
     * Note your deck will have 4 * maxRank cards.
     * <p>
     * Required Time Performance: Theta(maxRank)
     *
     * @param maxRank the maximum rank of the cards in the deck, must be >=1
     * @throws IllegalArgumentException if maxRank is less than 1 or greater
     *                                  than 13
     */
    public ArrayDeck(int maxRank) {
        max_rank = maxRank;
        deck = new Card[SUITS.length * maxRank];
        // TODO Complete for part 1
        if (maxRank < 1 || maxRank > 13) {
            throw new IllegalArgumentException();
        }
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= maxRank; j++) {
                deck[k] = new Card(j, SUITS[i]);
                k++;
            }
        }
    }

    /**
     * returns the top card in the deck, but does not remove it
     * <p>
     * Required Time Performance: Theta(1)
     */
    public Card peekTop() {
        // TODO Complete for part 1
        return deck[0];
    }

    /**
     * creates a new, independent deck, with the cards in the same order as
     * this deck.
     *
     * @return a copy of this deck
     * <p>
     * Required Time Performance: Theta(N) where N is the number of cards in the deck.
     */
    public Deck copy() {
        // TODO Complete for part 1
        Card[] array = new Card[deck.length];
        for (int i = 0; i < deck.length; i++) {
            array[i] = deck[i];
        }
        ArrayDeck copy = new ArrayDeck(deck.length / 4);
        copy.deck = array;
        return copy;
    }

    /**
     * Returns the number of cards in the deck.
     *
     * @returns the number of cards in the deck
     * <p>
     * Required Time Performance: Theta(1)
     */
    public int size() {
        // TODO Complete for part 1
        return deck.length;
    }

    /**
     * Does a faro out-shuffle that splits a deck in half, interleaves the cards
     * of the two halves, and leaves the original top card at the top and the
     * original bottom card at the bottom.
     * <p>
     * Refer to the problem description and the following wikipedia page for
     * more information: https://en.wikipedia.org/wiki/Faro_shuffle
     * <p>
     * For a deck with the following 12 cards
     * <p>
     * AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S
     * <p>
     * An out-shuffle would conceptually look like this:
     * <p>
     * 1st half:    AC    2C    3C    AD    2D    3D
     * 2nd half:       AH    2H    3H    AS    2S    3S
     * <p>
     * RESULT:      AC AH 2C 2H 3C 3H AD AS 2D 2S 3D 3S
     * <p>
     * Required Time Performance: Theta(N) where N is the number of cards in the deck.
     */
    public void outShuffle() {
        // TODO Complete for part 1
        Card[] half1 = new Card[deck.length / 2];
        Card[] half2 = new Card[deck.length / 2];
        int counter = 0;
        for (int i = 0; i < half1.length; i++) {
            half1[i] = deck[i];
            half2[i] = deck[i + (deck.length / 2)];
        }
        for (int i = 0; i < deck.length; i += 2) {
            deck[i] = half1[counter];
            deck[i + 1] = half2[counter];
            counter++;
        }
    }


    /**
     * Does a faro in-shuffle that split a deck in half, interleaves the cards
     * of the two halves, and moves the original top card to second and the
     * original bottom card to second from the bottom.
     * <p>
     * Refer to the problem description and the following wikipedia page for
     * more information: https://en.wikipedia.org/wiki/Faro_shuffle
     * <p>
     * For a deck with the following 12 cards
     * <p>
     * AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S
     * <p>
     * An in-shuffle would conceptually look like this:
     * <p>
     * 1st half:    AC    2C    3C    AD    2D    3D
     * 2nd half: AH    2H    3H    AS    2S    3S
     * <p>
     * RESULT:   AH AC @H 2C 3H 3C AS AD 2S 2D 3S 3D
     * <p>
     * Required Time Performance: Theta(N) where N is the number of cards in the deck.
     */
    public void inShuffle() {
        // TODO Complete for part 1
        Card[] half1 = new Card[deck.length / 2];
        Card[] half2 = new Card[deck.length / 2];
        int counter = 0;
        for (int i = 0; i < half1.length; i++) {
            half1[i] = deck[i];
            half2[i] = deck[i + (deck.length / 2)];
        }
        for (int i = 0; i < deck.length; i += 2) {
            deck[i] = half2[counter];
            deck[i + 1] = half1[counter];
            counter++;
        }
    }

    /**
     * creates a String representation of the deck.
     * <p>
     * For a deck with maxRank 3, the String representation is:
     * AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S
     * with no space before the first card, and no space after the last card.
     * <p>
     * Required Time Performance: Theta(N) where N is the number of cards in the deck.
     *
     * @return a String representation of the deck
     */
    public String toString() {
        // TODO Complete for part 1
        String toString = "";
        for (int i = 0; i < deck.length; i++) {
            if (i != deck.length - 1) {
                toString += deck[i].toString() + " ";
            }
            else {
                toString += deck[i].toString();
            }

        }
        return toString;
    }

    /**
     * determines if this deck is equal to that object. Part 2
     * *
     * Required Time Performance: Theta(N) where N is the number of cards in the deck.
     *
     * @param that an object to be checked for equivalence with this deck
     * @return true if that object is a deck that is equivalent to this deck,
     * false otherwise
     */
    @Override
    public boolean equals(Object that) {
        // TODO Complete for part 2
        if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass())
            return false;
        ArrayDeck x = (ArrayDeck) that;
        for (int i = 0; i < deck.length; i++) {
            if (this.deck[i] != (x.deck[i])) return false;
        }
        return true;
    }

    /**
     * Returns an iterator to this deck that iterates through the cards from
     * the top to the bottom.
     * <p>
     * The required complexity for your Iterator class's hasNext() and next()
     * is Theta(1).
     *
     * @return an iterator to this deck that iterates through the cards from
     * the top to the bottom..
     */

    public Iterator<Card> iterator() {
        // TODO Complete for part 2
        return new Iterator<Card>() {
            private int count = 0;

            public boolean hasNext() {
                if (size() > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

            public Card next() {
                if (hasNext()) {
                    return deck[count--];
                }
                else {
                    throw new IllegalArgumentException("");
                }
            }
        };
    }

    public static void main(String[] args) {
        // TODO Test your code
        ArrayDeck cards = new ArrayDeck(2);
        System.out.println(cards.toString());
        Deck deck = cards.copy();
        deck.outShuffle();
        System.out.println(deck.toString());
    }
}
