package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY, x, y;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public void generate(GamePanel gp, Entity entity) {
        x = (int)(Math.random()*(gp.maxScreenRow-1));
        y = (int)(Math.random()*(gp.maxScreenCol-1));
        boolean valid = false;
        while(!valid) {
            if(gp.tileM.mapLayout[x][y]==0 || gp.tileM.mapLayout[x][y]==3 || (entity.x==x && entity.y==y)) {
                valid = true;
                break;
            } else {
                x = (int)(Math.random()*(gp.maxScreenRow-1));
                y = (int)(Math.random()*(gp.maxScreenCol-1));
            }
        }
    }
}
