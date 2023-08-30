import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Shuffling {

    /**
     * For a deck of maxRank starting in the factory order,
     * find the minimum number of out shuffles needed to get the deck
     * back to its original order.
     *
     * @param maxRank the max rank of the cards in the deck
     * @return the smallest number of out-shuffles needed to get the deck
     * back to its original order
     */

    public static int minOutShuffles(int maxRank) {
        int min_shuffles = 1;
        ArrayDeck deck = new ArrayDeck(maxRank);
        ArrayDeck shuffled = new ArrayDeck(maxRank);
        shuffled.outShuffle();
        System.out.println("Min shuffles: " + min_shuffles);
        System.out.println("deck: " + deck);
        System.out.println("shuffled: " + shuffled);
        while (!deck.toString().equals(shuffled.toString())) {
            deck.outShuffle();
            min_shuffles++;
            System.out.println("Min shuffles: " + min_shuffles);
            System.out.println("deck: " + deck);
            System.out.println("shuffled: " + shuffled);
        }
        return min_shuffles;
    }

    /**
     * For a deck of maxRank starting in the factory order,
     * find the minimum number of in shuffles needed to get the deck
     * back to its original order.
     *
     * @param maxRank the max rank of the cards in the deck
     * @return the smallest number of in-shuffles needed to get the deck
     * to its original order
     */
    public static int minInShuffles(int maxRank) {
        int min_shuffles = 1;
        ArrayDeck deck = new ArrayDeck(maxRank);
        ArrayDeck shuffled = new ArrayDeck(maxRank);
        shuffled.inShuffle();
        while (!deck.toString().equals(shuffled.toString())) {
            deck.inShuffle();
            min_shuffles++;
        }
        return min_shuffles;
    }

    /**
     * computes and returns a queue of decks, starting with the specified
     * deck and going through the shuffling sequence
     *
     * @param deck            the start deck
     * @param shuffleSequence a String representing a shuffling sequence, with
     *                        'I' representing in-shuffle and 'O' representing
     *                        out-shuffle
     * @return a queue of decks starting with the given deck and going through
     * the shuffling sequence, including each intermediate deck
     */
    public static Queue<Deck> computeDeckSequence(Deck deck, String shuffleSequence) {
        Queue<Deck> queue = new LinkedList<>();
        String[] split = shuffleSequence.split("", 1);
        for (String i : split) {
            if (i.equals("O")) {
                deck.outShuffle();
                queue.add(deck.copy());
            }
            else if (i.equals("I")) {
                deck.inShuffle();
                queue.add(deck.copy());
            }
        }
        return queue;
    }


    private static class DeckShufflePair {
        private final Deck deck;
        private final String shuffle;

        public DeckShufflePair(Deck deck, String shuffle) {
            this.deck = deck;
            this.shuffle = shuffle;
        }
    }

    /**
     * For a deck of maxRank starting in the factory order,
     * Find the shortest shuffle sequence that brings each card to the top.
     *
     * @param maxRank the max rank of the cards in the deck
     * @return a hashmap, with Card as the Key and String as the value,
     * showing the shortet shuffle sequence to bring each card to the top
     */

    public static HashMap<Card, String> findShortestShuffles(int maxRank) {
        //declare and create a hashmap, to save card - shortest shuffle sequence
        HashMap<Card, String> shortestShuffles = new HashMap<Card, String>();
        ArrayDeck deck = new ArrayDeck(maxRank);
        Queue<DeckShufflePair> queue = new LinkedList<>();
        DeckShufflePair d = new DeckShufflePair(deck, "");
        queue.add(d);
        Card card = deck.peekTop();
        shortestShuffles.put(card, "");
        while (shortestShuffles.size() < deck.size()) {
            //checks top and sees if it is already in the hashmap
            DeckShufflePair current = queue.remove();
            if (!shortestShuffles.containsKey(current.deck.peekTop())) {
                shortestShuffles.put(current.deck.peekTop(), current.shuffle);
            }
            Deck indeck;
            indeck = current.deck.copy();
            indeck.inShuffle();
            Deck outdeck = current.deck.copy();
            outdeck.outShuffle();
            DeckShufflePair in = new DeckShufflePair(indeck, current.shuffle + "I");
            DeckShufflePair out = new DeckShufflePair(outdeck, current.shuffle + "O");
            queue.add(in);
            queue.add(out);
        }
        return shortestShuffles;
    }

    /*
     * Print the states of a deck as the specified shuffle sequence is
     * performed on it.
     *
     * @param maxRank         the initial deck state is a new deck with the
     *                        specified rank
     * @param shuffleSequence a String representing a shuffling sequence, with
     *                        'I' representing in-shuffle and 'O' representing
     *                        out-shuffle
     */
    private static void printSequence(int maxRank, String shuffleSequence) {
        String FORMAT_STRING = "  maxRank=%s, shuffleSequence=\"%s\"\n";
        Shuffling shuffling = new Shuffling();
        System.out.printf(FORMAT_STRING, maxRank, shuffleSequence);
        for (Deck deck : shuffling.computeDeckSequence(new ArrayDeck(maxRank),
                                                       shuffleSequence)) {
            System.out.println("    " + deck);
        }
    }

    /*
     * Print the shortest combination of shuffles needed to bring each card to
     * the top of a factor order deck
     *
     * @param maxRank the factory order deck has the specified rank
     */
    private static void printShortestShuffles(int maxRank) {
        Shuffling shuffling = new Shuffling();
        System.out.println("  maxRank=" + maxRank);
        String FORMAT_STRING = "    %-5s %s\n";
        System.out.printf(FORMAT_STRING, "Card", "Shuffle Sequence");
        HashMap<Card, String> shortestShuffles =
                shuffling.findShortestShuffles(maxRank);
        for (Card c : shortestShuffles.keySet()) {
            System.out.printf(FORMAT_STRING, c, shortestShuffles.get(c));
        }
    }

    public static void main(String[] args) {
        int highestRank = Integer.parseInt(args[0]);

        System.out.println("Minimum Numbers of Shuffles");
        String FORMAT_STRING = "  %-10s %-17s %-17s\n";
        System.out.printf(FORMAT_STRING,
                          "maxRank",
                          "Min Out Shuffles",
                          "Min In Shuffles");
        for (int i = 1; i <= highestRank; i++) {
            System.out.printf(FORMAT_STRING,
                              i,
                              minOutShuffles(i),
                              minInShuffles(i));
        }

        System.out.println("\nShuffle Sequences");
        printSequence(1, "OO");
        printSequence(2, "IIIIII");
        printSequence(1, "OIOI");

        System.out.println("\nShortest Shuffles");
        for (int i = 1; i <= highestRank; i++) {
            printShortestShuffles(i);
        }
    }
}
