import java.util.ArrayList;
import java.util.Scanner;

// Poker by Fletcher Cahill 
// Some things to work
// Make more visually appealing, implement high card logic
// Now want to add some more features - bet
// Spades, hearts, diamonds, clover
public class Game {
    private final Deck deck;
    private Player p1;
    private Player cpu;
    private int money;
    private int cpuMoney;
    private Scanner scan;
    private ArrayList<Card> table;
    private GameView window;
    // Instance variables
    public Game(){
        final String[] ranks = {"2", "3", "4", "5", "6", "7", "8",
                          "9", "10", "J", "Q", "K", "A"};
        final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        final int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        deck = new Deck(ranks, suits, values);
        p1 = new Player("Challenger");
        cpu = new Player("CPU");
        table = new ArrayList<>();
        money = 100;
        cpuMoney = 300;
        scan = new Scanner(System.in);
        deck.shuffle();
        this.window = new GameView(this);
    }
    public void dealHands(){
        // Deals two cards to the player and the cpu
        for (int i = 0; i < 2; i++){
            p1.addCard(deck.deal());
            cpu.addCard(deck.deal());
        }
    }


    public void playGame(){
        printInstructions();
        while (money > 0){
            System.out.println("You have $" + money);
            System.out.println("House has $" + cpuMoney);
            int bet = promptBet();
            resetTable();
            deck.shuffle();
            dealHands();
            dealTable();
            window.repaint();

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
            // More user friendly way of printing out the hands
            System.out.print("Cpu hand: " + cpu.getHand().getFirst().getRank() + " " + cpu.getHand().getFirst().getSuit());
            System.out.println(" " + cpu.getHand().getLast().getRank() + " " + cpu.getHand().getLast().getSuit());
            System.out.println("Your score: " + playerPoints);
            System.out.println("Cpu score: " + cpuPoints);
            // If player has better hand than Cpu they win
            if (playerPoints > cpuPoints){
                System.out.println("You win the hand!");
                money+=bet;
                cpuMoney-=bet;
            }
            // If cpu has better hand than player then they win
            else if (cpuPoints > playerPoints){
                System.out.println("Cpu wins the hand!");
                money-=bet;
                cpuMoney+=bet;
            }
            else{
                System.out.println("You tied!");
            }
            if (money <= 0){
                System.out.println("You are out of money, goodbye!");
                return;
            }
            if (cpuMoney <= 0){
                System.out.println("House is out of money, you win!");
            }
            System.out.println("Play again? (y/n)");
            String choice = scan.nextLine();
            // Only keep playing if they want to
            if (!choice.equals("y")){
                System.out.println("Thanks for playing!");
                break;
            }


        }

    }
    public Player getPlayer(){
        return p1;
    }
    public Player getCpu(){
        return cpu;
    }
    public ArrayList<Card> getTable(){
        return table;
    }
    public int getMoney(){
        return this.money;
    }
    public int getCpuMoney(){
        return this.cpuMoney;
    }
    // Clears the hands and table
    private void resetTable(){
        p1.getHand().clear();
        cpu.getHand().clear();
        table.clear();
    }
    // Deals the table hand
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
        // Only two cards in hand, so if second greater than first then it's the max
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
        // Ensures bet is correct range
        int bet = -1;
        while (bet < 1 || bet > money){
            System.out.println("Enter your bet: ");
            bet = scan.nextInt();
            scan.nextLine();
        }
        return bet;
    }

    public static void main(String[] args) {
        Game G1 = new Game();
        G1.playGame();
    }
}
