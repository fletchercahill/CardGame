import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JFrame{
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private static final int CARDSPACING = 120;
    private static final int CARDSIZE = 100;
    private static final int OVALWIDTH = 220;
    private static final int OVALHEIGHT = 100;
    private final Image bgImage;
    private final Image chips;
    private final Image cardBack;
    private Game backend;
    // Constants
    private static final int USER_OVAL = 350;
    private static final int USER_MONEY_Y = 720;
    private static final int USER_X = 750;
    private static final int MONEY_X = 390;

    public GameView(Game backend){
            this.backend = backend;
            // Initializing the images
            cardBack = new ImageIcon("src/main/resources/cardback.png").getImage();
            bgImage = new ImageIcon("src/main/resources/board.png").getImage();
            chips = new ImageIcon("src/main/resources/chips3.png").getImage();

            // NEW: Add a MouseListener to act as our Restart Button
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int mx = e.getX();
                    int my = e.getY();
                    // Check if the click coordinates fall within our drawn button (x:800-950, y:30-70)
                    if (mx >= 800 && mx <= 950 && my >= 30 && my <= 70) {
                        backend.restartGame();
                    }
                }
            });

            // Creating and setting window operations
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Poker Reimagined");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    // Draws the user's card at bottom of screen
    private void drawPlayerCards(Graphics g){
        int initialX = 370;
        int y = 515;
        int x = initialX;
        for (Card c: backend.getPlayer().getHand()){
            c.draw(g, x, y, this);
            x+=CARDSPACING;
        }
    }
    // Prints the winner or tie of each hand in the top left of screen
    private void handleWin(Graphics g){
        if (backend.getWinner() == 2){
            g.setFont(new Font("Georgia", Font.BOLD, 60));
            g.drawString("CPU wins!", 30, 150);
        }
        else if (backend.getWinner() == 1){
            g.setFont(new Font("Georgia", Font.BOLD, 60));
            g.drawString("You win!", 30, 150);
        }
        else if (backend.getWinner() == 3){
            g.setFont(new Font("Georgia", Font.BOLD, 60));
            g.drawString("Tie", 30, 150);
        }
    }
    // Draws the CPU's cards at the top of the screen
    private void drawCpuCards(Graphics g){
        int initialX = 370;
        int y = 200;
        int x = initialX;
        for (Card c: backend.getCpu().getHand()){
            c.draw(g, x, y, this);
            x+=CARDSPACING;
        }
    }
    // Draws the table cards in the middle of the screen
    private void drawTableCards(Graphics g){
        int initialX = 200;
        int y = 360;
        int x = initialX;
        for (Card c: backend.getTable()){
            c.draw(g, x, y, this);
            x+=CARDSPACING;
        }
    }
    // Handles drawing the window at end game
    private void endGame(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Georgia", Font.BOLD, 100));
        // Draws this screen if player is out of money
        if (backend.getCpuMoney() > 0){
            g.drawString("Thanks for Playing", 30, 200);
        }
        // Draws this screen if CPU is out of money
        else{
            g.drawString("YOU WIN!", 80, 200);
        }
    }
    // Prints out the instructions on the screen in bottom left
    private void drawInstructions(Graphics g){
        g.setColor(Color.CYAN);
        g.setFont(new Font("Georgia", Font.BOLD, 30));
        g.drawString("INSTRUCTIONS", 25, 655);
        g.setFont(new Font("Georgia", Font.BOLD, 18));
        g.drawString("Welcome to Poker Reimagined!", 25, 700);
        g.drawString("You have one opportuntity to bet.", 25, 720);
        g.drawString("Best poker hand wins.", 25, 740);
        g.drawString("Place a bet to get started!", 25, 760);
        g.setFont(new Font("SansSerif", Font.BOLD, 50));
    }

    // Function that paints onto screen
    public void paint (Graphics g){
        super.paint(g);
        // Draws the background and deck and chips images
        g.drawImage(bgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(chips, 600, 660, CARDSIZE, CARDSIZE, this);
        g.drawImage(cardBack, 30, 360, CARDSIZE, CARDSIZE, this);
        g.setFont(new Font("SansSerif", Font.BOLD, 50));
        g.setColor(Color.RED);
        // Draws ovals displaying user scores
        g.fillOval(USER_OVAL, 660, OVALWIDTH, OVALHEIGHT);
        g.drawImage(chips, 600, 50, 100, 100, this);
        g.fillOval(USER_OVAL, 50, OVALWIDTH, OVALHEIGHT);
        g.setColor(Color.BLACK);
        drawInstructions(g);
        // Draws money for cpu and user
        g.drawString("$" + backend.getMoney(), MONEY_X,USER_MONEY_Y);
        g.drawString("$" + backend.getCpuMoney(), MONEY_X, 115);
        g.drawString("CPU", USER_X, 125);
        g.drawString("User", USER_X, USER_MONEY_Y);
        // Draws the cards after user bets
        drawPlayerCards(g);
        drawCpuCards(g);
        drawTableCards(g);
        handleWin(g);
        if (backend.isShowResult()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255, 215, 0));
            g2.setStroke(new BasicStroke(6));

            int winY;
            String winnerName;
            if (backend.getWinner() == 1) {
                // If the Player won, use the player's Y-coordinate
                winY = 515;
                winnerName = "Player";
            } else {
                // If the CPU won, use the CPU's Y-coordinate
                winY = 200;
                winnerName = "CPU";
            }

            if (backend.getWinner() == backend.PLAYER_WON) {
                // Draw box around PLAYER cards only
                g2.drawRect(370 - 5, winY - 5, 230, 110);

                // Draw player message
                g2.setFont(new Font("Georgia", Font.BOLD, 20));
                g2.drawString("You won with " + backend.getWinHandName(), 620, 565);

            } else if (backend.getWinner() == backend.COMPUTER_WON) {
                // Draw box around CPU cards only
                g2.drawRect(370 - 5, winY - 5, 230, 110);

                // Draw CPU message
                g2.setFont(new Font("Georgia", Font.BOLD, 20));
                g2.drawString("CPU won with " + backend.getWinHandName(), 620, 250);
            }
            else {
                g2.drawRect(370 - 5, 515 - 5, 230, 110);
                g2.drawRect(370 - 5, 200 - 5, 230, 110);
            }
            if (backend.getCpuMoney() <= 0 || backend.getMoney() <= 0){
                endGame(g);
            }

            // NEW: Draw the Restart Button on top of everything
            g.setColor(new Color(50, 50, 50)); // Dark Grey background
            g.fillRect(800, 30, 150, 40);
            g.setColor(Color.WHITE); // White text
            g.setFont(new Font("SansSerif", Font.BOLD, 18));
            g.drawString("Restart Game", 815, 57);

        }
        // Checks if needs to handle end game
        if (backend.getCpuMoney() <= 0 || backend.getMoney() <= 0){
            endGame(g);
        }
    }

}
