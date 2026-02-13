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
        this.setTitle("Poker");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    private void drawPlayerCards(Graphics g){
        int initialX = 430;
        int y = 500;
        int spacing = 120;
        int x = initialX;
        for (Card c: backend.getPlayer().getHand()){
            System.out.println("Six)");
            c.draw(g, x, y, this);
            x+=spacing;
        }

    }
    private void drawCpuCards(Graphics g){
        int initialX = 200;
        int y = 100;
        int spacing = 120;
        int x = initialX;
        for (Card c: backend.getCpu().getHand()){
            System.out.println("Seven)");
            c.draw(g, x, y, this);
            x+=spacing;
        }
    }
    private void drawTableCards(Graphics g){
        int initialX = 200;
        int y = 300;
        int spacing = 120;
        int x = initialX;
        for (Card c: backend.getTable()){
            System.out.println("Eight)");
            c.draw(g, x, y, this);
            x+=spacing;
        }
    }
    public void paint (Graphics g){
        super.paint(g);
        g.drawImage(bgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(chips, 600, 600, 100, 100, this);
        g.setFont(new Font("SansSerif", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.fillOval(350, 600, 220, 100);
        g.drawImage(chips, 600, 100, 100, 100, this);
        g.fillOval(350, 100, 220, 100);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(backend.getMoney()), 400 , 660);
        g.drawString(Integer.toString(backend.getCpuMoney()), 400 , 100);
        System.out.println("hi");
        drawPlayerCards(g);
        drawCpuCards(g);
        drawTableCards(g);
        //g.drawString("CPU Money: ")


    }

}
