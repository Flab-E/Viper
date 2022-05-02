package object;

import java.awt.image.BufferedImage;
import main.GamePanel;
import java.awt.Graphics2D;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collicion = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
