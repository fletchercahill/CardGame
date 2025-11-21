public class Game {

    // Instance variables
    public Game(){
        final String[] ranks = {"2", "3", "4", "5", "6", "7", "8",
                          "9", "10", "J", "Q", "K", "A"};
        final String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Deck deck = new Deck(ranks, suits, values);
        Player p1 = new Player("Challenger");
        Player cpu = new Player("CPU");
        deck.shuffle();
        System.out.println(deck.deal());
    }
    public void playGame(){
        printInstructions();
        while(true){
            System.out.println("Your hand: ");
            break;
        }

    }
    public static void printInstructions(){
        System.out.println("WELCOME TO POKER!");
        System.out.println("Here's how it's gonna work: ");
        System.out.println("You will start with $100 and can bet however much" +
                " you'd like before the flop");
        System.out.println("If you win you get that money from the CPU");

    }

    public static void main(String[] args) {
        /* I have a couple of ideas for the CPU + Logic
        1. The cpu gets a random hand with a high card, ex .01% or whatever for a royal flush etc
        2. The cpu gets an actual hand, and is similarily evaluated by
        Game flow - User gets hand, cpu gets hand - TBD, user is shown current money
        prompted whether or not to bet, bet can only be integer and less than he currently has.
        3. Then the game hand is revealed
        4. The algorithm determines what hand both cpu and user have ex straight or high 7
        5. States both hands, and either removes or gives money to player

         */
        // You can generate a hand for the cpu via the player class
        // TODO: Figure out how I want to handle the cpu, another class or same as player class?
        // Generate hands for player cpu and the game
        // Start coding the hasstraight etc functions - where should these functions be?
        Game G1 = new Game();
        G1.playGame();

    }
}
