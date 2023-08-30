/**
 * defines a Deck interface, listing all the methods to be implemented
 */
public interface Deck {


    /**
     * returns the top card in the deck, but does not remove it
     * <p>
     * Required Time Performance: Theta(1)
     */
    Card peekTop();


    /**
     * creates a new, independent deck, with the cards in the same order as
     * this deck.
     *
     * @return a copy of this deck
     * <p>
     * Required Time Performance: Theta(N) where N is the number of cards in the deck.
     */
    Deck copy();

    /**
     * Returns the number of cards in the deck.
     *
     * @returns the number of cards in the deck
     * <p>
     * Required Time Performance: Theta(1)
     */
    int size();


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
    void outShuffle();

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
    void inShuffle();

}
