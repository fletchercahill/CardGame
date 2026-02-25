import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck (String[] ranks, String[] suits, int[] values) {
        // Every rank suit pair is unique card
        cards = new ArrayList<>();
        // Here is where the error is
        int count = 1;
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                Card new_card = new Card(ranks[i], suit, values[i], count);
                cards.add(new_card);
                count+=1;
            }
        }
        cardsLeft = cards.size();
    }
    // Functions required by problem set, however not utilized

    public Card deal(){
        if (cardsLeft == 0){
            return null;
        }
        // First decrement amount of cards left then return the card
        // More efficient than actually removing a card
        cardsLeft--;
        return cards.get(cardsLeft);
    }
    public void shuffle(){
        // Generates a random integer r (using Math.random) between 0 and i, inclusive;
        // Exchanges cards[i] and cards[r]
        cardsLeft = cards.size();
        for (int i = cardsLeft-1; i > 0; i--){
            int randomNumber = (int)(Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(randomNumber));
            cards.set(randomNumber, temp);
        }
    }
}