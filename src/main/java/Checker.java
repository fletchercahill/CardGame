import java.util.ArrayList;

public class Checker {
    // Takes in both the hand that is being checked,
    // and the table hand
    public static int check(ArrayList<Card> hand, ArrayList<Card> table){
        ArrayList<Card> total = new ArrayList<Card>();
        total.addAll(hand);
        total.addAll(table);
        if (hasRoyalFlush(total)){
            return 10;
        }
        if (hasStraightFlush(total)){
            return 9; 
        }
        if (hasFourOfAKind(total)){
            return 8;
        }
        if (hasFullHouse(total)){
            return 7;
        }
        if (hasFlush(total)){
            return 6;
        }
        if (hasStraight(total)){
            return 5;
        }
        if (hasThreeOfAKind(total)){
            return 4;
        }
        if (hasTwoPair(total)){
            return 3;
        }
        if (hasPair(total)){
            return 2;
        }
        // public static int highCard - work on this last


    }

    private static boolean hasTwoPair(ArrayList<Card> cards) {
    }

    private static boolean hasThreeOfAKind(ArrayList<Card> cards) {
    }

    private static boolean hasStraight(ArrayList<Card> cards) {
    }

    private static boolean hasFlush(ArrayList<Card> cards) {
    }

    private static boolean hasFullHouse(ArrayList<Card> cards) {
    }

    private static boolean hasFourOfAKind(ArrayList<Card> cards) {
    }

    private static boolean hasStraightFlush(ArrayList<Card> cards) {
    }
    // Can definitely make more efficient 
    private static boolean hasRoyalFlush(ArrayList<Card> cards) {
        // All cards are same suit
        // Ranks are 10, J, Q, K, A
        // First check if there are at least a 10, J, Q, K, A

        int diamonds = 0, hearts = 0, spades = 0, clubs = 0;
        for (Card card : cards){
            String current_suit = card.getSuit();
            if (current_suit.equals("Hearts")) hearts++;
            else if (current_suit.equals("Diamonds")) diamonds++;
            else if (current_suit.equals("Spades")) spades++;
            else clubs++;
        }

        String target = "Diamonds";
        int maxSuits = diamonds;
        if (hearts > maxSuits){
            target = "Hearts";
            maxSuits = hearts;
        }
        if (spades > maxSuits){
            target = "Spades";
            maxSuits = spades;
        }
        if (clubs > maxSuits){
            target = "Clubs";
            maxSuits = clubs;
        }
        // If not a flush rule it out
        if (maxSuits < 5){
            return false;
        }
        boolean hasTen = false, hasJack = false, hasQueen = false, hasKing = false, hasAce = false;
        for (Card card: cards){
            if (card.getSuit().equals(target)){
                if (card.getValue() == 10) hasTen = true;
                else if (card.getValue() == 11) hasJack = true;
                else if (card.getValue() == 12) hasQueen = true;
                else if (card.getValue() == 13) hasKing = true;
                else if (card.getValue() == 14) hasAce = true;
            }
        }
        return hasTen && hasJack && hasQueen && hasKing && hasAce;

    }
}
