import javax.swing.*;
import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private int order;
    private Image im;

    // Card constructor
    public Card(String rank, String suit, int value, int order) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.order = order;
        // Not printing out my aces for some rzn
        String imageString = "src/main/resources/Cards/" + Integer.toString(order + 4) + ".png";
        // - 1 to adjust it
        this.im = new ImageIcon(imageString).getImage();
    }
    // Getters for card attributes
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public Image getIm() {
        return im;
    }
    public void draw(Graphics g, int x, int y, GameView view){
        // How will card know where to draw itself, should specify size, but
        // must probably take in argument of location
        g.drawImage(im, x, y, 100, 100, view);
    }
    public int getValue() {
        return value;
    }
    // Formatted to string for each card
    @Override
    public String toString() {
        return "Card{" +
                "rank='" + rank + '\'' +
                ", suit='" + suit + '\'' +
                ", value=" + value + "\'" +
                ", order=" + order + "\'" +
                '}';
    }
}
