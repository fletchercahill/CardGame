import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;
    public Deck (String[] ranks, String[] suits, int[] values) {
        // Every rank suit pair is unique card
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                Card new_card = new Card(ranks[i], suit, values[i]);
                // Do I want to add a card at a random location?
                cards.add(new_card);
            }
        }
        cardsLeft = cards.size();
    }
    public boolean isEmpty(){
        return cardsLeft == 0;
    }
    public int getCardsLeft(){
        return cardsLeft;
    }
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
        /*For i = last index of the deck down to 0
        Generate a random integer r (using Math.random) between 0
        and i, inclusive;
        Exchange cards[i] and cards[r]
         */;
        cardsLeft = 52;
        for (int i = cardsLeft-1; i > 0; i--){
            // Review this to make sure it's correct
            int randomNumber = (int)(Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(randomNumber));
            cards.set(randomNumber, temp);
        }
    }
}