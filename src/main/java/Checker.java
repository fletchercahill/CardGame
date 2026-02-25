import java.util.ArrayList;

public class Checker {
    // Takes in both the hand that is being checked,
    // and the table hand
    public static int check(ArrayList<Card> hand, ArrayList<Card> table){
        // Because in Texas Holdem your hand and the communal hand are shared to form hands
        ArrayList<Card> total = new ArrayList<Card>();
        total.addAll(hand);
        total.addAll(table);

        // Checks from most valuable to least valuable hand
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
        // If High Card returns 1
        // If both player and cpu only have high card, there's a function in game to compare
        return 1;



    }
    // This only takes the users hand not including the table cards
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
    // Checks for two pairs
    private static boolean hasTwoPair(ArrayList<Card> cards) {
        int[] count = new int[15];
        // Adds the occurences of a value to an array
        for (Card card: cards){
            count[card.getValue()]++;

        }
        // Checks the array for number of pairs
        int numPairs = 0;
        for (int i = 2; i <= 14; i++){
            if (count[i] == 2){
                numPairs++;
            }
        }
        return numPairs >= 2;
    }

    private static boolean hasThreeOfAKind(ArrayList<Card> cards) {
        // Adds occurences to array
        int[] count = new int[15];
        for (Card card: cards){
            count[card.getValue()]++;
        }
        // Checks to see if any value has three occurences
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
            count[card.getValue()] = true;
        }
        // No rank less than 2
        for (int i = 2; i < 11; i++){
            if (count[i]){
                if (count[i+1] && count[i+2] && count[i+3] && count[i+4]){
                    return true;
                }
            }
        }
        // Ace low straight
        if (count[2] && count[3] && count[4] && count[5] && count[14]){
            return true;
        }
        return false;
    }

    private static boolean hasFlush(ArrayList<Card> cards) {
        // Checks occurences of each suit, if occurences for any greater than 4 then flush
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
        // Checks for three of a kind
        for (int i = 2; i <= 14; i++){
            if (count[i] == 3){
                hasThree = true;
                break;
            }
        }
        // Checks for pair
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
        // Same logic as pair and three of a kind checker functions
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
        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();
        ArrayList<Card> clubs = new ArrayList<>();
        // Adding each card to a list of only the same suit
        for (Card card : cards){
            String current_suit = card.getSuit();
            switch (current_suit) {
                case "Hearts" -> hearts.add(card);
                case "Diamonds" -> diamonds.add(card);
                case "Spades" -> spades.add(card);
                case "Clubs" -> clubs.add(card);
            }
        }
        // Must have a straight between the cards of same suit as well
        if (hearts.size() >= 5 && hasStraight(hearts)) return true;
        if (diamonds.size() >= 5 && hasStraight(diamonds)) return true;
        if (clubs.size() >= 5 && hasStraight(clubs)) return true;
        if (spades.size() >= 5 && hasStraight(spades)) return true;
        return false;
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
        // If a high straight of all same suit then royal flush
        return hasTen && hasJack && hasQueen && hasKing && hasAce;

    }
}
