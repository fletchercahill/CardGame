import java.util.ArrayList;

public class Player{
    // Each player has a name, hand, and points
    private String name;
    private ArrayList<Card> hand;
    private int points;
    public Player (String name){
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    // Methods to get, add, and subtract points
    // Method to add a card to hand
    public void addCard(Card newCard){
        hand.add(newCard);
    }
    @Override
    public String toString() {
        return this.name +
                " has " +
                this.points + " points "
                + "\n" + this.name + "'s cards: " +
                this.hand;
    }

}
