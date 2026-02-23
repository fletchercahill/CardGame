import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Image bgImage;
    private Image chips;
    private Game backend;
    public GameView(Game backend){
        this.backend = backend;
        bgImage = new ImageIcon("src/main/resources/board.png").getImage();
        chips = new ImageIcon("src/main/resources/chips3.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Poker Reimagined");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    private void drawPlayerCards(Graphics g){
        int initialX = 370;
        int y = 515;
        int spacing = 120;
        int x = initialX;
        for (Card c: backend.getPlayer().getHand()){
            c.draw(g, x, y, this);
            x+=spacing;
        }

    }
    private void drawPlayerWin(Graphics g){
        g.setFont(new Font("Georgia", Font.BOLD, 60));
        g.drawString("You win!", 30, 150);
    }
    private void drawCpuWin(Graphics g){
        g.setFont(new Font("Georgia", Font.BOLD, 60));
        g.drawString("CPU wins!", 30, 150);
    }
    private void drawTie(Graphics g){
        g.setFont(new Font("Georgia", Font.BOLD, 60));
        g.drawString("Tie", 30, 150);
    }
    private void drawCpuCards(Graphics g){
        int initialX = 370;
        int y = 200;
        int spacing = 120;
        int x = initialX;
        for (Card c: backend.getCpu().getHand()){
            c.draw(g, x, y, this);
            x+=spacing;
        }
    }
    private void drawTableCards(Graphics g){
        int initialX = 200;
        int y = 360;
        int spacing = 120;
        int x = initialX;
        for (Card c: backend.getTable()){
            c.draw(g, x, y, this);
            x+=spacing;
        }
    }
    public void paint (Graphics g){
        super.paint(g);
        g.drawImage(bgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(chips, 600, 660, 100, 100, this);
        g.setFont(new Font("SansSerif", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.fillOval(350, 660, 220, 100);
        g.drawImage(chips, 600, 50, 100, 100, this);
        g.fillOval(350, 50, 220, 100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Georgia", Font.BOLD, 30));
        g.drawString("INSTRUCTIONS", 25, 655);
        g.setFont(new Font("Georgia", Font.BOLD, 18));
        g.drawString("Welcome to Poker Reimagined!", 25, 700);
        g.drawString("You have one opportuntity to bet.", 25, 720);
        g.drawString("Best poker hand wins.", 25, 740);
        g.drawString("Place a bet to get started!", 25, 760);
        g.setFont(new Font("SansSerif", Font.BOLD, 50));
        g.drawString(Integer.toString(backend.getMoney()), 410 , 720);
        g.drawString(Integer.toString(backend.getCpuMoney()), 410 , 115);
        g.drawString("CPU", 750, 125);
        g.drawString("User", 750, 720);
        drawPlayerCards(g);
        drawCpuCards(g);
        drawTableCards(g);
        if (backend.getWinner() == 2){
            drawCpuWin(g);
            System.out.println("5");

        }
        else if (backend.getWinner() == 1){
            drawPlayerWin(g);
            System.out.println("6");

        }
        else if (backend.getWinner() == 3){
            drawTie(g);
        }


    }

}
