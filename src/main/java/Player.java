import java.util.ArrayList;

public class Player{
    // Method to check for hand
    // Check for royal flush = 10 points
    // Check for straight flush = 9 points
    // Check for 4 of a kind = 8 points
    // Check for full house = 7 points
    // Check for flush = 6 points
    // Check for straight = 5 points
    // Check for three of a kind = 4 points
    // Check for two pair = 3 points
    // Check for pair = 2 points
    // Check for high card = 1 point - potentially need to compare high cards?
    //private ArrayList<Card>;
    private String name;
    private ArrayList<Card> hand;
    private int points;
    public Player (String name){
        this.name = name;
        this.points = 0;
    }
    // Constructor that also takes in a hand
    public Player(String name, ArrayList<Card> given_hand){
        this.name = name;
        for (int i = 0; i < given_hand.size(); i++){
            this.hand.add(i, given_hand.get(i));

        }
    }
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

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
