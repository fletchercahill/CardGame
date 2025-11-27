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

    private static boolean hasPair(ArrayList<Card> cards) {
        int[] count = new int[15];
        for (Card card: cards){
            count[card.getValue()]++;

        }
        for (int i = 2; i <= 14; i++){
            if (count[i] == 2){
                return true;
            }
        }
        return false;
    }

    private static boolean hasTwoPair(ArrayList<Card> cards) {
        int[] count = new int[15];
        for (Card card: cards){
            count[card.getValue()]++;

        }
        int num_pairs = 0;
        for (int i = 2; i <= 14; i++){
            if (count[i] == 2){
                num_pairs++;
            }
        }
        return num_pairs >= 2;
    }

    private static boolean hasThreeOfAKind(ArrayList<Card> cards) {
        int[] count = new int[15];
        for (Card card: cards){
            count[card.getValue()]++;

        }
        for (int i = 2; i <= 14; i++){
            if (count[i] == 3){
                return true;
            }
        }
        return false;
    }

    private static boolean hasStraight(ArrayList<Card> cards) {
        // Here we created an array of booleans as no need to know frequency of cards
        // Finish this up
        boolean[] count = new boolean[15];
        for (Card card: cards){

        }

        if (count[2] && count[3] && count[4] && count[5] && count[14]){
            return true;
        }
    }

    private static boolean hasFlush(ArrayList<Card> cards) {
        int diamonds = 0, hearts = 0, spades = 0, clubs = 0;
        for (Card card : cards){
            String current_suit = card.getSuit();
            switch (current_suit) {
                case "Hearts" -> hearts++;
                case "Diamonds" -> diamonds++;
                case "Spades" -> spades++;
                default -> clubs++;
            }
        }
        return diamonds > 4 || hearts > 4 || spades > 4 || clubs > 4;


    }

    private static boolean hasFullHouse(ArrayList<Card> cards) {
        int[] count = new int[15];
        for (Card card: cards){
            count[card.getValue()]++;

        }
        boolean hasThree = false;
        boolean hasPair = false;
        for (int i = 2; i <= 14; i++){
            if (count[i] == 3){
                hasThree = true;
                break;
            }
        }
        for (int i = 2; i<=14; i++){
            if (count[i] == 2){
                hasPair = true;
                break;
            }
        }
        return hasThree && hasPair;
    }

    private static boolean hasFourOfAKind(ArrayList<Card> cards) {
        int[] count = new int[15];
        // Ranks go up to 14
        for (Card card : cards){
            count[card.getValue()]++;
        }
        for (int c : count){
            if (c >= 4){
                return true;
            }
        }
        return false;
    }

    private static boolean hasStraightFlush(ArrayList<Card> cards) {
        // Very similar to royal flush, however different in straight logiic
        // First check for flush
        int diamonds = 0, hearts = 0, spades = 0, clubs = 0;
        for (Card card : cards){
            String current_suit = card.getSuit();
            switch (current_suit) {
                case "Hearts" -> hearts++;
                case "Diamonds" -> diamonds++;
                case "Spades" -> spades++;
                default -> clubs++;
            }
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

    }
    // Can definitely make more efficient 
    private static boolean hasRoyalFlush(ArrayList<Card> cards) {
        // All cards are same suit
        // Ranks are 10, J, Q, K, A
        // First check if flush

        int diamonds = 0, hearts = 0, spades = 0, clubs = 0;
        for (Card card : cards){
            String current_suit = card.getSuit();
            switch (current_suit) {
                case "Hearts" -> hearts++;
                case "Diamonds" -> diamonds++;
                case "Spades" -> spades++;
                default -> clubs++;
            }
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
        // Secondly check if has high strqight
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
