package entity;
import main.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends Entity {

    GamePanel gp;
    main.KeyHandler keyH;

    public Player(GamePanel gp, main.KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
        direction = "down";
    }
    
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed == true) {
            direction = "up";
            y -= speed;                     // player moves up by 4 pixels
        }
        if(keyH.downPressed == true) {
            direction = "down";
            y += speed;                     // player moves down by 4 pixels
        }
        if(keyH.rightPressed == true) {
            direction = "right";
            x += speed;                     // player moves right by 4 pixels
        }
        if(keyH.leftPressed == true) {
            direction = "left";
            x -= speed;                     // player moves left by 4 pixels
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.white);               // set color to white
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);       // create a rectangle of 1 tile at 100,100
    
        BufferedImage image = null;
        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image=right1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
