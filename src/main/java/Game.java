import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

// Poker Reimagined by Fletcher Cahill, Completed 2/25/26
public class Game {
    private Deck deck;
    private Player p1;
    private Player cpu;
    private int money;
    private int cpuMoney;
    private Scanner scan;
    private int winner;
    private ArrayList<Card> table;
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private GameView window;
    private String winHandName = "";
    private boolean showResult = false;

    // Constants
    public static final int  PLAYER_WON = 1;
    public static final int COMPUTER_WON = 2;
    public static final int TIE = 3;

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
        // Sort of like a dictionary: given a certain string returns certain value
        hashMap.put("RoyalFlush", 10);
        hashMap.put("StraightFlush", 9);
        hashMap.put("FourOfKind", 8);
        hashMap.put("Full", 7);
        hashMap.put("Flush", 6);
        hashMap.put("Straight", 5);
        hashMap.put("ThreeOfKind", 4);
        hashMap.put("TwoPair", 3);
        hashMap.put("Pair", 2);
        hashMap.put("Nada", 1);
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

        while (true){
            // NEW: If someone is bankrupt, wait for the GUI restart button to reset the money
            if (money <= 0 || cpuMoney <= 0) {
                System.out.println("Game Over! Click 'Restart Game' in the window to play again.");
                while (money <= 0 || cpuMoney <= 0) {
                    try { Thread.sleep(250); } catch (InterruptedException e) {}
                }
            }
            showResult = false;
            winner = 0;
            System.out.println("You have $" + money);
            System.out.println("House has $" + cpuMoney);

            // ... (Keep the rest of your existing playGame() code from here down)
            int bet = promptBet();
            resetTable();
            deck.shuffle();
            dealHands();
            dealTable();

            // Gets the description of each hand, what they have
            String playerHand = Checker.check(p1.getHand(), table);
            String cpuHand = Checker.check(cpu.getHand(), table);

            // Gets point total for each player
            int pPoints = getScoreValue(playerHand);
            int cPoints = getScoreValue(cpuHand);

            // Checks to see who won and then updates hands
            if (pPoints > cPoints) {
                winner = PLAYER_WON;
                winHandName = playerHand;
                money+= bet;
                cpuMoney -= bet;
            }
            else if (cPoints > pPoints) {
                winner = COMPUTER_WON;
                winHandName = cpuHand;
                money-= bet;
                cpuMoney += bet;
            }
            else{
                winner = TIE;
            }
            showResult = true;
            window.repaint();
            System.out.println("Play again? (y/n)");
            String choice = scan.nextLine();
            // Only keep playing if they want to
            if (!choice.equals("y")){
                System.out.println("Thanks for playing!");
                break;
            }


        }

    }
    // Returns the numeric value of the scores
    private int getScoreValue(String hand) {
        return hashMap.get(hand);
    }
    public int getWinner(){
        return winner;
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
    public String getWinHandName() {
        return winHandName;
    }

    public boolean isShowResult() {
        return showResult;
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
        int maxCpu = cpu.getHand().getFirst().getValue();
        // Only two cards in hand, so if second greater than first then it's the max
        if (p1.getHand().getLast().getValue() > maxPlayer) maxPlayer = p1.getHand().getLast().getValue();
        if (cpu.getHand().getLast().getValue() > maxCpu) maxCpu = cpu.getHand().getLast().getValue();
        return maxPlayer > maxCpu;
    }
    // Prints out these instructions to the console
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
            try{
                bet = scan.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
            }
            scan.nextLine();
        }
        return bet;
    }
    // Resets the game to its initial state
    public void restartGame() {
        this.money = 100;
        this.cpuMoney = 300;
        this.showResult = false;
        this.winner = 0;
        this.winHandName = "";
        resetTable();

        // Create a fresh, shuffled deck
        final String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        final int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        this.deck = new Deck(ranks, suits, values);
        this.deck.shuffle();

        window.repaint();
        System.out.println("\n*** Game Restarted! ***");
        System.out.println("Play again (y/n)");
    }


    public static void main(String[] args) {
        Game G1 = new Game();
        G1.playGame();
    }
}
