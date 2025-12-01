import java.util.ArrayList;
import java.util.Scanner;

// Some things to work
// Make more visually appealing, implement high card logic
public class Game {
    private final Deck deck;
    private Player p1;
    private Player cpu;
    private int money;
    private Scanner scan;
    private ArrayList<Card> table;
    // Instance variables
    public Game(){
        final String[] ranks = {"2", "3", "4", "5", "6", "7", "8",
                          "9", "10", "J", "Q", "K", "A"};
        final String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        final int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        deck = new Deck(ranks, suits, values);
        p1 = new Player("Challenger");
        cpu = new Player("CPU");
        table = new ArrayList<>();
        money = 100;
        scan = new Scanner(System.in);
        deck.shuffle();
    }
    public void dealHands(){
        for (int i = 0; i < 2; i++){
            p1.addCard(deck.deal());
            cpu.addCard(deck.deal());
        }
    }


    public void playGame(){
        printInstructions();
        while (money > 0){
            System.out.println("You have $" + money);
            int bet = promptBet();
            resetTable();
            deck.shuffle();
            dealHands();
            dealTable();
            // I want to make this more visually appealing
            System.out.print("Your hand: " + p1.getHand().getFirst().getRank() + " " + p1.getHand().getFirst().getSuit());
            System.out.println(" " + p1.getHand().getLast().getRank() + " " + p1.getHand().getLast().getSuit());
            System.out.print("Table: " + table.getFirst().getRank() + " " + table.getFirst().getSuit());
            System.out.print(" " + table.get(1).getRank() + " " + table.get(1).getSuit());
            System.out.print(" " + table.get(2).getRank() + " " + table.get(2).getSuit());
            System.out.print(" " + table.get(3).getRank() + " " + table.get(3).getSuit());
            System.out.println(" " + table.get(4).getRank() + " " + table.get(4).getSuit());
            int playerPoints = Checker.check(p1.getHand(), table);
            int cpuPoints = Checker.check(cpu.getHand(), table);
            // Both have only high card, call high card function
            if (playerPoints == 1 && cpuPoints == 1){
                boolean winner = compareHighCards();
                // Player wins
                if (winner) cpuPoints = 0;
                // Cpu wins
                else playerPoints = 0;
            }
            System.out.print("Cpu hand: " + cpu.getHand().getFirst().getRank() + " " + cpu.getHand().getFirst().getSuit());
            System.out.println(" " + cpu.getHand().getLast().getRank() + " " + cpu.getHand().getLast().getSuit());
            System.out.println("Your score: " + playerPoints);
            System.out.println("Cpu score: " + cpuPoints);
            if (playerPoints > cpuPoints){
                System.out.println("You win the hand!");
                money+=bet;
            }
            else if (cpuPoints > playerPoints){
                System.out.println("Cpu wins the hand!");
                money-=bet;
            }
            else{
                System.out.println("You tied!");
            }
            if (money <= 0){
                System.out.println("You are out of money, goodbye!");
                return;
            }
            System.out.println("Play again? (y/n)");
            String choice = scan.nextLine();
            if (!choice.equals("y")){
                System.out.println("Thanks for playing!");
                break;
            }


        }

    }
    private void resetTable(){
        p1.getHand().clear();
        cpu.getHand().clear();
        table.clear();
    }
    private void dealTable(){
        table.clear();
        // For now only betting at first, no betting in between flop,
        // turn, river
        for (int i = 0; i < 5; i++){
            table.add(deck.deal());
        }
    }
    private boolean compareHighCards(){
        int maxPlayer = p1.getHand().getFirst().getValue();
        int maxCpu = p1.getHand().getFirst().getValue();
        if (p1.getHand().getLast().getValue() > maxPlayer) maxPlayer = p1.getHand().getLast().getValue();
        if (cpu.getHand().getLast().getValue() > maxPlayer) maxCpu = cpu.getHand().getLast().getValue();
        return maxPlayer > maxCpu;
    }

    public static void printInstructions(){
        System.out.println("WELCOME TO POKER!");
        System.out.println("Here's how it's gonna work: ");
        System.out.println("You will start with $100 and can bet however much" +
                " you'd like before the flop");
        System.out.println("If you win you get that money from the CPU");

    }
    private int promptBet(){
        int bet = -1;
        while (bet < 1 || bet > money){
            System.out.println("Enter your bet: ");
            bet = scan.nextInt();
            scan.nextLine();
        }
        return bet;
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
