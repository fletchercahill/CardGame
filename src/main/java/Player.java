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
    // Constructor that also takes in a hand
    public Player(String name, ArrayList<Card> given_hand){
        this.name = name;
        this.hand.addAll(given_hand);
    }
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    // Methods to get, add, and subtract points
    // Method to add a card to hand
    public int getPoints() {
        return points;
    }
    public void addPoints(int new_points){
        this.points += new_points;
    }
    public void subtractPoints(int new_points){
        this.points+=new_points;
    }
    public void addCard(Card new_card){
        hand.add(new_card);
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
