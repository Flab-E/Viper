package entity;
    
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public int speedCounter=0;
    
    // for sprite render
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    // for sprite animation
    public int animation_state = 1;
    public int animation_count = 0;
    public int max_animation_count=10;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
